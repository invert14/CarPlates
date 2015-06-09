package com.carplates.web.view;

import com.carplates.domain.CarPlate;
import com.carplates.domain.Comparer;
import com.carplates.domain.Owner;
import com.carplates.ejb.GlobalManager;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * User: sebastianpawlak Date: 24.05.2013
 */
@ViewScoped
@Named
public class GlobalView implements Serializable {

    @Inject
    private GlobalManager globalManager;

    private Owner owner;

    private CarPlate carPlate;

    private List<Owner> filteredOwners;

    private List<CarPlate> filteredCarPlates;

    private Boolean orderAscending = Boolean.TRUE;
    private String fieldName = "firstName";
    private String plateField = "brand";

    public Collection<Owner> getAllOwners() {
        return sort(globalManager.getAllOwners());
    }

    public Collection<CarPlate> getAllCarPlates() {
        return sortPlates(globalManager.getAllCarPlates());
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public CarPlate getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(CarPlate carPlate) {
        this.carPlate = carPlate;
    }

    public List<Owner> getFilteredOwners() {
        return sort(filteredOwners);
    }

    public void setFilteredOwners(List<Owner> filteredOwners) {
        this.filteredOwners = filteredOwners;
    }

    public List<CarPlate> getFilteredCarPlates() {
        return sortPlates(filteredCarPlates);
    }

    public void setFilteredCarPlates(List<CarPlate> filteredCarPlates) {
        this.filteredCarPlates = filteredCarPlates;
    }

    private List<Owner> sort(List<Owner> owners) {
        if (owners == null) {
            return null;
        }
        System.out.println(fieldName);
        Collections.sort(owners, new Comparator<Owner>() {
            public int compare(Owner o1, Owner o2) {
                int result;
                if ("firstName".equals(fieldName)) {
                    result = Comparer.compare(o1.getFirstName(), o2.getFirstName());
                } else if ("lastName".equals(fieldName)) {
                    result = Comparer.compare(o1.getLastName(), o2.getLastName());
                } else if ("pesel".equals(fieldName)) {
                    result = Comparer.compare(o1.getPesel(), o2.getPesel());
                } else if ("city".equals(fieldName)) {
                    result = Comparer.compare(o1.getCityAddress(), o2.getCityAddress());
                } else if ("postCode".equals(fieldName)) {
                    result = Comparer.compare(o1.getPostCodeAddress(), o2.getPostCodeAddress());
                } else if ("street".equals(fieldName)) {
                    result = Comparer.compare(o1.getStreetAddress(), o2.getStreetAddress());
                } else {
                    result = Comparer.compare(o1.getFirstName(), o2.getFirstName());
                }
                if (!orderAscending) {
                    result *= -1;
                }
                return result;
            }
        });
        return owners;
    }

    private List<CarPlate> sortPlates(List<CarPlate> plates) {
        if (plates == null) {
            return null;
        }
        System.out.println(plateField);
        Collections.sort(plates, new Comparator<CarPlate>() {
            public int compare(CarPlate o1, CarPlate o2) {
                int result;
                if ("brand".equals(plateField)) {
                    result = Comparer.compare(o1.getCarBrand(), o2.getCarBrand());
                } else if ("model".equals(plateField)) {
                    result = Comparer.compare(o1.getCarModel(), o2.getCarModel());
                } else if ("regDate".equals(plateField)) {
                    result = Comparer.compare(o1.getRegistrationDate(), o2.getRegistrationDate());
                } else if ("firstRegDate".equals(plateField)) {
                    result = Comparer.compare(o1.getFirstRegistrationDate(), o2.getFirstRegistrationDate());
                } else if ("expiryDate".equals(plateField)) {
                    result = Comparer.compare(o1.getRegistrationExpirationDate(), o2.getRegistrationExpirationDate());
                } else if ("vin".equals(plateField)) {
                    result = Comparer.compare(o1.getVin(), o2.getVin());
                } else if ("auth".equals(plateField)) {
                    result = Comparer.compare(o1.getRegistrationAuthority(), o2.getRegistrationAuthority());
                } else if ("regNo".equals(plateField)) {
                    result = Comparer.compare(o1.getRegistrationNumber(), o2.getRegistrationNumber());
                } else {
                    result = Comparer.compare(o1.getCarBrand(), o2.getCarBrand());
                }
                if (!orderAscending) {
                    result *= -1;
                }
                return result;
            }
        });
        return plates;
    }

    public String sortPlateBy(String plateField) {

        if (this.plateField.equals(plateField)) {
            orderAscending = !orderAscending;
        }
        this.plateField = plateField;
        sortPlates(filteredCarPlates);

        return null;
    }

    public String sortBy(String fieldName) {

        if (this.fieldName.equals(fieldName)) {
            orderAscending = !orderAscending;
        }
        this.fieldName = fieldName;
        sort(filteredOwners);

        return null;
    }

    public List<Owner> getOwnersForCarplate(CarPlate carplate) {
        System.out.println("HEEEEEEEEEEEEEEEEEJAAAAAAAAAAAAAAAAAA");
        return globalManager.getAllOwnersForCarPlate(carplate);
    }

    public void setCarPlateByNumber(String number) {
        this.carPlate = globalManager.getCarPlateByNumber(number);
    }
}
