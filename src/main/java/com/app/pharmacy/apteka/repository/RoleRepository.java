package com.app.pharmacy.apteka.repository;

import com.app.pharmacy.apteka.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Long> {
    public Role findByName(String name);
}
