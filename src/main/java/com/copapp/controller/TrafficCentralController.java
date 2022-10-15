package com.copapp.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.copapp.model.Role;
import com.copapp.model.User;
import com.copapp.model.UserRole;
import com.copapp.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("")
public class TrafficCentralController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
  
    @PostMapping("/trafficcentral/signup") 
    public User createUser(@RequestBody User user) throws Exception{
       
            user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
            Set<UserRole> roles=new HashSet<>();
            Role role=new Role();
            role.setRoleId(1L);
            role.setRoleName("TRAFFIC CENTRAL");

            UserRole userRole=new UserRole();
            userRole.setRole(role);
            userRole.setUser(user);
            roles.add(userRole);
            return this.userService.createUser(user, roles);
   }
   

    
}
