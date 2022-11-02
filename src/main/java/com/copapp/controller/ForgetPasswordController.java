package com.copapp.controller;
import java.util.Random;

import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.copapp.exception.InvalidCredentialsException;
import com.copapp.exception.ResourceNotFoundException;
import com.copapp.model.User;
import com.copapp.repo.UserRepository;
import com.copapp.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("")
public class ForgetPasswordController {
	
	public static final Logger log=Logger.getLogger(ForgetPasswordController.class);
	
	private Random rand = new Random();  // SecureRandom is preferred to Random

	@Autowired
	private UserService userService;
		
	@Autowired
	private UserRepository userRepository;
	
	 @Autowired
	 private JavaMailSender mailSender;
	 	 
	@PostMapping("/forgetpassword/{email}")
	public void sendEmail(@PathVariable("email") String email	)
	{
		User user=this.userRepository.findUserByEmail(email);
		if( user != null)
		{  
			int otp = rand.nextInt(999999);
			log.info("OTP : "+otp);
			try {
				MimeMessage mimeMessage=mailSender.createMimeMessage();
				MimeMessageHelper helper=new MimeMessageHelper(mimeMessage);
				helper.setFrom("copfriendly@gmail.com","Cop app Support");
				helper.setTo(email);
				log.info("OTP : "+otp);
				String subject="OTP from Cop friendly app";
				
				String content = "<p>Hello "+user.getName()+",<p>"+
				"<p>You have requested to reset your password.</p>"+
						"<p>Here you find the otp below to change the password:</p>"+
				"<h1> "+otp+"</h1>"+
				"<p>**Ignore this email if you do remember your password, or you have not made the request.**</p> ";
				
				helper.setSubject(subject);
				helper.setText(content, true);
				
				mailSender.send(mimeMessage);
				log.info("Mail send Successfully");
				user.setOtp(Integer.toString(otp));
				this.userRepository.save(user);
								
			}
			catch(Exception E)
			{
				throw new ResourceNotFoundException("Mail can't be send");
			}			
		}
		else
		{
			throw new ResourceNotFoundException("Email does not exist !!"); 
		}
		
	}
	
	@PostMapping("/verifyotp/{email}/{otp}")
	public void verfyingotp(@PathVariable("otp") String otp,@PathVariable("email") String email)
	{
		User user=this.userRepository.findUserByEmail(email);
		String myotp=user.getOtp();
		if(myotp.equals(otp))
		{
			user.setOtp("0");
			this.userRepository.save(user);
			log.info("Verified OTP !!");
		}
		else
		{
			throw new InvalidCredentialsException("Invalid OTP !!");
		}
	}
	
	@PostMapping("/resetpassword/{email}/{password}")
	public void changePassword(@PathVariable("password") String password,@PathVariable("email") String email )
	{		
		this.userService.updatePassword(email, password);
	}
	
	@GetMapping("/email/{email}")
    public User getUser(@PathVariable("email") String email) {
        return this.userService.getUser(email);

    }
}
