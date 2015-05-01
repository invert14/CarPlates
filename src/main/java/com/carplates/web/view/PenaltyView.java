package com.carplates.web.view;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Instance;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.solder.servlet.http.RequestParam;

import com.carplates.domain.Owner;
import com.carplates.domain.Penalty;
import com.carplates.ejb.GlobalManager;
import com.carplates.ejb.OwnersManager;
import com.carplates.ejb.PenaltiesManager;
import com.carplates.web.view.session.UserSession;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class PenaltyView implements Serializable {

    private List<Penalty> filteredPenalties;

    private Owner driverForAddEdit;

    @Inject
    private PenaltiesManager penaltyManager;

    @Inject
    private OwnersManager ownersManager;

    @Inject
    private GlobalManager globalManager;

    @Inject
    @RequestParam("penaltyId")
    private Instance<String> penaltyId;

    @Inject
    private UserSession userSession;

    private Penalty penalty;

    private Mode mode;

    public List<String> getRegions() {
        List<String> list = new ArrayList<String>();
        list.add("GA");
        list.add("GD");
        list.add("GSP");
        return list;
    }

    public void removePenalty(Long id) {
        Penalty p = penaltyManager.getPenaltyById(id);
        penaltyManager.remove(p);
        redirect("/faces/global/penalties-view.xhtml");
    }

    @PostConstruct
    public void init() {
        if (penaltyId.get() == null || penaltyId.get().trim().isEmpty()) {
            prepareToAdd();
        } else {
            prepareToEdit();
        }
    }

    private void prepareToAdd() {
        mode = Mode.ADD;
        penalty = new Penalty();
    }

    private void prepareToEdit() {
        try {
            mode = Mode.EDIT;
            penalty = penaltyManager.getPenaltyById(new Long(penaltyId.get()));
            driverForAddEdit = globalManager.getOwnerByIdAndRegion(penalty.getDriverid(), penalty.getRegionid());
        } catch (Exception e) {
            System.out.println("ERROR " + e.getMessage());
        }

    }

    public void saveNew() {
        penalty.setDriverid(driverForAddEdit.getId());
        penalty.setRegionid(driverForAddEdit.getRegion());
        penaltyManager.persist(penalty);
        redirect("/faces/global/penalties-view.xhtml");
    }

    public void edit() {
        penalty.setDriverid(driverForAddEdit.getId());
        penalty.setRegionid(driverForAddEdit.getRegion());
        penalty = penaltyManager.merge(penalty);
        redirect("/faces/global/penalties-view.xhtml");
    }

    public List<Penalty> getPenalties() {
        if (userSession.isLocal()) {
            return new ArrayList<Penalty>();
        }
        return penaltyManager.findAll();
    }

    public List<Penalty> getFilteredPenalties() {
        return filteredPenalties;
    }

    public void setFilteredPenalties(List<Penalty> filteredCarPlates) {
        this.filteredPenalties = filteredCarPlates;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public Penalty getPenalty() {
        return penalty;
    }

    public void setPenalty(Penalty carPlate) {
        this.penalty = carPlate;
    }

    public Owner getDriverForAddEdit() {
        return driverForAddEdit;
    }

    public void setDriverForAddEdit(Owner driverForAddEdit) {
        this.driverForAddEdit = driverForAddEdit;
    }

    public Instance<String> getPenaltyId() {
        return penaltyId;
    }

    public void setPenaltyId(Instance<String> carPlateId) {
        this.penaltyId = carPlateId;
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

        ADD, EDIT
    }
}
