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

import com.mcit.cvbuilder.models.Education;
import com.mcit.cvbuilder.models.Job;
import com.mcit.cvbuilder.models.RegisterForm;
import com.mcit.cvbuilder.models.User;
import com.mcit.cvbuilder.models.UserProfile;

@Controller
public class HomeController {

    @Autowired
    UserProfileRepository userProfileRepository;
    

    @GetMapping("/")
    public String home() {
        return "index";
    }
	@GetMapping(path="/registerForm")
	public String getRegisterForm(Model model,RegisterForm registerForm) {
		
		model.addAttribute("RegisterForm", new RegisterForm());
		return "registration";
	}
	
	@PostMapping(path="/register-submit")
	public String submitRegistration(@ModelAttribute RegisterForm registerForm,Model model) {
		
		
		
	        userProfileRepository.saveAll(registerForm);
		return "registration-success";
		}


    @GetMapping("/edit")
    public String edit(Model model, Principal principal, @RequestParam(required = false) String add, Job job, @ModelAttribute UserProfile userprofilee) {
        String userId = principal.getName();
        model.addAttribute("userId", userId);						//which userId? from where? -> from html page ${userId}
        Optional<UserProfile> userProfileOptional = userProfileRepository.findByUserName(userId);
        userProfileOptional.orElseThrow(() -> new RuntimeException("Not found: " + userId));
        UserProfile userProfile = userProfileOptional.get();
        if ("job".equals(add)) {
        	if (job == null) {
        		 
        		userProfile.getJobs().add(new Job());
        		
        	}
        	else {
        		userProfile.getJobs().add(job);
        		
        	}
        } else if ("education".equals(add)) {
            userProfile.getEducations().add(new Education());
        } else if ("skill".equals(add)) {
            userProfile.getSkills().add("");
        }

        model.addAttribute("userProfile", userProfile);
        return "profile-edit";
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
        return "redirect:/edit";
    }

    @PostMapping("/edit")
    public String postEdit(Model model, Principal principal, @ModelAttribute UserProfile userProfile) {
        String userName = principal.getName();
        Optional<UserProfile> userProfileOptional = userProfileRepository.findByUserName(userName);
        userProfileOptional.orElseThrow(() -> new RuntimeException("Not found: " + userName));
        UserProfile savedUserProfile = userProfileOptional.get();
        userProfile.setId(savedUserProfile.getId());
        userProfile.setUserName(userName);
        userProfileRepository.save(userProfile);
        return "redirect:/view/" + userName;
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
