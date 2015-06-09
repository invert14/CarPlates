package com.carplates.web.view;

import com.carplates.domain.Owner;
import com.carplates.ejb.OwnersManager;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 * User: sebastianpawlak Date: 24.05.2013
 */
@RequestScoped
@FacesConverter("owner.converter")
public class OwnerConverter implements Converter {

    @Inject
    private OwnersManager ownersManager;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {

        try {
            Long id = new Long(s);
            return ownersManager.getOwnerById(id);
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {

        try {
            return Long.toString(((Owner) o).getId());
        } catch (Exception e) {
            return null;
        }

    }
}
