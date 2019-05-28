package com.app.pharmacy.apteka.controller;

import com.app.pharmacy.apteka.model.*;
import com.app.pharmacy.apteka.repository.MedicineAmountRepository;
import com.app.pharmacy.apteka.repository.MedicineRepository;
import com.app.pharmacy.apteka.repository.OrderRepository;
import com.app.pharmacy.apteka.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;
import java.util.zip.DeflaterOutputStream;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    MedicineRepository medicineRepository;

    @Autowired
    MedicineAmountRepository medicineAmountRepository;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserService userService;

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
    public String savePost(String data, String address, DeliveryMode delivery_mode, String phone_number){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user= (User) userService.findUserByUsername(auth.getName());
        Order order=new Order();
        order.setUser(user);
        order.setDate(new Date());
        order.setDelivery_status(DeliveryStatus.WAITING);
        order.setAddress(address);
        order.setPhone_number(phone_number);
        order.setDelivery_mode(delivery_mode);
        HashMap<Set<MedicineAmount>,Double> sets=dataParser(data,"fromSave");
        Set<MedicineAmount> medicineAmounts=null;
        Double total=null;
        for (Set<MedicineAmount> dd:sets.keySet()){
            medicineAmounts=dd;
            total=sets.get(dd);
            break;
        }
        order.setTotalCost(total);
        order.setMedicineAmounts(medicineAmounts);

        orderRepository.save(order);

        return "redirect:/order/user/"+user.getId()+"/list";
    }

    @GetMapping("/cart")
    public String getCart(Model model,String data){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user=userService.findUserByUsername(auth.getName());
        Set<MedicineAmount> medicineAmountSet=null;
        for (Set<MedicineAmount> dd:dataParser(data,"fromCart").keySet()){
            medicineAmountSet=dd;
            break;
        }
        model.addAttribute("amounts",medicineAmountSet);
        model.addAttribute("data",data);



        return "order/cart";
    }
    private HashMap<Set<MedicineAmount>,Double> dataParser(String data,String fo){
        Set<MedicineAmount> medicineAmountSet=new HashSet<MedicineAmount>();
        String [] splitted1=data.split("\\?");
        HashMap<String,String> map=new HashMap<>();
        for(int i=splitted1.length-1;i>=0;i--){
            String[] splitted2=splitted1[i].split("-");
            if(!map.containsKey(splitted2[0]))
                map.put(splitted2[0],splitted2[1]);
        }
        Double sum=0.0;
        for(String s:map.keySet()){
            MedicineAmount medicineAmount=new MedicineAmount();
            medicineAmount.setAmount(Double.valueOf(map.get(s)));
            Medicine medicine=medicineRepository.getOne(Long.valueOf(s));
            medicineAmount.setMedicine(medicine);
            medicineAmountSet.add(medicineAmount);
            if(fo.equals("fromSave")){
                medicineAmountRepository.save(medicineAmount);
            }
            sum=sum+(medicine.getCost()*Double.valueOf(map.get(s)));
        }
        HashMap<Set<MedicineAmount>,Double> result=new HashMap<>();
        result.put(medicineAmountSet,sum);
        return result;
    }
}
