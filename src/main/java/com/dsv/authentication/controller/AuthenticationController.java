package com.dsv.authentication.controller;

import com.dsv.authentication.domain.*;
import com.dsv.authentication.service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class AuthenticationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);


    @Autowired
    private AuthenticationService authenticationService;


    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public AuthTokenDTO createAuthenticationToken(@RequestBody final JwtRequest jwtRequest) {
        Set<Permission> permissions= new HashSet();
        Set<Role> roles= new HashSet();
        Permission permission= new Permission();
        permission.setPermissionName("VIEW_FUTURE_DOCUMENT");
        permissions.add(permission);
        Role role= new Role();
        role.setRoleName("ROLE_ADMIN");
        role.setPermissions(permissions);
        roles.add(role);
        final User user = new User(jwtRequest.getUsername(), jwtRequest.getUsername()+"@ihsmarkit.com", roles);
        LOGGER.info("Trying to authenticate user={}",jwtRequest.getUsername());

        return authenticationService.buildJwtToken(user, user.getRoles().stream().map(r -> r.getRoleName()).collect(Collectors.toList()), role.getPermissions().stream().map(r -> r.getPermissionName())
                .collect(Collectors.toList()));

    }



}
