package com.app.pharmacy.apteka.controller;

import com.app.pharmacy.apteka.model.Message;
import com.app.pharmacy.apteka.model.Order;
import com.app.pharmacy.apteka.model.OutputMessage;
import com.app.pharmacy.apteka.model.User;
import com.app.pharmacy.apteka.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class MessageController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/chat")
    public String chat(){
        return "/chat";
    }

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public OutputMessage send(Order order) throws Exception {

        String time = new SimpleDateFormat("HH:mm").format(new Date());
        User user=userRepository.findUserByUsername(System.getProperty("user.name"));
        return new OutputMessage(user.getId(),order.getId(), time);
    }

    @RequestMapping("/topic/messages")
    @ResponseBody
    public String lists(OutputMessage outputMessage){
        return outputMessage.getDate();
    }

}
