package com.carplates.ejb;

import com.carplates.domain.CarPlate;
import com.carplates.domain.Owner;
import com.carplates.domain.RegistrationAuthority;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * User: sebastianpawlak
 * Date: 23.05.2013
 */

@Stateless
public class CarPlatesManager {

    @Inject @Default
    EntityManager entityManager;

    public List<CarPlate> getCarPlates(){

        Query q = entityManager.createQuery
                ("select c from CarPlate c");


        List<CarPlate> result;

        try {
            result =  q.getResultList();
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
            return new ArrayList<CarPlate>();
        }

//        for(CarPlate c : result) {
//            c.getOwners().size();
//        }

        return result;
    }


    public CarPlate getCarPlateById(Long id){

        Query q = entityManager.createQuery
                ("select c from CarPlate c where c.id=:id");

        q.setParameter("id", id);

        CarPlate result;

        try {
            result = (CarPlate) q.getSingleResult();
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
            return null;
        }

        return result;
    }

    public void persist(CarPlate carPlate) {
        Query q = entityManager.createQuery
                ("select r from RegistrationAuthority r");
        RegistrationAuthority result;
        try {
            result = (RegistrationAuthority) q.getSingleResult();
            carPlate.setRegistrationAuthority(result);
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
            return;
        }
        entityManager.persist(carPlate);
    }

    public CarPlate merge(CarPlate carPlate) {
        return entityManager.merge(carPlate);
    }



}
