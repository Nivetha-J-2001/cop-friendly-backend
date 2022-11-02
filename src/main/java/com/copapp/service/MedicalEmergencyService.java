package com.copapp.service;

import java.util.List;

import com.copapp.model.MedicalEmergency;

public interface MedicalEmergencyService {
	
	public MedicalEmergency addMedicalEmergency(MedicalEmergency medicalEmergency);
	
	public MedicalEmergency updateMedicalEmergency(MedicalEmergency medicalEmergency);
	
	public List<MedicalEmergency> getAllMedicalEmergency();
	
	public MedicalEmergency getMedicalEmergency(Long medicalEmergencyId);
	
	public void deleteMedicalEmergency(Long medicalEmergencyId);

	public List<MedicalEmergency> getByStatus(String keyword);
	
	

}
