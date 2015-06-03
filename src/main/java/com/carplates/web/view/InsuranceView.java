package com.carplates.web.view;

import com.carplates.domain.CarPlate;
import com.carplates.domain.Insurance;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.carplates.ejb.CarPlatesManager;
import com.carplates.ejb.InsurancesManager;
import com.carplates.ejb.InsurancesManager2;
import com.carplates.web.view.session.UserSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class InsuranceView implements Serializable {

    @Inject
    private InsurancesManager insurancesManager;
    @Inject
    private InsurancesManager2 insurancesManager2;

    @Inject
    private CarPlatesManager carPlatesManager;

    @Inject
    private UserSession userSession;

    public List<Insurance> getInsurances() {
        if (!userSession.isInsurance()) {
            return new LinkedList<Insurance>();
        }
        String username = userSession.getLoggedInUser().getUsername();
        if (username.equals("ins_pzu")) {
            return insurancesManager.findAll();
        } else if (username.equals("ins_wrt")) {
            return insurancesManager2.findAll();
        }
        return new ArrayList<Insurance>();
    }

    public Boolean isCarplateInsured(long plateId) {
        Insurance i = insurancesManager.find(plateId);
        if (i == null) {
            i = insurancesManager2.find(plateId);
        }

        if (i == null) {
            System.err.println("is insured: " + plateId + ": false");
            return false;
        }
        System.err.println("is insured: " + plateId + ": " + i.isValid().toString());
        return i.isValid();
    }

    public String getRegistrationNumber(long plateId) {
        CarPlate carPlate = carPlatesManager.getCarPlateById(plateId);
        if (carPlate != null) {
            return carPlate.getRegistrationNumber();
        }
        return "-- NONE --";
    }

    public String getCurrentTime() {
        return new Timestamp(new Date().getTime()).toString();
    }
}
