package com.mcit.company.models.Controller;

import com.mcit.company.models.JobPositionsMetrics;
import com.mcit.company.models.Models.CompanyProfile;
import com.mcit.company.models.Models.JobPositions;
import com.mcit.company.models.Models.MapperClass;
import com.mcit.company.models.Repository.CompanyProfileRepository;
import com.mcit.company.models.Repository.JobPositionRepository;
import com.mcit.company.models.Repository.MappingRepository;
import com.mcit.cvbuilder.user.ButtonClicks;
import com.mcit.cvbuilder.user.MyUserDetailsService;
import com.mcit.cvbuilder.user.UserProfileRepository;
import com.mcit.models.RegisterForm;
import com.mcit.models.User;
import com.mcit.resumebuilder.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/company")
public class CompanyHomeController {

	private final List<String> JOB_TYPES = List.of("Full Time", "Contract", "Permanent", "Temporary");

	@Autowired
	JobPositionRepository jobPositionRepository;

	@Autowired
	MyUserDetailsService myUserDetailsService;

	@Autowired
	MappingRepository mappingRepository;

	@Autowired
	CompanyProfileRepository companyProfileRepository;

	@Autowired
	UserProfileRepository userProfileRepository;

	HashMap<String, CompanyProfile> modifiedCompanyProfile = new HashMap<String, CompanyProfile>();

	@GetMapping("/home")
	public String LandingPage(Principal principal, Model model) {

		List<JobPositions> allJobPositions = getAllJobPositions();

		long fulFilledPositions = allJobPositions.stream().filter(job -> job.isFulfilled()).count();

		List<JobPositions> filterJobPositions = FilterJobPositions(allJobPositions, principal.getName());

		long openPositionsByEnterprise = filterJobPositions.stream().filter(job -> !job.isFulfilled()).count();

		JobPositionsMetrics jpm = new JobPositionsMetrics((allJobPositions.size()), fulFilledPositions,
				allJobPositions.size() - fulFilledPositions, Long.valueOf(filterJobPositions.size()),
				openPositionsByEnterprise);
		
		

		model.addAttribute("userId", principal.getName());
		model.addAttribute("metrics", jpm);
		return "company-home";
	}

	private List<JobPositions> getAllJobPositions() {
		return jobPositionRepository.findAll();
	}

	private List<JobPositions> FilterJobPositions(List<JobPositions> jobPositions, String filter) {
		return jobPositions.stream().filter(position -> position.getCompanyId().equals(filter))
				.collect(Collectors.toList());

	}

	@GetMapping("/registerForm")
	public String getCompanyRegisterForm(Model model, RegisterForm registerForm) {
		model.addAttribute("RegisterForm", new UserRegistrationDto());
		return "company-registration";
	}

	@PostMapping(path = "/registerForm")
	public String submitRegistration(Model model, @ModelAttribute UserRegistrationDto registerForm) {

		try {
			User savedUser = myUserDetailsService.save(registerForm);

			MapperClass mapping = new MapperClass();
			mapping.setCompanyLoginId(registerForm.getUserName());
			mapping.setIscompanyLoginId(true);

			mappingRepository.save(mapping);

			CompanyProfile companyProfile = new CompanyProfile();

			companyProfile.setCompanyId(registerForm.getUserName());
			companyProfile.setCompanyName(registerForm.getFirstName());
			companyProfile.setEmail(registerForm.getEmail());
			companyProfile.setCompanySecondaryName(registerForm.getLastName());

			companyProfileRepository.save(companyProfile);

		} catch (Exception e) {
			System.out.println(e.toString());
			model.addAttribute("errorMessage", "CompanyName already exists Please Try Again");
			return "error";
		}

		return "redirect:/login";
	}

	@GetMapping("/addJobs")
	public String addJobOffer(Model model, Principal principal) {

		JobPositions jobPositions = new JobPositions();
		
		CompanyProfile companyProfile = companyProfileRepository.findByCompanyId(principal.getName());
		
		jobPositions.setCompanyName(companyProfile.getCompanyName() + " ("+ companyProfile.getCompanySecondaryName() + ")");
		jobPositions.setCompanyEmail(companyProfile.getEmail());
		
		model.addAttribute("newOffer", jobPositions);
		model.addAttribute("jobTypes", JOB_TYPES);
		return "company-add-job";
	}

	@PostMapping("/addJobs")
	public String addJobOffer(Model model, @ModelAttribute JobPositions newOffer, Principal principal) {

		model.addAttribute("newOffer", new JobPositions());
		newOffer.setJobPostedTime(LocalDateTime.now());
		
		String[] split = newOffer.getSkillsInString().split(",");
		newOffer.setRequiredskills(Arrays.asList(split));
		newOffer.setFulfilled(false);
		newOffer.setCompanyId(principal.getName());
	
		System.out.println("I am here");
		System.out.println(newOffer.toString());
		
		jobPositionRepository.save(newOffer);
		return "redirect:/company/viewJobs?filter=YES";
	}

	@GetMapping("/viewJobs")
	public String view(Model model, Principal principal, @RequestParam(required = false) String filter) {
		List<JobPositions> all = jobPositionRepository.findAll();

		if (filter.equals("YES")) {
			List<JobPositions> filteredList = all.stream()
					.filter(position -> position.getCompanyId().equals(principal.getName()))
					.collect(Collectors.toList());
			model.addAttribute("all", filteredList);
		} else {
			model.addAttribute("all", all);
		}

		model.addAttribute("userId", principal.getName());
		return "job-list";
	}

	@GetMapping("/edit")
	public ModelAndView edit(@RequestParam int jobId) {
		System.out.println(jobId);
		ModelAndView mav = new ModelAndView("company-add-job");
		JobPositions jobPositions = jobPositionRepository.findById(jobId).get();
		mav.addObject("newOffer", jobPositions);
		mav.addObject("jobTypes", JOB_TYPES);
		return mav;
	}
	
	@GetMapping("/view/job")
	public ModelAndView viewJob(@RequestParam int jobId) {
		System.out.println(jobId);
		ModelAndView mav = new ModelAndView("company-view-job");
		JobPositions jobPositions = jobPositionRepository.findById(jobId).get();
		String skillsInString = jobPositions.getRequiredskills().stream().collect(Collectors.joining(","));
		jobPositions.setSkillsInString(skillsInString);
		mav.addObject("newOffer", jobPositions);
		mav.addObject("jobTypes", JOB_TYPES);
		return mav;
	}

	@GetMapping("/delete")
	public String delete(@RequestParam int jobId) {

		jobPositionRepository.deleteById(jobId);

		return "redirect:/company/viewJobs?filter=YES";
	}
}
