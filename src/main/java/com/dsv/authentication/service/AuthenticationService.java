package com.dsv.authentication.service;


import com.dsv.authentication.domain.AuthTokenDTO;
import com.dsv.authentication.domain.User;
import com.dsv.authentication.util.JWTTokenBuilder;
import com.dsv.authentication.util.JsonWebTokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationService {

    @Autowired
    private JsonWebTokenUtility jsonWebTokenUtility;

    public AuthTokenDTO buildJwtToken(final User user, final List<String> userRoles,
                                       final List<String> userPermissions) {
        // build the token
        final JWTTokenBuilder jwtTokenDTO = new JWTTokenBuilder(user.getUsername(), user.getUserEmail(), userRoles,
                userPermissions, buildExpirationTime());
        final String jwtToken = jsonWebTokenUtility.createJsonWebToken(jwtTokenDTO);
        final AuthTokenDTO authTokenDTO = new AuthTokenDTO(jwtToken);
        return authTokenDTO;
    }

    private Long buildExpirationTime() {
        //As of now token expiration time is current time + 1 min
        return System.currentTimeMillis() + 60000;
    }
}
