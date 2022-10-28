package com.copapp.controller;

import java.util.Set;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.copapp.exception.ResourceNotFoundException;
import com.copapp.model.AdditionalCop;
import com.copapp.service.AdditionalCopService;

@RestController
@CrossOrigin("*")
@RequestMapping("/additionalcop")
public class AdditionalCopController {
	
	@Autowired
	private AdditionalCopService additionalCopService;
	@Autowired
	 private JavaMailSender mailSender;
	 	
	 @PostMapping("/addadditionalcop")
	 public AdditionalCop addAdditionalCop(@RequestBody AdditionalCop additionalCop){
		 AdditionalCop additionalCop1=additionalCop;
		 try {
				MimeMessage mimeMessage=mailSender.createMimeMessage();
				MimeMessageHelper helper=new MimeMessageHelper(mimeMessage);
				helper.setFrom("copfriendly@gmail.com","Cop app Support");
				helper.setTo("nivethajayaraj2001@gmail.com");
				String subject="Emergency from Cop friendly app";
				
				String content = "<p>Hello,<p>"+
				"<p>We have requested to send the cop team to the location.</p>"+
						"<p>Here you find the location and no of peeople required</p>"+
				"<h1> "+"Location : <i>"+additionalCop1.getLocation()+"</i></h1>"+
				"<h1> "+"No of people Required : <i>"+additionalCop1.getNoOfRequired()+"</i></h1>"+
				"<h1> "+"Contact : <i>"+additionalCop1.getPhoneNumber()+"</i></h1>"+
				"<h1> "+"Priority : <i>"+additionalCop1.getPriority()+"</i></h1>";
				
				helper.setSubject(subject);
				helper.setText(content, true);
				
				mailSender.send(mimeMessage);
				additionalCop1.setMessageSend(content);

				System.out.println("Mail send successfully");
				return this.additionalCopService.addAdditionalCop(additionalCop1);
			}
			catch(Exception E)
			{
				throw new ResourceNotFoundException("Mail can't be send");
			}
	 }
	 
	 @GetMapping("/{additionalId}")
	 public AdditionalCop getAdditionalCop(@PathVariable("additionalId")Long additionalId) {
		 return this.additionalCopService.getAdditionalCop(additionalId);
	 }
	 
	 @GetMapping("/viewadditionalcop")
	 public Set<AdditionalCop> getAdditionalCop(){
		 return this.additionalCopService.getAllAdditionalCop();
	 }
	 
	 @PutMapping("/editadditionalcop")
	 public AdditionalCop updateAdditionalCop(@RequestBody AdditionalCop additionalCop) {
		 return this.additionalCopService.updateAdditionalCop(additionalCop);
		 
	 }
	 
	 @DeleteMapping("/deleteadditionalcop/{additionalId}")
	 public void deleteAdditionalCop(@PathVariable("additionalId")Long additionalId) {
		 this.additionalCopService.deleteAdditionalCop(additionalId);
	 }
	 
	 @GetMapping("/search/{keyword}")
	 public Set<AdditionalCop> search(@PathVariable(value = "keyword")String keyword) {
		   return this.additionalCopService.getByStatus(keyword);
	 }

}
