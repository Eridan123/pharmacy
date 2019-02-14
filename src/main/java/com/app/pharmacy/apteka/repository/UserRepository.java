package com.app.pharmacy.apteka.repository;

import com.app.pharmacy.apteka.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    public User findUserByUsername(String username);

}
