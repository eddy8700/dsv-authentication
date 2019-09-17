package com.dsv.authentication.domain;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Set;

public class Role implements Serializable, GrantedAuthority {
    private Long id;

    private String roleName;

    @Override
    public String getAuthority() {
        return roleName;
    }

    private Set<Permission> permissions;

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }
}
