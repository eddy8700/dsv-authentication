package com.dsv.authentication.util;

import java.util.List;

public class JWTTokenBuilder {

    private String userName;

    private String userEmail;

    private List<String> roles;

    private List<String> permissions;

    private Long expirationTimeInMillis;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public Long getExpirationTimeInMillis() {
        return expirationTimeInMillis;
    }

    public void setExpirationTimeInMillis(Long expirationTimeInMillis) {
        this.expirationTimeInMillis = expirationTimeInMillis;
    }

    @Override
    public String toString() {
        return "JWTTokenBuilder{" +
                "userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", roles=" + roles +
                ", permissions=" + permissions +
                ", expirationTimeInMillis=" + expirationTimeInMillis +
                '}';
    }

    public JWTTokenBuilder(String userName, String userEmail, List<String> roles, List<String> permissions, Long expirationTimeInMillis) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.roles = roles;
        this.permissions = permissions;
        this.expirationTimeInMillis = expirationTimeInMillis;
    }
}
