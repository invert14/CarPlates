package com.carplates.web.view;

import com.carplates.domain.Owner;
import com.carplates.ejb.GlobalManager;
import com.carplates.ejb.OwnersManager;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 * User: sebastianpawlak
 * Date: 24.05.2013
 */

@RequestScoped
@FacesConverter("owner.global.converter")
public class GlobalOwnerConverter implements Converter {

    @Inject
    private GlobalManager globalManager;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {

        try {
        	String[] elements = s.split(";");
        	Long id = new Long(elements[0]);
            return globalManager.getOwnerByIdAndRegion(id, elements[1]);
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {

        try {
            return ((Owner) o).getGlobalId();
        } catch (Exception e) {
            return null;
        }



    }
}
