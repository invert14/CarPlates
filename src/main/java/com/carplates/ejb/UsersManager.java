package com.carplates.ejb;

import com.carplates.domain.User;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * User: sebastianpawlak
 * Date: 23.05.2013
 */

@Stateless
public class UsersManager {

    @Inject @Default
    EntityManager entityManager;

    public User getUserByUsernameAndPassword(String username, String password){

        Query q = entityManager.createQuery
                ("select u from User u where u.username=:username and u.password=:password");

        q.setParameter("password", password);
        q.setParameter("username", username);

        User result;

        try {
            result = (User) q.getSingleResult();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }

        return result;
    }

}
