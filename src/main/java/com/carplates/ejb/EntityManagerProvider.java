package com.carplates.ejb;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * User: sebastianpawlak Date: 23.05.2013
 */
@ApplicationScoped
public class EntityManagerProvider {

    @PersistenceContext(unitName = "GD")
    EntityManager GD;

    @PersistenceContext(unitName = "GA")
    EntityManager GA;

    @PersistenceContext(unitName = "GSP")
    EntityManager GSP;

    //@Resource(name = "region")
    private String region = "GA";

    @Produces
    @Default
    EntityManager getEntityManager() {

        if ("GA".equals(region)) {
            return GA;
        }
        if ("GD".equals(region)) {
            return GD;
        }
        if ("GSP".equals(region)) {
            return GSP;
        }
        return null;
    }
}
