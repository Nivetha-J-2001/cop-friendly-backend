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
import com.copapp.model.ViolationDetails;
import com.copapp.service.ViolationDetailsService;

@RestController
@CrossOrigin("*")
@RequestMapping("/violationdetails")
public class ViolationDetailsController {
	
	@Autowired
	private ViolationDetailsService violationDetailsService;
	
	@Autowired
	 private JavaMailSender mailSender;
	 
	 @PostMapping("/addviolationdetails")
	 public ViolationDetails addViolationDetails(@RequestBody ViolationDetails ViolationDetails){
		 
		 if(ViolationDetails.getFineAmount()>0 && ViolationDetails.getPaymentStatus().equalsIgnoreCase("success"))
		 {
			 try {
				 MimeMessage mimeMessage=mailSender.createMimeMessage();
					MimeMessageHelper helper=new MimeMessageHelper(mimeMessage);
					helper.setFrom("copfriendly@gmail.com","Cop app Support");
					helper.setTo(ViolationDetails.getMailId());
					String subject="fine from Cop friendly app";
					
					String content = "<p>Hello "+ViolationDetails.getName()+",<p>"+
					"<p>We have registered the violation on you!</p>"+
							"<p>Here you can find the violation details below,</p>"+
					"<p>Violation Type : "+ViolationDetails.getViolationType()+"</p>"+
					"<p>Vehicle Type : "+ViolationDetails.getVehicleType()+"</p>"+
					"<p>Location : "+ViolationDetails.getLocation()+"</p>"+
					"<p>Fine Amount : "+ViolationDetails.getFineAmount()+"</p>"+
					"<p>Payment Status : "+ViolationDetails.getPaymentStatus()+"</p>"+
					"<p>Date : "+ViolationDetails.getDate()+"</p>";
					
					
					helper.setSubject(subject);
					helper.setText(content, true);
					
					mailSender.send(mimeMessage);
					ViolationDetails.setMessageSend(content);

					System.out.println("Mail send successfully");
					this.violationDetailsService.addViolationDetails(ViolationDetails);
				}
				catch(Exception E)
				{
					throw new ResourceNotFoundException("Mail can't be send");
				}			
			 
		 }
		 return this.violationDetailsService.addViolationDetails(ViolationDetails);
	 }
	 
	 @GetMapping("/{violationId}")
	 public ViolationDetails getViolationDetails(@PathVariable("violationId")Long violationId) {
		 return this.violationDetailsService.getViolationDetails(violationId);
	 }
	 
	 @GetMapping("/viewviolationdetails")
	 public Set<ViolationDetails> getViolationDetailss(){
		 return this.violationDetailsService.getAllViolationDetails();
	 }
	 
	 @PutMapping("/editviolationdetails")
	 public ViolationDetails updateViolationDetails(@RequestBody ViolationDetails ViolationDetails) {
		 return this.violationDetailsService.updateViolationDetails(ViolationDetails);
	 }
	 
	 @DeleteMapping("/deleteviolationdetails/{violationId}")
	 public void deleteViolationDetails(@PathVariable("violationId")Long violationId) {
		 this.violationDetailsService.deleteViolationDetails(violationId);
	 }
	 
	 @GetMapping("/search/{keyword}")
	 public Set<ViolationDetails> search(@PathVariable(value = "keyword", required = false)String keyword,ViolationDetails ViolationDetails) {
		 Set<ViolationDetails> list = this.violationDetailsService.getByLicenceNo(keyword);
		 return list;	
	}
}
