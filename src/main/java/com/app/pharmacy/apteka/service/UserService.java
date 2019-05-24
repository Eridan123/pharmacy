package com.app.pharmacy.apteka.service;

import com.app.pharmacy.apteka.model.User;
import org.springframework.stereotype.Service;


public interface UserService {
    public User findUserByUsername(String username);
}
