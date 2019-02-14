package com.app.pharmacy.apteka.repository;

import com.app.pharmacy.apteka.model.Role;
import com.app.pharmacy.apteka.model.User;
import com.app.pharmacy.apteka.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole,Long> {
    public List<UserRole> findAllByUser(User user);
}
