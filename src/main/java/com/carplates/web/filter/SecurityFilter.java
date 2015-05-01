package com.carplates.web.filter;

import com.carplates.domain.Tenant;
import com.carplates.web.qualifier.Active;
import com.carplates.web.qualifier.Possible;
import org.apache.log4j.Logger;
import org.jboss.seam.security.Identity;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

/**
 * User: sebastianpawlak
 * Date: 01.03.2013
 */

//@WebFilter(filterName = "SecurityFilter", urlPatterns = {"/*"})
public class SecurityFilter implements Filter {

    Logger logger = Logger.getLogger(SecurityFilter.class);

    //@Inject @Possible
    private List<Tenant> possibleTenants;

    //@Inject @Active
    private Instance<Tenant> activeTenant;

    //@Inject
    private Instance<Identity> identity;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        if( ( (HttpServletRequest) req).getRequestURI().contains("login.xhtml")) {
            logger.info("login page -> doFilter");
            chain.doFilter(req, resp);
            return;
        }

        if(identity.get().isLoggedIn()) {
            logger.info("user logged in -> doFilter");
            chain.doFilter(req, resp);
            return;
        }

        logger.info("403");
        ( (HttpServletResponse) resp).sendError(403);
    }

    public void init(FilterConfig config) throws ServletException {

    }


}
