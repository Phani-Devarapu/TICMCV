package com.mcit.cvbuilder.user;

import java.security.Principal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mcit.models.Education;
import com.mcit.models.Job;
import com.mcit.models.UserProfile;

@Controller
public class ResumeBuilderController {

	HashMap<String, UserProfile> dbUserProfiles = new HashMap<String, UserProfile>();
	HashMap<String, UserProfile> modifiedUserProfile = new HashMap<String, UserProfile>();

	ButtonClicks buttonClicks = new ButtonClicks();

	@Autowired
	UserProfileRepository userProfileRepository;

	private void getUserDeatils(Principal principal) {
		String userId = principal.getName();
		Optional<UserProfile> userProfileOptional = userProfileRepository.findByUserName(userId);
		userProfileOptional.orElseThrow(() -> new RuntimeException("Not found: " + userId));
		this.dbUserProfiles.put(userId, userProfileOptional.get());
		this.modifiedUserProfile.put(userId, userProfileOptional.get());
	}

	@GetMapping("/edit")
	public String edit(Model model, Principal principal, @RequestParam(required = false) String add,
			@ModelAttribute Job job, @ModelAttribute UserProfile userprofilee) {

		buttonClicks.setClicked(false);
		buttonClicks.setIsaddNewEducationClicked(false);
		buttonClicks.setAddSkillClicked(false);

		UserProfile modifiedUserProfileTemp = null;

		if (this.modifiedUserProfile.containsKey(principal.getName())) {
			modifiedUserProfileTemp = this.modifiedUserProfile.get(principal.getName());
		} else {
			this.getUserDeatils(principal);
			modifiedUserProfileTemp = this.modifiedUserProfile.get(principal.getName());
		}
		if ("job".equals(add)) {
			buttonClicks.setClicked(true);
		}
		if ("education".equals(add)) {
			buttonClicks.setIsaddNewEducationClicked(true);
		}
		if ("skill".equals(add)) {
			buttonClicks.setAddSkillClicked(true);
		}
		Validations validations = new Validations();
		Set<String> validateProfile = validations.validateProfile(this.modifiedUserProfile.get(principal.getName()));
		validateProfile.addAll(validations.validateDates(this.modifiedUserProfile.get(principal.getName())));

		model.addAttribute("userProfile", modifiedUserProfileTemp);
		model.addAttribute("jobsList", modifiedUserProfileTemp.getJobs());
		model.addAttribute("eduList", modifiedUserProfileTemp.getEducations());
		model.addAttribute("skillsList", modifiedUserProfileTemp.getSkills());
		model.addAttribute("pa", buttonClicks);
		model.addAttribute("errors", validateProfile);

		model.addAttribute("newjob", new Job());
		model.addAttribute("newEducation", new Education());
		model.addAttribute("newSkill", new String());
		model.addAttribute("userId", principal.getName());

		return "profile-edit";
	}

	@PostMapping("/addJob")
	public String addJob(Model model, Principal principal, @ModelAttribute Job job) {
		System.out.println("The new job is " + job.getSummary());

		String[] split = job.getSummary().split(",");

		UserProfile moUserProfile = this.modifiedUserProfile.get(principal.getName());

		List<Job> oldJobs = moUserProfile.getJobs();

		List<String> arr = Arrays.asList(split);

		job.setResponsibilities(arr);
		oldJobs.add(job);
		moUserProfile.setJobs(oldJobs);

		this.modifiedUserProfile.put(principal.getName(), moUserProfile);

		model.addAttribute("userProfile", moUserProfile);
		model.addAttribute("newjob", new Job());
		model.addAttribute("newEducation", new Education());
		model.addAttribute("newSkill", new String());
		return "redirect:/edit";
	}

	@PostMapping("/addEducation")
	public String addEducation(Model model, Principal principal, @ModelAttribute Education newEducation) {
		System.out.println("The new job is " + newEducation.toString());

		UserProfile moUserProfileEd = this.modifiedUserProfile.get(principal.getName());

		List<Education> oldJEdus = moUserProfileEd.getEducations();
		oldJEdus.add(newEducation);
		moUserProfileEd.setEducations(oldJEdus);

		this.modifiedUserProfile.put(principal.getName(), moUserProfileEd);

		model.addAttribute("userProfile", moUserProfileEd);
		model.addAttribute("newjob", new Job());
		model.addAttribute("newEducation", new Education());
		model.addAttribute("newSkill", new String());
		return "redirect:/edit";
	}

	@PostMapping("/addSkill")
	public String addSkill(Model model, Principal principal, @RequestParam String newSkill) {

		UserProfile moUserProfileSk = this.modifiedUserProfile.get(principal.getName());

		List<String> skills = moUserProfileSk.getSkills();
		skills.add(newSkill);
		moUserProfileSk.setSkills(skills);

		this.modifiedUserProfile.put(principal.getName(), moUserProfileSk);

		model.addAttribute("userProfile", moUserProfileSk);
		model.addAttribute("newjob", new Job());
		model.addAttribute("newEducation", new Education());
		model.addAttribute("newSkill", new String());
		return "redirect:/edit";
	}

	@PostMapping("/updateUserDetails")
	public String updateUserDetails(Model model, Principal principal, @ModelAttribute UserProfile userProfile) {

		UserProfile mdUserProfileUpdate = this.modifiedUserProfile.get(principal.getName());

		mdUserProfileUpdate.setFirstName(userProfile.getFirstName());
		mdUserProfileUpdate.setLastName(userProfile.getLastName());
		mdUserProfileUpdate.setEmail(userProfile.getEmail());
		mdUserProfileUpdate.setDesignation(userProfile.getDesignation());
		mdUserProfileUpdate.setPhone(userProfile.getPhone());
		mdUserProfileUpdate.setSummary(userProfile.getSummary());

		this.modifiedUserProfile.put(principal.getName(), mdUserProfileUpdate);

		model.addAttribute("userProfile", mdUserProfileUpdate);
		model.addAttribute("newjob", new Job());
		model.addAttribute("newEducation", new Education());
		model.addAttribute("newSkill", new String());
		return "redirect:/edit";
	}

	@PostMapping("/edit")
	public String postEdit(Model model, Principal principal, @ModelAttribute UserProfile userProfile) {
		String userName = principal.getName();
		Validations validations = new Validations();
		Set<String> validateProfile = validations.validateProfile(this.modifiedUserProfile.get(principal.getName()));
		validateProfile.addAll(validations.validateDates(this.modifiedUserProfile.get(principal.getName())));

		if (validateProfile.size() > 0) {
			return "redirect:/edit";
		}

		userProfile.setId(this.dbUserProfiles.get(principal.getName()).getId());
		userProfile.setUserName(userName);
		userProfileRepository.save(this.modifiedUserProfile.get(principal.getName()));
		return "redirect:/view/" + userName;
	}

	@GetMapping("/delete")
	public String delete(Model model, Principal principal, @RequestParam String type, @RequestParam int index) {
		String userId = principal.getName();
		Optional<UserProfile> userProfileOptional = userProfileRepository.findByUserName(userId);
		userProfileOptional.orElseThrow(() -> new RuntimeException("Not found: " + userId));
		UserProfile userProfile = userProfileOptional.get();
		if ("job".equals(type)) {
			userProfile.getJobs().remove(index);
		} else if ("education".equals(type)) {
			userProfile.getEducations().remove(index);
		} else if ("skill".equals(type)) {
			userProfile.getSkills().remove(index);
		}
		userProfileRepository.save(userProfile);
		this.modifiedUserProfile.remove(principal.getName());
		this.getUserDeatils(principal);

		return "redirect:/edit";
	}

	@GetMapping("/edit/education")
	public String editEducation(Model model, Principal principal, @RequestParam String type, @RequestParam int index) {
		final String userId = principal.getName();
		UserProfile userProfile = this.modifiedUserProfile.get(userId);
		Education education = userProfile.getEducations().get(index);
		model.addAttribute("education", education);
		return "education-edit";

	}

	@GetMapping("/edit/experience")
	public String editExperience(Model model, Principal principal, @RequestParam String type, @RequestParam int index) {
		final String userId = principal.getName();
		UserProfile userProfile = this.modifiedUserProfile.get(userId);
		Job job = userProfile.getJobs().get(index);
		String responsibilities = job.getResponsibilities().stream().collect(Collectors.joining(","));
		job.setSummary(responsibilities);
		model.addAttribute("job", job);
		model.addAttribute("index", index);
		model.addAttribute("userId", userId);
		return "experience-edit";

	}

	@PostMapping("/edit/experience")
	public String editExperience(Model model, Principal principal, @ModelAttribute Job job, @RequestParam int index) {
		System.out.println("The post experience is " + job.toString() + " " + index);
		final String userId = principal.getName();
		UserProfile userProfile = this.modifiedUserProfile.get(userId);
		userProfile.getJobs().set(index, job);

		Optional<UserProfile> userProfileOptional = userProfileRepository.findByUserName(userId);
		UserProfile userProfile2 = userProfileOptional.get();
		userProfile2.getJobs().remove(index);
		userProfileRepository.save(userProfile2);

		job.setResponsibilities(Arrays.asList(job.getSummary().split(",")));

		userProfile2.getJobs().add(job);
		userProfileRepository.save(userProfile2);

		this.getUserDeatils(principal);

		return "redirect:/edit";

	}
}
