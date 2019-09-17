package com.dsv.authentication.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class User implements UserDetails {

    private static final long serialVersionUID = 5962377075810731122L;

    private long id;

    private String userEmail;


    private String userName;

    private Set<Role> roles;

    private Set<Permission> permissions;

    @Override
    public String toString() {
        String rolesString = "";
        if (roles != null) {
            for (Role role : roles) {
                rolesString += role.toString();
            }
        }

        return String.format("User[id=%d, email=%s, roles=%s]", id, userEmail, rolesString);
    }

    public Set<Permission> getPermissions() {
        Set<Permission> perms = new HashSet<Permission>();
        for (Role role : roles) {
            perms.addAll(role.getPermissions());
        }
        return perms;
    }

    public User(final String userName,final String userEmail,final Set<Role> authorities){
        this.userName=userName;
        this.userEmail=userEmail;
        this.roles=authorities;

    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        authorities.addAll(getRoles());
        authorities.addAll(getPermissions());
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.userEmail; // as of now email is the username
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}