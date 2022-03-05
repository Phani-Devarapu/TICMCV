package com.mcit.company.models.Controller;

import com.mcit.company.models.JobPositionsMetrics;
import com.mcit.company.models.Models.CompanyProfile;
import com.mcit.company.models.Models.JobPositions;
import com.mcit.company.models.Models.MapperClass;
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
	
	private final List<String> JOB_TYPES = List.of("Full Time","Contract","Permanent","Temporary");

	@Autowired
	JobPositionRepository jobPositionRepository;

	@Autowired
	MyUserDetailsService myUserDetailsService;

	@Autowired
	MappingRepository mappingRepository;

	@Autowired
	UserProfileRepository userProfileRepository;

	HashMap<String, CompanyProfile> modifiedCompanyProfile = new HashMap<String, CompanyProfile>();

	

	@GetMapping("/home")
	public String LandingPage(Principal principal, Model model) {
		
		model.addAttribute("userId", principal.getName());

		List<JobPositions> jobPositions = jobPositionRepository.findByCompanyId(principal.getName());

		long fulFilledPositions = jobPositions.stream().filter(job -> job.isFulfilled()).count();

		JobPositionsMetrics jpm = new JobPositionsMetrics((jobPositions.size()), fulFilledPositions,
				jobPositions.size() - fulFilledPositions);

		model.addAttribute("metrics", jpm);
		return "company-home";
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
		jobPositions.setCompanyName(principal.getName());
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

	@GetMapping("/viewJobs/{id}")
	public ModelAndView viewJob(@PathVariable int id) {

		ModelAndView modelAndView = new ModelAndView("job-view");
		JobPositions jobPositions = jobPositionRepository.findById(id).get();
		modelAndView.addObject("job", jobPositions);
		return modelAndView;

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

	@GetMapping("/delete")
	public String delete(@RequestParam int jobId) {

		jobPositionRepository.deleteById(jobId);

		return "redirect:/company/viewJobs?filter=YES";
	}
}
