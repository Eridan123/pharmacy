package com.app.pharmacy.apteka.dao;

import com.app.pharmacy.apteka.model.Role;
import com.app.pharmacy.apteka.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class RoleDao {

    @Autowired
    private EntityManager entityManager;

    public List<String> getRoleNames(Long userId) {
        String sql = "Select ur.role.name from " + UserRole.class.getName() + " ur " //
                + " where ur.user.id = :userId ";

        Query query = this.entityManager.createQuery(sql, String.class);
        query.setParameter("id", userId);
        return query.getResultList();
    }
}
