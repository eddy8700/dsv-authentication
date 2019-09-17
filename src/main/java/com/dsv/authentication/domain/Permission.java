package com.dsv.authentication.domain;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

public class Permission implements Serializable, GrantedAuthority {

    private Long id;

    private String permissionName;

    @Override
    public String getAuthority() {
        return permissionName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }
}
