package com.copapp.service;

import java.util.List;
import java.util.Set;

import com.copapp.model.MedicalEmergency;

public interface MedicalEmergencyService {
	
	public MedicalEmergency addMedicalEmergency(MedicalEmergency MedicalEmergency);
	
	public MedicalEmergency updateMedicalEmergency(MedicalEmergency MedicalEmergency);
	
	public List<MedicalEmergency> getAllMedicalEmergency();
	
	public MedicalEmergency getMedicalEmergency(Long MedicalEmergencyId);
	
	public void deleteMedicalEmergency(Long MedicalEmergencyId);

	public List<MedicalEmergency> getByStatus(String keyword);
	
	

}
