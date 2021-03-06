package com.app.pharmacy.apteka.controller;

import com.app.pharmacy.apteka.model.Order;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

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
    public String userRegister(com.app.pharmacy.apteka.model.User user) {

        user.setEncryted_password(bCryptPasswordEncoder.encode(user.getEncryted_password()));

        UserRole userRole=new UserRole();
        Role role=roleRepository.findByName("ROLE_USER");
        userRole.setRole(role);
        userRepository.save(user);
        userRole.setUser(user);
        userRoleRepository.save(userRole);
        return "login";
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

    @RequestMapping("/user/list")
    public String userList(ModelMap model){
        model.addAttribute("users",userRepository.findAll());

        return "userList";
    }

    @RequestMapping("/user/{id}/view")
    public String userView(ModelMap model, @PathVariable("id") Long id){
        com.app.pharmacy.apteka.model.User user=userRepository.getOne(id);
        model.addAttribute("user",user);
        List<UserRole> userRole=userRoleRepository.findAllByUser(user);
        Role role=(userRole.get(0).getRole());
        if(role.getName().equals("ROLE_ADMIN")){
            model.addAttribute("role","ADMIN");
        }
        else {
            model.addAttribute("role","USER");
        }
        List<Order> orders=orderRepository.findByUserId(id);
        model.addAttribute("orders",orders);

        return "userInfoPage";
    }
}