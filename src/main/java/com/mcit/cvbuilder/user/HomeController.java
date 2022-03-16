package com.mcit.cvbuilder.user;

import java.security.Principal;

import java.util.Optional;

import com.mcit.company.models.JobPositions;
import com.mcit.company.models.MapperClass;
import com.mcit.company.models.Repository.MappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

    @Autowired
    MappingRepository mappingRepository;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/home")
    public String LandingPage(Principal principal, Model model) {
        model.addAttribute("userId", principal.getName());

        MapperClass byCompanyName = mappingRepository.findBycompanyLoginId(principal.getName());
        if (byCompanyName != null) {
            return "redirect:/company/home";
        } else {
            return "home";
        }
    }

    @GetMapping("/login")
    public String LoginPage() {
        return "login";
    }

    @GetMapping("/confirmlogout")
    public String LogoutPage() {
        return "confirm-logout";
    }

    @GetMapping("/logout")
    public String LogoutUser(@RequestParam(required = false) String confirm) {

        if (confirm.equals("YES")) {

            SecurityContextHolder.clearContext();
            return "redirect:/login";

        } else {
            return "redirect:/home";
        }
    }

    @GetMapping(path = "/registerForm")
    public String getRegisterForm(Model model, RegisterForm registerForm) {

       // model.addAttribute("RegisterForm", new UserRegistrationDto());
        return "registrationSubs";
    }
    
    @GetMapping(path = "user/registerForm")
    public String getUserRegisterForm(Model model, RegisterForm registerForm) {

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
        return "redirect:/login";
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
