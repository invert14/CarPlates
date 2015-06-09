package com.carplates.security;

import com.carplates.web.view.session.UserSession;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import org.jboss.seam.security.Identity;
import org.jboss.seam.security.annotations.Secures;

/**
 * User: sebastianpawlak Date: 05.06.2013
 */
public class RoleChecker {

    private transient final Logger logger = Logger.getLogger(RoleChecker.class);

    @Inject
    private UserSession userSession;

    public @Secures
    @Local
    boolean localCheck(Identity identity) {

        if (identity == null || identity.getUser() == null) {

            return false;

        } else {

            return userSession.isLocal();
        }

    }

    public @Secures
    @Global
    boolean globalCheck(Identity identity) {

        if (identity == null || identity.getUser() == null) {

            return false;

        } else {
            return userSession.isGlobal();
        }

    }

    public @Secures
    @InsuranceRole
    boolean insuranceCheck(Identity identity) {

        if (identity == null || identity.getUser() == null) {

            return false;

        } else {
            return userSession.isInsurance();
        }

    }
}
