package com.carplates.ejb;

import com.carplates.domain.CarPlate;
import com.carplates.domain.Owner;
import com.ocpsoft.shade.org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * User: sebastianpawlak Date: 24.05.2013
 */
@Stateless
public class GlobalManager {

    @PersistenceContext(unitName = "GD")
    EntityManager carplateEntityManagerGD;

    @PersistenceContext(unitName = "GA")
    EntityManager carplateEntityManagerGA;

    @PersistenceContext(unitName = "OWNGD")
    EntityManager ownerEntityManagerGD;

    @PersistenceContext(unitName = "OWNGA")
    EntityManager ownerEntityManagerGA;

//    List<EntityManager> allManagers;
    Map<String, EntityManager> ownerManagers;
    Map<String, EntityManager> carplatesManagers;

    @PostConstruct
    public void init() {

        carplatesManagers = new HashMap<String, EntityManager>();
        carplatesManagers.put("GD", carplateEntityManagerGD);
        carplatesManagers.put("GA", carplateEntityManagerGA);

        ownerManagers = new HashMap<String, EntityManager>();
        ownerManagers.put("GD", ownerEntityManagerGD);
        ownerManagers.put("GA", ownerEntityManagerGA);

    }

    public List<Owner> getAllOwners() {

        String queryString = ("SELECT o FROM Owner o");
        List<Owner> results = new LinkedList<Owner>();
        for (java.util.Map.Entry<String, EntityManager> em : ownerManagers.entrySet()) {
            Query q = em.getValue().createQuery(queryString);
            List<Owner> res = q.getResultList();
            for (Owner o : res) {
                o.setRegion(em.getKey());
            }
            results.addAll(res);
        }

//        for (Owner o : results) {
//            o.getCarPlates().size();
//        }
        return mergeOwners(results);

    }

    public Owner getOwnerByIdAndRegion(Long id, String region) {
        Query q = ownerManagers.get(region).createQuery("select o from Owner o where o.id=:id");
        q.setParameter("id", id);
        Owner result;
        try {
            result = (Owner) q.getSingleResult();
            result.setRegion(region);
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
            return null;
        }

        return result;
    }

    public Owner getOwnerByPesel(String pesel) {
        Owner result;
        for (java.util.Map.Entry<String, EntityManager> em : ownerManagers.entrySet()) {
            Query q = em.getValue().createQuery("select o from Owner o where o.pesel=:pesel");
            q.setParameter("pesel", pesel);
            try {
                result = (Owner) q.getSingleResult();
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

    public List<Owner> getAllOwnersForCarPlate(CarPlate carplate) {
        List<String> pesels = carplate.getPesels();
        List<Owner> owners = new ArrayList<Owner>();
        for (String pesel : pesels) {
            Owner owner = getOwnerByPesel(pesel);
            if (owner != null) {
                owners.add(owner);
            }
        }
        return owners;
    }

    public List<CarPlate> getAllCarPlates() {

        String queryString = ("SELECT c FROM CarPlate c");

        List<CarPlate> results = new LinkedList<CarPlate>();

        for (EntityManager em : carplatesManagers.values()) {

            Query q = em.createQuery(queryString);

            results.addAll(q.getResultList());
        }

//        for(CarPlate c : results) {
//            c.getOwners().size();
//        }
        return results;
    }

    public CarPlate getCarPlateByNumber(String number) {
        for (EntityManager em : carplatesManagers.values()) {
            Query q = em.createQuery("SELECT c FROM CarPlate c WHERE c.registrationNumber = :number");
            q.setParameter("number", number);

            try {
                return (CarPlate) q.getSingleResult();
            } catch (Exception e) {
                System.out.println("Error" + e.getMessage());
            }
        }

        return null;
    }

    private List<Owner> mergeOwners(List<Owner> owners) {

        List<Owner> results = new ArrayList<Owner>();

        Collections.sort(owners, ownerPeselComparator);

        List<Owner> ownersToMerge = new ArrayList<Owner>();
        String lastPesel = "";

        for (Owner owner : owners) {
//    System.out.println(owner.getFirstName() + owner.getLastName());

            if (lastPesel.equals("")) {
//                System.out.println("1");
                lastPesel = owner.getPesel();
                ownersToMerge = new ArrayList<Owner>();
                ownersToMerge.add(owner);
            } else if (lastPesel.equals(owner.getPesel())) {
//                System.out.println("2");
                ownersToMerge.add(owner);
            } else {
//                System.out.println("3");

                lastPesel = "";
                if (ownersToMerge.size() == 1) {
//                    System.out.println("3a");

                    results.add(ownersToMerge.get(0));
                } else {
//                    System.out.println("3b");

                    results.add(tempOwner(ownersToMerge));
                }

                lastPesel = owner.getPesel();
                ownersToMerge = new ArrayList<Owner>();
                ownersToMerge.add(owner);

            }
        }

        if (!lastPesel.equals("")) {
            System.out.println("X");
            if (ownersToMerge.size() == 1) {
                System.out.println("XA");

                results.add(ownersToMerge.get(0));
            } else {
                System.out.println("XB");

                results.add(tempOwner(ownersToMerge));
            }
        }

        Collections.sort(results, nameLastNameComparator);
        return results;
    }

    private Owner tempOwner(List<Owner> owners) {
        Owner result = new Owner();
        Set<CarPlate> resultsCarPlates = new HashSet<CarPlate>();

        try {
            BeanUtils.copyProperties(result, owners.get(0));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

//        for (Owner o : owners) {
//            resultsCarPlates.addAll(o.getCarPlates());
//        }
//
//        result.setCarPlates(resultsCarPlates);
        return result;
    }

    private Comparator<Owner> ownerPeselComparator = new Comparator<Owner>() {
        @Override
        public int compare(Owner o1, Owner o2) {
            if (o1 == null || o2 == null) {
                return -1;
            }

            if (o1.getPesel() == null || o2.getPesel() == null) {
                return -1;
            }

            return o1.getPesel().compareToIgnoreCase(o2.getPesel());
        }
    };

    private Comparator<Owner> nameLastNameComparator = new Comparator<Owner>() {
        @Override
        public int compare(Owner o1, Owner o2) {
            if (o1.getLastName().compareTo(o2.getLastName()) == 0) {
                return o1.getFirstName().compareTo(o2.getFirstName());
            }

            return o1.getLastName().compareTo(o2.getLastName());
        }
    };

}
