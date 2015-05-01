package com.carplates.web.view.session;

import com.carplates.domain.Tenant;
import com.carplates.web.qualifier.Active;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import java.io.Serializable;

/**
 * User: sebastianpawlak
 * Date: 01.03.2013
 */

@SessionScoped
public class TenantSession implements Serializable{

    private Tenant tenant = new Tenant("GSP");

    @Produces
    @Active
    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }
}
