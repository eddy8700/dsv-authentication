package com.dsv.authentication.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @RequestMapping({ "/hello" })
    @PreAuthorize("hasPermission(#user,'VIEW_FUTURE_DOCUMENT')")
    public String firstPage(@RequestHeader("Authorization") String authenticationToken) {
        return "Hello World";
    }

}
