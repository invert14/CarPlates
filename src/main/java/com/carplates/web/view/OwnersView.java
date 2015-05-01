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
public class OwnersView implements Serializable {

    private List<Owner> filteredOwners;

    @Inject
    private OwnersManager ownersManager;

    @Inject
    @RequestParam("ownerId")
    private Instance<Long> ownerId;

    @Inject
    private UserSession userSession;


    private Owner owner;

    private Mode mode;

    private Boolean orderAscending = Boolean.TRUE;
    private String fieldName = "firstName";
    
    @PostConstruct
    public void init() {
        if(ownerId.get() == null || ownerId.get() < 1) {
            prepareToAdd();
        } else {
            prepareToEdit();
        }

    }

    private void prepareToAdd() {

        mode = Mode.ADD;
        owner = new Owner();

    }

    private void prepareToEdit() {

        try {
            mode = Mode.EDIT;
            owner = ownersManager.getOwnerById(ownerId.get());

        } catch (Exception e) {
            System.out.println("ERROR " + e.getMessage());
        }

    }

    public void saveNew() {
        ownersManager.persist(owner);
        redirect("/faces/local/owners-view.xhtml");
    }

    public void edit() {
        owner = ownersManager.merge(owner);
        redirect("/faces/local/owners-view.xhtml");
    }

    public List<Owner> getOwners() {

        if(userSession.isGlobal()) return new ArrayList<Owner>();

        return sort(ownersManager.getOwners());
    }

    public List<Owner> getFilteredOwners() {
        return sort(filteredOwners);
    }

    public void setFilteredOwners(List<Owner> filteredOwners) {
        this.filteredOwners = filteredOwners;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Instance<Long> getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Instance<Long> ownerId) {
        this.ownerId = ownerId;
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
                if(!orderAscending){
                    result *= -1;
                }
                return result;
            }
        });
        return owners;
    }

    public String sortBy(String fieldName) {
        
        if (this.fieldName.equals(fieldName)) {
            orderAscending = !orderAscending;
        }
        this.fieldName = fieldName;
        sort(filteredOwners);

        return null;
    }
}
