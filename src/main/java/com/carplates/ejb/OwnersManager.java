package com.carplates.ejb;

import com.carplates.domain.Owner;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * User: sebastianpawlak Date: 23.05.2013
 */
@Stateless
public class OwnersManager {

    @Inject
    @Default
    EntityManager entityManager;

    public List<Owner> getOwners() {

        Query q = entityManager.createQuery("select o from Owner o");

        List<Owner> result;

        try {
            result = q.getResultList();
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
            return new ArrayList<Owner>();
        }

//        for(Owner o : result) {
//            o.getCarPlates().size();
//        }
        return result;
    }

    public Owner getOwnerById(Long id) {

        Query q = entityManager.createQuery("select o from Owner o where o.id=:id");

        q.setParameter("id", id);

        Owner result;

        try {
            result = (Owner) q.getSingleResult();
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
            return null;
        }

        return result;
    }

    public void persist(Owner owner) {
        entityManager.persist(owner);
    }

    public Owner merge(Owner owner) {
        return entityManager.merge(owner);
    }

}
