package com.dsv.authentication;

import com.dsv.authentication.domain.Permission;
import com.dsv.authentication.domain.Role;
import com.dsv.authentication.domain.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {


    @Override
    public void run(String... arguments) throws Exception {
        // Create Roles
        Set<Permission> permissions= new HashSet();
        Set<Role> roles= new HashSet();
        Permission permission= new Permission();
        permission.setPermissionName("ADD_SECURITY");
        permissions.add(permission);
        Role role= new Role();
        role.setRoleName("ROLE_ADMIN");
        role.setPermissions(permissions);
        User user= new User("aditya.gupta2", "aditya.gupta2@ihsmarkit.com", roles);


    }
}
