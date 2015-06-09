package com.carplates.ejb;

import com.carplates.domain.Insurance;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Stateless
public class InsurancesManager {

    @PersistenceContext(unitName = "INSPZU")
    EntityManager insurancesEntityManagerPZU;

    @PersistenceContext(unitName = "INSWRT")
    EntityManager insurancesEntityManagerWRT;

    Map<String, EntityManager> insurancesManagers;

    @PostConstruct
    public void init() {
        insurancesManagers = new HashMap<String, EntityManager>();
        insurancesManagers.put("ins_pzu", insurancesEntityManagerPZU);
        insurancesManagers.put("ins_wrt", insurancesEntityManagerWRT);
    }

    public List<Insurance> findByCompany(String username) {
        EntityManager em = insurancesManagers.get(username);
        if (em == null) {
            return null;
        }
        TypedQuery<Insurance> query = em.createNamedQuery("Insurance.findAll", Insurance.class);
        List<Insurance> results = query.getResultList();
        return results;
    }

    public List<Insurance> findByCarplate(String carplate) {
        List<Insurance> insurances = new ArrayList<Insurance>();
        for (java.util.Map.Entry<String, EntityManager> em : insurancesManagers.entrySet()) {
            Query q = em.getValue().createQuery("select i from Insurance i where i.carplate = :carplate");
            q.setParameter("carplate", carplate);

            Insurance result;

            try {
                result = (Insurance) q.getSingleResult();
            } catch (Exception e) {
                System.out.println("Error" + e.getMessage());
                result = null;
            }

            if (result != null) {
                insurances.add(result);
            }
        }
        return insurances;
    }
}
