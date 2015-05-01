package com.carplates.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.carplates.domain.Penalty;

@Stateless
public class PenaltiesManager {

	@PersistenceContext(unitName = "PEN")
	EntityManager penaltiesEntityManager;
	
	public List<Penalty> findAll() {
		TypedQuery<Penalty> query = penaltiesEntityManager.createNamedQuery("Penalty.findAll", Penalty.class);
		List<Penalty> results = query.getResultList();
		return results;
	}

    public Penalty getPenaltyById(Long id){
        TypedQuery<Penalty> q = penaltiesEntityManager.createNamedQuery("Penalty.findById", Penalty.class);
        q.setParameter("id", id);
        try {
        	return q.getSingleResult();
        } catch(NoResultException e) {
        	return null;
        }
    }
    
    public List<Penalty> getPenaltiesByDriver(Long driverId, String regionId){
        TypedQuery<Penalty> q = penaltiesEntityManager.createNamedQuery("Penalty.findByDriver", Penalty.class);
        q.setParameter("driverid", driverId);
        q.setParameter("regionid", regionId);
        try {
        	return q.getResultList();
        } catch(NoResultException e) {
        	return null;
        }
    }

    public void persist(Penalty penalty) {
    	penaltiesEntityManager.persist(penalty);
    }

    public Penalty merge(Penalty penalty) {
        return penaltiesEntityManager.merge(penalty);
    }
    
    public void remove(Penalty penalty) {
    	penaltiesEntityManager.remove(penalty);
    }

}
