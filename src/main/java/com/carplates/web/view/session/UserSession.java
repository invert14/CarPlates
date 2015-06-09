package com.carplates.web.view.session;

import com.carplates.domain.User;
import com.carplates.domain.UserRoleType;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * User: sebastianpawlak Date: 05.06.2013
 */
@SessionScoped
@Named
public class UserSession implements Serializable {

    private User loggedInUser;

    public boolean isUserLoggedIn() {
        return loggedInUser != null;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public boolean isLocal() {

        if (!isUserLoggedIn()) {
            return false;
        }

        return loggedInUser.getUserRole().equals(UserRoleType.LOCAL);

    }

    public boolean isGlobal() {

        if (!isUserLoggedIn()) {
            return false;
        }

        return loggedInUser.getUserRole().equals(UserRoleType.GLOBAL);

    }

    public boolean isInsurance() {

        if (!isUserLoggedIn()) {
            return false;
        }

        return loggedInUser.getUserRole().equals(UserRoleType.INSURANCE);
    }
}
