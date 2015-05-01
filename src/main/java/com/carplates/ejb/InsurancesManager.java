
package com.carplates.ejb;

import com.carplates.domain.Insurance;
import com.carplates.domain.Penalty;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class InsurancesManager {
    @PersistenceContext(unitName = "INS")
	EntityManager insurancesEntityManager;
    
    public List<Insurance> findAll() {
		TypedQuery<Insurance> query = insurancesEntityManager.createNamedQuery("Insurance.findAll", Insurance.class);
		List<Insurance> results = query.getResultList();
		return results;
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
