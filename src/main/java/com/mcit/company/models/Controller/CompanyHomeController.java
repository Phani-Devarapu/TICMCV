package com.mcit.company.models.Controller;

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

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/company")
public class CompanyHomeController {

    @Autowired
    JobPositionRepository jobPositionRepository;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Autowired
    MappingRepository mappingRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

    HashMap<String, CompanyProfile> modifiedCompanyProfile = new HashMap<String, CompanyProfile>();

    ButtonClicks buttonClicks = new ButtonClicks();

    @GetMapping("/home")
    public String LandingPage(Principal principal, Model model) {
        model.addAttribute("companyLoginId", principal.getName());
        return "company-home";
    }

    @GetMapping("/companyRegisterForm")
    public String getCompanyRegisterForm(Model model, RegisterForm registerForm) {
        model.addAttribute("RegisterForm", new UserRegistrationDto());
        return "company-registration";
    }


    @PostMapping(path = "/companyRegisterForm")
    public String submitRegistration(Model model, @ModelAttribute UserRegistrationDto registerForm) {
        System.out.println("I am heer");

        try {
            User savedUser = myUserDetailsService.save(registerForm);
            MapperClass mapping = new MapperClass();

            mapping.setCompanyLoginId(registerForm.getUserName());
//            System.out.println(registerForm.getUserName());
            mapping.setIscompanyLoginId(true);
//            System.out.println(mapping.toString());
            mappingRepository.save(mapping);
        } catch (Exception e) {
            System.out.println(e.toString());
            model.addAttribute("errorMessage", "CompanyName already exists Please Try Again");
            return "error";
        }

        return "redirect:/login";
    }
    @GetMapping("/addJobs")
    public String addJobOffer(Model model) {
        model.addAttribute("newOffer", new JobPositions());
        return "company-add-job";
    }

    @PostMapping("/addJobs")
    public String addJobOffer(Model model, @ModelAttribute JobPositions newOffer) {
//        System.out.println("vinisha");
        model.addAttribute("newOffer", new JobPositions());
        jobPositionRepository.save(newOffer);
        return "redirect:/company/viewJobs";
    }




    @GetMapping("/viewJobs")
    public String view(Model model) {
        List<JobPositions> all = jobPositionRepository.findAll();
        model.addAttribute("all", all);
        System.out.println(jobPositionRepository.findAll());
        return "job-list";
    }


    @GetMapping("/delete")
    public String delete(@RequestParam int jobId){

        jobPositionRepository.deleteById(jobId);

        return "redirect:/company/viewJobs";
    }
}