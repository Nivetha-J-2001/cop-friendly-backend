package com.copapp.controller;


import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.copapp.configure.JwtUtils;
import com.copapp.exception.InvalidCredentialsException;
import com.copapp.exception.ResourceNotFoundException;
import com.copapp.model.JwtRequest;
import com.copapp.model.JwtResponse;
import com.copapp.model.User;
import com.copapp.service.UserService;
import com.copapp.service.impl.UserDetailsServiceImpl;


@CrossOrigin("*")
@RequestMapping("/role")
@RestController
public class AuthenticateController {
    
	@Autowired
	private UserService userService;
	
	@Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;
    
    @Autowired
    private JwtUtils jwtUtils;
//    public Long signin(@RequestBody User user) throws Exception{
//    	return this.userService.loginUser(user.getEmail(), user.getPassword());
//    }

    @CrossOrigin("*")
    @PostMapping("/signin")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
        
//      try {
          
          authenticate(jwtRequest.getEmail(), jwtRequest.getPassword());
//      } catch (Exception e) {
//          e.printStackTrace();
//          String exc=e.toString();
//          throw new ResourceNotFoundException(exc);
//      }
     

      UserDetails userDetails=this.userDetailsServiceImpl.loadUserByUsername(jwtRequest.getEmail());
      String token = this.jwtUtils.generateToken(userDetails);   
      User user=userService.getUser(jwtRequest.getEmail());
      return ResponseEntity.ok(new JwtResponse(token,userDetails.getUsername(),userDetails.getAuthorities(),user.getId()));
  }
    
    private void authenticate(String email,String password) throws Exception{
       
        try {
            
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password));
        } catch (DisabledException e) {
             throw new ResourceNotFoundException("Email not found ");
        }
        catch(BadCredentialsException e){
            throw new InvalidCredentialsException("Invalid Credentials ");
        }

    }
    @GetMapping("/current-user")
    public User getCurrentUser(Principal principal){
        return (User)this.userDetailsServiceImpl.loadUserByUsername(principal.getName());
    }
   
}
