package com.carplates.security;

import org.jboss.seam.faces.security.AccessDeniedView;
import org.jboss.seam.faces.security.LoginView;
import org.jboss.seam.faces.view.config.ViewConfig;
import org.jboss.seam.faces.view.config.ViewPattern;

/**
 * User: sebastianpawlak Date: 09.03.2013
 */
@ViewConfig
public interface ViewAuthorization {

    static enum Access {

        //@FacesRedirect
        @ViewPattern("/local/*")
        @LoginView("/login/login.xhtml")
        @AccessDeniedView("/common/access-denied.xhtml")
        @Local
        LOCAL,
        //@FacesRedirect
        @ViewPattern("/global/*")
        @LoginView("/login/login.xhtml")
        @AccessDeniedView("/common/access-denied.xhtml")
        @Global
        GLOBAL,
        //@FacesRedirect
        @ViewPattern("/insurance/*")
        @LoginView("/login/login.xhtml")
        @AccessDeniedView("/common/access-denied.xhtml")
        @InsuranceRole
        INSURANCE,
    }
}
