package com.copapp.controller;
import java.util.Set;

import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
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
	
	public static final Logger log=Logger.getLogger(ViolationDetailsController.class);
	@Autowired
	private ViolationDetailsService violationDetailsService;
	
	@Autowired
	 private JavaMailSender mailSender;
	 
	 @PostMapping("/addviolationdetails")
	 public ViolationDetails addViolationDetails(@RequestBody ViolationDetails violationDetails){
		 
		 if(violationDetails.getFineAmount()>0 && violationDetails.getPaymentStatus().equalsIgnoreCase("success"))
		 {
			 try {
				 MimeMessage mimeMessage=mailSender.createMimeMessage();
					MimeMessageHelper helper=new MimeMessageHelper(mimeMessage);
					helper.setFrom("copfriendly@gmail.com","Cop app Support");
					helper.setTo(violationDetails.getMailId());
					String subject="fine from Cop friendly app";
					
					String content = "<p>Hello "+violationDetails.getName()+",<p>"+
					"<p>We have registered violation on you!</p>"+
							"<p>The violation details are given below,</p>"+
					"<p>Violation Type : "+violationDetails.getViolationType()+"</p>"+
					"<p>Vehicle Type : "+violationDetails.getVehicleType()+"</p>"+
					"<p>Location : "+violationDetails.getLocation()+"</p>"+
					"<p>Fine Amount : "+violationDetails.getFineAmount()+"</p>"+
					"<p>Payment Status : "+violationDetails.getPaymentStatus()+"</p>"+
					"<p>Date : "+violationDetails.getDate()+"</p>";
					
					
					helper.setSubject(subject);
					helper.setText(content, true);
					
					mailSender.send(mimeMessage);
					violationDetails.setMessageSend(content);

					log.info("Mail send successfully");
					this.violationDetailsService.addViolationDetails(violationDetails);
				}
				catch(Exception E)
				{
					throw new ResourceNotFoundException("Mail can't be send");
				}			
			 
		 }
		 return this.violationDetailsService.addViolationDetails(violationDetails);
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
	 public ViolationDetails updateViolationDetails(@RequestBody ViolationDetails violationDetails) {
		 return this.violationDetailsService.updateViolationDetails(violationDetails);
	 }
	 
	 @DeleteMapping("/deleteviolationdetails/{violationId}")
	 public void deleteViolationDetails(@PathVariable("violationId")Long violationId) {
		 this.violationDetailsService.deleteViolationDetails(violationId);
	 }
	 
	 @GetMapping("/search/{keyword}")
	 public Set<ViolationDetails> search(@PathVariable(value = "keyword", required = false)String keyword) {
		 return this.violationDetailsService.getByLicenceNo(keyword);
	}
}
