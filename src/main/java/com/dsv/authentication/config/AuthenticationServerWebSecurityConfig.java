package com.dsv.authentication.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthenticationServerWebSecurityConfig extends JsonWebTokenSecurityConfig {

    @Override
    protected void setupAuthorization(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/authenticate").permitAll()
                .anyRequest().authenticated();
    }
}

