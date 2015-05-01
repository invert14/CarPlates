package com.carplates.security;

import java.io.Serializable;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.jboss.seam.security.Authenticator;
import org.jboss.seam.security.BaseAuthenticator;
import org.jboss.seam.security.CredentialsImpl;

import com.carplates.domain.Tenant;
import com.carplates.ejb.UsersManager;
import com.carplates.web.view.session.UserSession;

public class CarPlatesAuthenticator extends BaseAuthenticator implements Authenticator, Serializable {
    private static final Logger logger = Logger.getLogger(CarPlatesAuthenticator.class);

    @Inject
    private CredentialsImpl credentials;

    @Inject
    private UsersManager usersManager;

    @Inject
    private UserSession userSession;

    private static final String LOGIN = "spawlak";
    private static final String PASS = "spawlak";
    private static final Tenant TENANT = new Tenant("GSP");

    @Override
    public void authenticate() {
        String username;
        String password;
        try {
            username = credentials.getUsername();
            password = credentials.getPassword();

            logger.info("Try to login with username "+username);

            com.carplates.domain.User user = usersManager.getUserByUsernameAndPassword(username, password);

            if(user != null) {

                logger.info("Logged in: "+username);

                setUser(new UserImpl(username, user.getId().toString()));

                setStatus(AuthenticationStatus.SUCCESS);
                userSession.setLoggedInUser(user);
                return;
            }

            setStatus(AuthenticationStatus.FAILURE);

        } catch(Exception e) {
            setStatus(AuthenticationStatus.FAILURE);

        }
    }
}