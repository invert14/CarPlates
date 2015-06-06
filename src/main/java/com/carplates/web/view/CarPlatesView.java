package com.carplates.web.view;

import com.carplates.domain.*;
import com.carplates.ejb.*;
import com.carplates.web.view.session.UserSession;
import org.jboss.solder.servlet.http.RequestParam;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Instance;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

/**
 * User: sebastianpawlak
 * Date: 23.05.2013
 */

@Named
@ViewScoped
public class CarPlatesView implements Serializable {

    private List<CarPlate> filteredCarPlates;

    private List<Owner> ownersForAddEdit;

    @Inject
    private CarPlatesManager carPlatesManager;

    @Inject
    @RequestParam("carPlateId")
    private Instance<Long> carPlateId;

    @Inject
    private UserSession userSession;

    private CarPlate carPlate;

    private Mode mode;

    @PostConstruct
    public void init() {
        if(carPlateId.get() == null || carPlateId.get() < 1) {
            prepareToAdd();
        } else {
            prepareToEdit();
        }

    }

    private void prepareToAdd() {

        mode = Mode.ADD;
        carPlate = new CarPlate();
        ownersForAddEdit = new ArrayList<Owner>();

    }

    private void prepareToEdit() {

        try {
            mode = Mode.EDIT;
            carPlate = carPlatesManager.getCarPlateById(carPlateId.get());
//            ownersForAddEdit = new ArrayList<Owner>(carPlate.getOwners());
//            carPlate.getOwners().size();

        } catch (Exception e) {
            System.out.println("ERROR " + e.getMessage());
        }

    }

    public void saveNew() {
//        carPlate.setOwners(new HashSet<Owner>(ownersForAddEdit));
        carPlatesManager.persist(carPlate);
        redirect("/faces/local/carplates-view.xhtml");
    }

    public void edit() {
        //carPlate = carPlatesManager.getCarPlateById(carPlate.getId());
//        carPlate.setOwners(new HashSet<Owner>(ownersForAddEdit));
        carPlate = carPlatesManager.merge(carPlate);
        redirect("/faces/local/carplates-view.xhtml");
    }

    public List<CarPlate> getCarPlates() {
        if(userSession.isGlobal()) return new ArrayList<CarPlate>();

        return sortPlates(carPlatesManager.getCarPlates());
    }

    public List<CarPlate> getFilteredCarPlates() {
        return sortPlates(filteredCarPlates);
    }

    public void setFilteredCarPlates(List<CarPlate> filteredCarPlates) {
        this.filteredCarPlates = filteredCarPlates;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public CarPlate getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(CarPlate carPlate) {
        this.carPlate = carPlate;
    }

    public Instance<Long> getCarPlateId() {
        return carPlateId;
    }

    public void setCarPlateId(Instance<Long> carPlateId) {
        this.carPlateId = carPlateId;
    }

    public List<Owner> getOwnersForAddEdit() {
        return ownersForAddEdit;
    }

    public void setOwnersForAddEdit(List<Owner> ownersForAddEdit) {
        this.ownersForAddEdit = ownersForAddEdit;
    }

    private void redirect(String url) {

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.redirect(ec.getRequestContextPath() + url);
        } catch (IOException e) {
            System.out.println("ERROR " + e.getMessage());
        }

    }


    private enum Mode {
        ADD,EDIT
    }
    
    private Boolean orderAscending = Boolean.TRUE;
    private String plateField = "brand";
    
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
                    result = Comparer.compare(o1.getRegistrationDate(),o2.getRegistrationDate());
                } else if ("firstRegDate".equals(plateField)) {
                    result = Comparer.compare(o1.getFirstRegistrationDate(),o2.getFirstRegistrationDate());
                } else if ("expiryDate".equals(plateField)) {
                    result = Comparer.compare(o1.getRegistrationExpirationDate(),o2.getRegistrationExpirationDate());
                } else if ("vin".equals(plateField)) {
                    result = Comparer.compare(o1.getVin(), o2.getVin());
                } else if ("auth".equals(plateField)) {
                    result = Comparer.compare(o1.getRegistrationAuthority(), o2.getRegistrationAuthority());
                } else if ("regNo".equals(plateField)) {
                    result = Comparer.compare(o1.getRegistrationNumber(), o2.getRegistrationNumber());
                } else {
                    result = Comparer.compare(o1.getCarBrand(),o2.getCarBrand());
                }
                if(!orderAscending){
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
    
}
