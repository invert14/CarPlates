package com.carplates.security;

import org.picketlink.idm.api.User;

/**
 * User: sebastianpawlak
 * Date: 12.03.2013
 */
public class UserImpl implements User {

    private String id;
    private String key;

    public UserImpl(String key, String id) {

        this.id = id;
        this.key = key;

    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getKey() {
        return key;
    }
}
