package com.copapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.copapp.exception.ResourceNotFoundException;
import com.copapp.model.MedicalEmergency;
import com.copapp.repo.MedicalEmergencyRepo;
import com.copapp.service.MedicalEmergencyService;

@Service 
public class MedicalEmergencyServiceImpl implements MedicalEmergencyService{
	
	@Autowired
	private MedicalEmergencyRepo medicalEmergencyRepository;
	
	@Override
	public MedicalEmergency addMedicalEmergency(MedicalEmergency medicalEmergency ) {
		medicalEmergency.setStatus("Applied");
		return this.medicalEmergencyRepository.save(medicalEmergency);
	}

	@Override
	public MedicalEmergency updateMedicalEmergency(MedicalEmergency medicalEmergency) {
		medicalEmergencyRepository.findById(medicalEmergency.getMedicalId()).orElseThrow(()->new ResourceNotFoundException("MedicalEmergency doesn't exist with an id : "+medicalEmergency.getMedicalId()));
		MedicalEmergency i1=this.medicalEmergencyRepository.findById(medicalEmergency.getMedicalId()).get();
		medicalEmergency.setCreatedAt(i1.getCreatedAt());
		return this.medicalEmergencyRepository.save(medicalEmergency);
	}

	@Override
	public List<MedicalEmergency> getAllMedicalEmergency() {
		return this.medicalEmergencyRepository.findAll();
	}

	@Override
	public MedicalEmergency getMedicalEmergency(Long medicalId) {
		this.medicalEmergencyRepository.findById(medicalId).orElseThrow(() -> new ResourceNotFoundException("MedicalEmergency not found here with an id : "+medicalId));
		return this.medicalEmergencyRepository.findById(medicalId).get();
	}

	@Override
	public void deleteMedicalEmergency(Long medicalId) {
		MedicalEmergency local=this.medicalEmergencyRepository.findById(medicalId).orElseThrow(() -> new ResourceNotFoundException("MedicalEmergency not found with an id : "+medicalId));
		this.medicalEmergencyRepository.delete(local);
	}

	@Override
	public List<MedicalEmergency> getByStatus(String keyword) {
		return medicalEmergencyRepository.findByStatus(keyword);
	}
	

}
