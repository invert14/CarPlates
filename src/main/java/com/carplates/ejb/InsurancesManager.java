package com.carplates.ejb;

import com.carplates.domain.Insurance;
import com.carplates.web.view.session.UserSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
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

    @Inject
    private UserSession userSession;

    @PostConstruct
    public void init() {
        insurancesManagers = new HashMap<String, EntityManager>();
        insurancesManagers.put("ins_pzu", insurancesEntityManagerPZU);
        insurancesManagers.put("ins_wrt", insurancesEntityManagerWRT);
    }

    private EntityManager getEntityManager() {
        return insurancesManagers.get(userSession.getLoggedInUser().getUsername());
    }

    public List<Insurance> findAll() {
        EntityManager em = getEntityManager();
        if (em == null) {
            return null;
        }
        TypedQuery<Insurance> query = em.createNamedQuery("Insurance.findAll", Insurance.class);
        List<Insurance> results = query.getResultList();
        return results;
    }

    public Insurance find(String carplate) {
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
                return result;
            }
        }
        return null;
    }

    public void persist(Insurance insurance) {
        EntityManager em = getEntityManager();
        if (em == null) {
            return;
        }
        persist(insurance);
    }

    public Insurance merge(Insurance insurance) {
        EntityManager em = getEntityManager();
        if (em == null) {
            return null;
        }
        return getEntityManager().merge(insurance);
    }

    public void remove(Insurance insurance) {
        EntityManager em = getEntityManager();
        if (em == null) {
            return;
        }
        getEntityManager().remove(insurance);
    }
}
