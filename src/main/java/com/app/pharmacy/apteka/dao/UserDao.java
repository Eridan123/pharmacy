package com.app.pharmacy.apteka.dao;


import com.app.pharmacy.apteka.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.Transactional;

//@Repository
@Transactional
public class UserDao {

    @Autowired
    EntityManager entityManager;

    public User findUserAccount(String username){
        try {
            String sql = "Select e from " + User.class.getName() + " e " //
                    + " Where e.username = :username";

            Query query = entityManager.createQuery(sql, User.class);
            query.setParameter("username", username);

            return (User) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
