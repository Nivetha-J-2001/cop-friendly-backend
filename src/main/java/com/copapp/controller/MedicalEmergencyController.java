package com.copapp.controller;

import java.util.List;

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
import com.copapp.model.MedicalEmergency;
import com.copapp.service.MedicalEmergencyService;


@RestController
@CrossOrigin("*")
@RequestMapping("/medicalemergency")
public class MedicalEmergencyController {
	
	public static final Logger log=Logger.getLogger(MedicalEmergencyController.class);
	@Autowired
	private MedicalEmergencyService medicalEmergencyService;
	
	@Autowired
	 private JavaMailSender mailSender;
	 	
	 @PostMapping("/addmedicalemergency")
	 public MedicalEmergency addMedicalEmergency(@RequestBody MedicalEmergency medicalEmergency){
		 try {
				MimeMessage mimeMessage=mailSender.createMimeMessage();
				MimeMessageHelper helper=new MimeMessageHelper(mimeMessage);
				helper.setFrom("copfriendly@gmail.com","Cop app Support");
				helper.setTo("nivethajayaraj2001@gmail.com");
				String subject="Emergency from Cop friendly app";
				
				String content = "<p>Hello Team,<p>"+
				"<p>We have requested to send the medical team to the location.</p>"+
						"<p>Here you find the location and no of peeople affected</p>"+
				"<h1><p> "+"Location : "+medicalEmergency.getLocation()+"</p>"+
				"<p>No of people Affected : "+medicalEmergency.getNoOfAffected()+"</p>"+
				"<p>Name : "+medicalEmergency.getName()+"</p>"+
				"<p>Contact : "+medicalEmergency.getPhoneNumber()+"</p>"+
				"<p>Priority : "+medicalEmergency.getPriority()+"</p>"+"</h1>";
				
				helper.setSubject(subject);
				helper.setText(content, true);
				
				mailSender.send(mimeMessage);
				medicalEmergency.setMessageSend(content);

				log.info("Mail send successfully");
				return this.medicalEmergencyService.addMedicalEmergency(medicalEmergency);
			}
			catch(Exception E)
			{
				throw new ResourceNotFoundException("Mail can't be send");
			}			
		 
	 }
	 
	 @GetMapping("/{medicalId}")
	 public MedicalEmergency getMedicalEmergency(@PathVariable("medicalId")Long medicalId) {
		 return this.medicalEmergencyService.getMedicalEmergency(medicalId);
	 }
	 
	 @GetMapping("/viewmedicalemergencies")
	 public List<MedicalEmergency> getMedicalEmergencys(){
		 return this.medicalEmergencyService.getAllMedicalEmergency();
	 }
	 
	 @PutMapping("/editmedicalemergency")
	 public MedicalEmergency updateMedicalEmergency(@RequestBody MedicalEmergency medicalEmergency) {
		 
		 return this.medicalEmergencyService.updateMedicalEmergency(medicalEmergency);
	 }
	 
	 @DeleteMapping("/deletemedicalemergency/{medicalId}")
	 public void deleteMedicalEmergency(@PathVariable("medicalId")Long medicalId) {
		 this.medicalEmergencyService.deleteMedicalEmergency(medicalId);
	 }
	 
	 @GetMapping("/search/{keyword}")
	 public List<MedicalEmergency> search(@PathVariable(value = "keyword")String keyword) {
		   return this.medicalEmergencyService.getByStatus(keyword);
	 }

}
