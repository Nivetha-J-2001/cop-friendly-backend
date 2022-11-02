package com.copapp.service.impl;

import java.util.List;
import java.util.Optional;

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
		Optional<MedicalEmergency> medicalEmergencyList=this.medicalEmergencyRepository.findById(medicalEmergency.getMedicalId());
		if(medicalEmergencyList.isEmpty()) {
			throw new ResourceNotFoundException("MedicalEmergency doesn't exist with an id : "+medicalEmergency.getMedicalId());
		}
		else {
			MedicalEmergency i1=medicalEmergencyList.get();
			medicalEmergency.setCreatedAt(i1.getCreatedAt());
			medicalEmergency.setMessageSend(i1.getMessageSend());
		}
		return this.medicalEmergencyRepository.save(medicalEmergency);
	}

	@Override
	public List<MedicalEmergency> getAllMedicalEmergency() {
		return this.medicalEmergencyRepository.findAll();
	}

	@Override
	public MedicalEmergency getMedicalEmergency(Long medicalId) {
		Optional<MedicalEmergency> medicalEmergencyList=this.medicalEmergencyRepository.findById(medicalId);
		if(medicalEmergencyList.isEmpty()) {
			throw new ResourceNotFoundException("MedicalEmergency not found here with an id : "+medicalId);
		}
		else {
			return medicalEmergencyList.get();
		}
	}

	@Override
	public void deleteMedicalEmergency(Long medicalId) {
		Optional<MedicalEmergency> medicalEmergencyList=this.medicalEmergencyRepository.findById(medicalId);
		if(medicalEmergencyList.isEmpty()) {
			throw new ResourceNotFoundException("MedicalEmergency not found with an id : "+medicalId);
		}
		else {
			this.medicalEmergencyRepository.delete(medicalEmergencyList.get());
		}
	}

	@Override
	public List<MedicalEmergency> getByStatus(String keyword) {
		return medicalEmergencyRepository.findByStatus(keyword);
	}
	

}
