package com.mcit.company.models.Controller;

import com.mcit.company.models.Models.CompanyProfile;
import com.mcit.company.models.Models.JobPositions;
import com.mcit.cvbuilder.user.MyUserDetailsService;
import com.mcit.cvbuilder.user.UserProfileRepository;
import com.mcit.models.RegisterForm;
import com.mcit.models.User;
import com.mcit.resumebuilder.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/company")
public class CompanyHomeController {


	@Autowired
	UserProfileRepository userProfileRepository;

	@Autowired
	MyUserDetailsService myUserDetailsService;


	@GetMapping("/home")
	public String LandingPage(Principal principal, Model model) {
		model.addAttribute("companyLoginId", principal.getName());
		return "company-home";
	}

	@GetMapping("/addJobs")
	public String addJobOffer(Model model){
//		System.out.println("smdhgfjhgj");
		model.addAttribute("newOffer",new JobPositions());
		return "company-add-job";
	}

	@PostMapping("/addJobs")
	public String addJobOffer(Model model,@ModelAttribute JobPositions newOffer){
		System.out.println("vinisha");
		model.addAttribute("newOffer", new JobPositions());
		UserProfileRepository.save(newOffer);
		return "redirect:/company/home";
	}
//	@GetMapping("/viewJobs")
//	public String viewJobOffer(Model model){
////		System.out.println("smdhgfjhgj");
//		model.addAttribute("newOffer",new JobPositions());
//		return "company-add-job";
//	}


    @GetMapping("/companyRegisterForm")
    public String getCompanyRegisterForm(Model model, RegisterForm registerForm) {
//        System.out.println("skhdfkjdhkf");
        model.addAttribute("RegisterForm", new UserRegistrationDto());
        return "company-registration";
    }


	@PostMapping(path = "/companyRegisterForm")
	public String submitRegistration(Model model, @ModelAttribute UserRegistrationDto registerForm) {

		try {
			User savedUser = myUserDetailsService.save(registerForm);
		} catch (Exception e) {
			System.out.println(e.toString());
			model.addAttribute("errorMessage", "CompanyName already exists Please Try Again");
			return "error";
		}

		CompanyProfile companyProfile = new CompanyProfile();

		companyProfile.setCompanyName(registerForm.getFirstName());
//		companyProfile.setLastName(registerForm.getLastName());
		companyProfile.setEmail(registerForm.getEmail());
		companyProfile.setCompanyLoginId(registerForm.getUserName());
		companyProfile.setTheme(1);

		model.addAttribute("RegisterForm", new RegisterForm());
		 userProfileRepository.save(companyProfile);
		return "redirect:/login";
	}

//	@GetMapping("/viewJobs")
//	public String view(Principal principal, Model model) {
////		if (principal != null && principal.getName() != "") {
////			boolean currentUsersProfile = principal.getName().equals(userId);
////			model.addAttribute("currentUsersProfile", currentUsersProfile);
////		}
//		String userName = principal.getName();
//		Optional<CompanyProfile> userProfileOptional = companyProfileRepository.findByCompanyName(userId);
//		userProfileOptional.orElseThrow(() -> new RuntimeException("Not found: " + userId));
//
//		model.addAttribute("userId", userId);
//		CompanyProfile companyProfile = userProfileOptional.get();
//		model.addAttribute("userProfile", companyProfile);
//		System.out.println(companyProfile.getJobs());
//
//		return "profile-templates/" + companyProfile.getTheme() + "/index";
//	}

}
