package com.carplates.web.view;

import com.carplates.domain.CarPlate;
import com.carplates.domain.Insurance;
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
import com.carplates.ejb.CarPlatesManager;
import com.carplates.ejb.GlobalManager;
import com.carplates.ejb.InsurancesManager;
import com.carplates.ejb.OwnersManager;
import com.carplates.ejb.PenaltiesManager;
import com.carplates.web.view.session.UserSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class InsuranceView implements Serializable {

    @Inject
    private InsurancesManager insurancesManager;
    
    @Inject
    private CarPlatesManager carPlatesManager;
    
    @Inject
    private UserSession userSession;

    
    public List<Insurance> getInsurances() {
        if (!userSession.isInsurance()) 
            return new LinkedList<Insurance>();
        return insurancesManager.findAll();
    }
    
    public String getRegistrationNumber(long plateId) {
        CarPlate carPlate = carPlatesManager.getCarPlateById(plateId);
        if (carPlate != null)
            return carPlate.getRegistrationNumber();
        return "-- NONE --";
    }
    
    public String getCurrentTime() {
        return new Timestamp(new Date().getTime()).toString();
    }
}
