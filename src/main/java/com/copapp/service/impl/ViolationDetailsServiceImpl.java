package com.copapp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.copapp.exception.ResourceNotFoundException;
import com.copapp.model.ViolationDetails;
import com.copapp.repo.ViolationDetailsRepo;
import com.copapp.service.ViolationDetailsService;

@Service 
public class ViolationDetailsServiceImpl implements ViolationDetailsService{
	
	@Autowired
	private ViolationDetailsRepo violationDetailsRepository;
	
	@Override
	public ViolationDetails addViolationDetails(ViolationDetails violationDetails ) {
		return this.violationDetailsRepository.save(violationDetails);
	}

	@Override
	public ViolationDetails updateViolationDetails(ViolationDetails violationDetails) {
		Optional<ViolationDetails> violationList=this.violationDetailsRepository.findById(violationDetails.getViolationId());
		if(violationList.isEmpty()) {
			throw new ResourceNotFoundException("ViolationDetails doesnot exist !!");
		}
		else {
			ViolationDetails i1=violationList.get();
			violationDetails.setCreatedAt(i1.getCreatedAt());
			violationDetails.setMessageSend(i1.getMessageSend());
		}
		return this.violationDetailsRepository.save(violationDetails);
	}

	@Override
	public List<ViolationDetails> getAllViolationDetails() {
		return new ArrayList<>(this.violationDetailsRepository.findAll());
	}

	@Override
	public ViolationDetails getViolationDetails(Long violationDetailsId) {
		Optional<ViolationDetails> violationList=this.violationDetailsRepository.findById(violationDetailsId);
		if(violationList.isEmpty()) {
			throw new ResourceNotFoundException("ViolationDetails not found");
		}
		else {
			return violationList.get();
		}
	}

	@Override
	public void deleteViolationDetails(Long violationDetailsId) {
		Optional<ViolationDetails> violationList=this.violationDetailsRepository.findById(violationDetailsId);
		if(violationList.isEmpty()) {
			throw new ResourceNotFoundException("ViolationDetails not found");
		}
		else {
			this.violationDetailsRepository.delete(violationList.get());
		}
	}

	@Override
	public Set<ViolationDetails> getByLicenceNo(String keyword) {

		return violationDetailsRepository.findByLicenceNo(keyword);
	}
	

}

