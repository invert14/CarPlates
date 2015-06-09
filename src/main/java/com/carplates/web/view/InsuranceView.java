package com.carplates.web.view;

import com.carplates.domain.Insurance;
import com.carplates.ejb.CarPlatesManager;
import com.carplates.ejb.InsurancesManager;
import com.carplates.web.view.session.UserSession;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

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
        if (!userSession.isInsurance()) {
            return new LinkedList<Insurance>();
        }
        return insurancesManager.findAll();
    }

    public Boolean isCarplateInsured(String carplate) {
        Insurance i = insurancesManager.find(carplate);
        if (i == null) {
            return false;
        }
        return i.isValid();
    }

    public String getCurrentTime() {
        return new Timestamp(new Date().getTime()).toString();
    }
}
