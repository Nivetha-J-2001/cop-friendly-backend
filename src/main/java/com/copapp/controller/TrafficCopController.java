package com.copapp.controller;

import java.util.HashSet;
import java.util.Set;

import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
public class TrafficCopController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
	 private JavaMailSender mailSender;

    @PostMapping("/trafficcop/signup")
    public User createUser(@RequestBody User user) throws Exception {
        Set<UserRole> roles = new HashSet<>();
        Role role = new Role();
        role.setRoleId(2L);
        role.setRoleName("TRAFFIC COP");
        
        try {
			MimeMessage mimeMessage=mailSender.createMimeMessage();
			MimeMessageHelper helper=new MimeMessageHelper(mimeMessage);
			helper.setFrom("copfriendly@gmail.com","Cop app Support");
			helper.setTo(user.getEmail());
			String subject="Login from Cop friendly app";
			
			String content = "<p>Hello "+user.getUser_name()+",<p>"+
			"<p>Please find your new auto generated password along with your details</p>"+
			"<p>User Name: "+user.getUser_name()+"</p>"+
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
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        UserRole userRole = new UserRole();
        userRole.setRole(role);
        userRole.setUser(user);
        roles.add(userRole);
        System.out.println("send");
        return this.userService.createUser(user, roles);
    }


}
