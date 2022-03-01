package com.mcit.company.models;

import com.mcit.models.RegisterForm;
import com.mcit.resumebuilder.dto.CompanyUserRegistrationDto;
import com.mcit.resumebuilder.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CompanyHomeController {


    @Autowired
    CompanyProfileRepository companyProfileRepository;

    @Autowired
    UserDetailsService UserDetailsService;

//	@GetMapping("/")
//	public String home() {
//		return "index";
//	}
//
//	@GetMapping("/home")
//	public String LandingPage(Principal principal, Model model) {
//		model.addAttribute("userId", principal.getName());
//		return "home";
//	}
//
//	@GetMapping("/login")
//	public String LoginPage() {
//		return "login";
//	}
//
//	@GetMapping("/confirmlogout")
//	public String LogoutPage() {
//		return "confirm-logout";
//	}
//
//	@GetMapping("/logout")
//	public String LogoutUser(@RequestParam(required = false) String confirm) {
//
//		if (confirm.equals("YES")) {
//
//			SecurityContextHolder.clearContext();
//			return "redirect:/login";
//
//		} else {
//			return "redirect:/home";
//		}
//	}

    @GetMapping("/companyRegisterForm")
    public String getCompanyRegisterForm(Model model, RegisterForm registerForm) {
//        System.out.println("skhdfkjdhkf");
        model.addAttribute("RegisterForm", new CompanyUserRegistrationDto());
        return "company-registration";
    }


//	@PostMapping(path = "/companyRegisterForm")
//	public String submitRegistration(Model model, @ModelAttribute CompanyUserRegistrationDto registerForm) {
//
//		try {
//			CompanyUser savedUser = myCompanyUserDetailsService.save(registerForm);
//		} catch (Exception e) {
//			System.out.println(e.toString());
//			model.addAttribute("errorMessage", "CompanyName already exists Please Try Again");
//			return "error";
//		}
//
//		CompanyProfile companyProfile = new CompanyProfile();
//
//		companyProfile.setCompanyName(registerForm.getCompanyName());
////		companyProfile.setLastName(registerForm.getLastName());
//		companyProfile.setCompanyEmail(registerForm.getEmail());
////		companyProfile.setCompanyPhone(registerForm.get);
//		companyProfile.setTheme(1);
//
//		model.addAttribute("RegisterForm", new RegisterForm());
//		companyProfileRepository.save(companyProfile);
//		return "redirect:/login";
//	}
//
//	@GetMapping("/view/{userId}")
//	public String view(Principal principal, @PathVariable String userId, Model model) {
//		if (principal != null && principal.getName() != "") {
//			boolean currentUsersProfile = principal.getName().equals(userId);
//			model.addAttribute("currentUsersProfile", currentUsersProfile);
//		}
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
