package com.app.pharmacy.apteka.controller;

import com.app.pharmacy.apteka.model.DeliveryStatus;
import com.app.pharmacy.apteka.model.Order;
import com.app.pharmacy.apteka.model.User;
import com.app.pharmacy.apteka.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    OrderRepository orderRepository;

    @RequestMapping("/list")
    public String list(ModelMap model){
        List<Order> list=orderRepository.findAll();
        model.addAttribute("list",list);

        return "order/list";
    }

    @RequestMapping("/user/{userId}/list")
    public String list(ModelMap model,@PathVariable("userId") Long userId){

        List<Order> list=orderRepository.findByUserId(userId);
        model.addAttribute("list",list);

        return "order/list";
    }

    @RequestMapping("/{id}/save")
    public String saveGet(ModelMap model, @PathVariable("id") Long id){
        if(id==null||id==0){
            model.addAttribute("model",new Order());
        }
        else{
            Order order=orderRepository.getOne(id);
            model.addAttribute("model",order);
        }

        return "order/form";
    }

    @PostMapping("/save")
    public String savePost(Order order){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user= (User) userDetailsService.loadUserByUsername(auth.getName());

        if(order.getId()==null || order.getId()==0){
            order.setDelivery_status(DeliveryStatus.WAITING);
            order.setDate(new Date());
            order.setUser(user);
            orderRepository.save(order);
        }
        else{
            orderRepository.save(order);
        }

        return "redirect:/order/user/"+user.getId()+"/list";
    }
}
