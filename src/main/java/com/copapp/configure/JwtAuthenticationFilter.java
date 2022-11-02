package com.copapp.configure;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.copapp.service.impl.UserDetailsServiceImpl;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	public static final Logger log=Logger.getLogger(JwtAuthenticationFilter.class);
	
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;
   
    @Autowired
    private JwtUtils jwtUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
       
       final String requestTokenHeader=request.getHeader("Authorization");
     
       String email=null;
       String jwtToken=null;
        
       if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer ")){
                
           jwtToken=requestTokenHeader.substring(7);
           try {
            email=this.jwtUtil.extractEmail(jwtToken);
               
           } catch (Exception e) {
               e.printStackTrace();
               log.error("Token Expired");
           }
       }
       else{
    	   log.error("Invalid Token, does not start with Bearer string");
       }

       if(email!=null && SecurityContextHolder.getContext().getAuthentication()==null){
           final UserDetails userDetails=this.userDetailsServiceImpl.loadUserByUsername(email);
           if(Boolean.TRUE.equals(this.jwtUtil.validateToken(jwtToken, userDetails))){
            UsernamePasswordAuthenticationToken usernamePasswordAuthentication=   new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
            usernamePasswordAuthentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthentication);
           }
        }
        else{
        	log.error("Invalid Token");
        }
        filterChain.doFilter(request, response);
    }
    
}
