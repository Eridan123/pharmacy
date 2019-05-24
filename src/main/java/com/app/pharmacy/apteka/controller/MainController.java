package com.app.pharmacy.apteka.controller;

import com.app.pharmacy.apteka.model.Role;
import com.app.pharmacy.apteka.model.UserRole;
import com.app.pharmacy.apteka.repository.*;
import com.app.pharmacy.apteka.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class MainController {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    MedicineRepository medicineRepository;

    @Autowired
    MedicineCategoryRepository medicineCategoryRepository;

    @Autowired
    MedicineForRepository medicineForRepository;

    @RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
    public String welcomePage(Model model) {
        model.addAttribute("list", medicineRepository.findAll());
        model.addAttribute("categories",medicineCategoryRepository.findAll());
        model.addAttribute("fors",medicineForRepository.findAll());
        return "home";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model, Principal principal) {

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);

        return "adminPage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {

        return "login";
    }

    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }

    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String userInfo(Model model, Principal principal) {

        // After user login successfully.
        String userName = principal.getName();

        System.out.println("User Name: " + userName);

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = (String) WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);

        return "userInfoPage";
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.GET)
    public String userReg(ModelMap model) {

        // After user login successfully


        model.addAttribute("user", new com.app.pharmacy.apteka.model.User());

        return "registerUser";
    }
    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public String userRegi(com.app.pharmacy.apteka.model.User user) {

        user.setEncryted_password(bCryptPasswordEncoder.encode(user.getEncryted_password()));
        if(user.getUsername()=="eridan"){
            UserRole userRole=new UserRole();
            Role role=roleRepository.findByName("ROLE_ADMIN");
            userRole.setRole(role);
            userRole.setUser(user);
        }
        else{
            UserRole userRole=new UserRole();
            Role role=roleRepository.findByName("ROLE_USER");
            userRole.setRole(role);
            userRole.setUser(user);
        }
        userRepository.save(user);

        return "loginPage";
    }


    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {

        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();

            String userInfo = WebUtils.toString(loginedUser);

            model.addAttribute("userInfo", userInfo);

            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);

        }

        return "403Page";
    }

}