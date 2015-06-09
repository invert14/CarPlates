package com.carplates.web.filter;

import com.carplates.domain.Tenant;
import com.carplates.web.view.session.TenantSession;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;
import javax.enterprise.inject.Instance;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.jboss.seam.security.IdentityImpl;

/**
 * User: sebastianpawlak Date: 01.03.2013
 */
//@WebFilter(filterName = "TenantLoginFilter", urlPatterns = {"/login/*"})
public class TenantLoginFilter implements Filter {

    Logger logger = Logger.getLogger(TenantLoginFilter.class);

    //@Inject @Possible
    private List<Tenant> possibleTenants;

    //@Inject
    private Instance<TenantSession> tenantSession;

    //@Inject
    private Instance<IdentityImpl> identity;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        logger.info(request.getContextPath());
        logger.info(request.getServletPath());
        logger.info(request.getRequestURI());

        Tenant tenantFromURI = getTenantFromURI(request.getRequestURI(), request.getContextPath());

        if (!possibleTenants.contains(tenantFromURI) || identity.get().isLoggedIn()) {
            response.sendError(404);
            return;
        }

        tenantSession.get().setTenant(tenantFromURI);

        String uri = removeTenantFromURI(request.getContextPath(), request.getServletPath(), tenantFromURI);

        logger.info(uri);

        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(uri);

        dispatcher.forward(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

    private Tenant getTenantFromURI(String uri, String contextPath) {

        StringTokenizer st = new StringTokenizer(uri, "/");
        String contextPathWithoutSlash = contextPath.replaceAll("/", "");

        while (st.hasMoreElements()) {
            String element = st.nextToken();

            if (contextPathWithoutSlash.equals(element)) {

                if (st.hasMoreElements()) {
                    return new Tenant(st.nextToken());
                }
                return null;
            }
        }

        return null;
    }

    private String removeTenantFromURI(String contextPath, String servletPath, Tenant tenant) {

        String result = servletPath;

        result = result.replaceAll("/" + tenant.getName(), "");

        return result;
    }

}
