package com.copapp.controller;

import java.util.HashSet;
import java.util.Set;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.copapp.exception.ResourceNotFoundException;
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
    
    @Autowired
	 private JavaMailSender mailSender;
  
    @PostMapping("/trafficcentral/signup") 
    public User createUser(@RequestBody User user) throws Exception{
       
            user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
            Set<UserRole> roles=new HashSet<>();
            Role role=new Role();
            role.setRoleId(1L);
            role.setRoleName("TRAFFIC CENTRAL");
            try {
				MimeMessage mimeMessage=mailSender.createMimeMessage();
				MimeMessageHelper helper=new MimeMessageHelper(mimeMessage);
				helper.setFrom("copfriendly@gmail.com","Cop app Support");
				helper.setTo(user.getEmail());
				String subject="Login from Cop friendly app";
				
				String content = "<p>Hello "+user.getUsername()+",<p>"+
				"<p>Please find your new auto generated password along with your details</p>"+
				"<p>User Name: "+user.getUsername()+"</p>"+
				"<p>Email: "+user.getEmail()+"</p>"+
				"<p>Mobile Number: "+user.getMobileNo()+"</p>"+
				"<p>Password: "+user.getPassword()+"</p>"+
				"<p>**If there is any correction, please contact the central team**</p>"+
				"<P><b>Regards,</b></p>"+
				"<p>Central Team</p>";
				
				helper.setSubject(subject);
				helper.setText(content, true);
				
				mailSender.send(mimeMessage);
				System.out.println("Mail send Successfully");
				user.setMessageSend(content);
			}
			catch(Exception E)
			{
				throw new ResourceNotFoundException("Mail can't be send");
			}			
            UserRole userRole=new UserRole();
            userRole.setRole(role);
            userRole.setUser(user);
            roles.add(userRole);
            return this.userService.createUser(user, roles);
   }
   

    
}
