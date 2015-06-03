package com.carplates.ejb;

import com.carplates.domain.Insurance;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Stateless
public class InsurancesManager2 {

    @PersistenceContext(unitName = "INSWRT")
    EntityManager insurancesEntityManager;

    public List<Insurance> findAll() {
        TypedQuery<Insurance> query = insurancesEntityManager.createNamedQuery("Insurance.findAll", Insurance.class);
        List<Insurance> results = query.getResultList();
        return results;
    }

    public Insurance find(long plateId) {
        Query q = insurancesEntityManager.createQuery(
            "select i from Insurance i where i.carplateid = " + String.valueOf(plateId));

        Insurance result;

        try {
            result = (Insurance)q.getSingleResult();
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
            return null;
        }
        return result;
    }

    public void persist(Insurance insurance) {
        insurancesEntityManager.persist(insurance);
    }

    public Insurance merge(Insurance insurance) {
        return insurancesEntityManager.merge(insurance);
    }

    public void remove(Insurance insurance) {
        insurancesEntityManager.remove(insurance);
    }
}
