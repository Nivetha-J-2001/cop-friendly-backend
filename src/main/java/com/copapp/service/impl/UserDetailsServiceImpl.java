package com.copapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.copapp.exception.ResourceNotFoundException;
import com.copapp.model.User;
import com.copapp.repo.UserRepository;


@Service 
public class UserDetailsServiceImpl implements UserDetailsService{


    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        
        User user=this.userRepository.findUserByEmail(email);
       if(user!=null){
    	   return user;
       }else {
    	   throw new ResourceNotFoundException("Email not found !!");
       }
    }
    
}
