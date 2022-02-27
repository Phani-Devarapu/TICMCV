package com.mcit.cvbuilder;

import java.security.Principal;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mcit.models.Education;
import com.mcit.models.Job;
import com.mcit.models.RegisterForm;
import com.mcit.models.User;
import com.mcit.models.UserProfile;
import com.mcit.resumebuilder.dto.UserRegistrationDto;

@Controller
public class HomeController {

	@Autowired
	UserProfileRepository userProfileRepository;

	@Autowired
	MyUserDetailsService myUserDetailsService;

	@GetMapping("/")
	public String home() {
		return "index";
	}

	@GetMapping("/home")
	public String LandingPage(Principal principal, Model model) {
		model.addAttribute("userId", principal.getName());
		return "home";
	}

	@GetMapping(path = "/registerForm")
	public String getRegisterForm(Model model, RegisterForm registerForm) {

		model.addAttribute("RegisterForm", new UserRegistrationDto());
		return "registration";
	}

	@PostMapping(path = "/registerForm")
	public String submitRegistration(Model model, @ModelAttribute UserRegistrationDto registerForm) {

		try {
			User savedUser = myUserDetailsService.save(registerForm);
		} catch (Exception e) {
			System.out.println(e.toString());
			model.addAttribute("errorMessage", "UserName already exists Please Try Again");
			return "error";
		}

		UserProfile userProfile = new UserProfile();

		userProfile.setFirstName(registerForm.getFirstName());
		userProfile.setLastName(registerForm.getLastName());
		userProfile.setEmail(registerForm.getEmail());
		userProfile.setUserName(registerForm.getUserName());
		userProfile.setTheme(1);

		model.addAttribute("RegisterForm", new RegisterForm());
		userProfileRepository.save(userProfile);
		return "registration-success";
	}

	@GetMapping("/view/{userId}")
	public String view(Principal principal, @PathVariable String userId, Model model) {
		if (principal != null && principal.getName() != "") {
			boolean currentUsersProfile = principal.getName().equals(userId);
			model.addAttribute("currentUsersProfile", currentUsersProfile);
		}
		String userName = principal.getName();
		Optional<UserProfile> userProfileOptional = userProfileRepository.findByUserName(userId);
		userProfileOptional.orElseThrow(() -> new RuntimeException("Not found: " + userId));

		model.addAttribute("userId", userId);
		UserProfile userProfile = userProfileOptional.get();
		model.addAttribute("userProfile", userProfile);
		System.out.println(userProfile.getJobs());

		return "profile-templates/" + userProfile.getTheme() + "/index";
	}

}
