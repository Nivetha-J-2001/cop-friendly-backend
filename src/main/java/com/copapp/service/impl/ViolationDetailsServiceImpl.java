package com.copapp.service.impl;

import java.util.LinkedHashSet;
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
	public ViolationDetails addViolationDetails(ViolationDetails ViolationDetails ) {
		return this.violationDetailsRepository.save(ViolationDetails);
	}

	@Override
	public ViolationDetails updateViolationDetails(ViolationDetails ViolationDetails) {
		violationDetailsRepository.findById(ViolationDetails.getViolationId()).orElseThrow(()->new ResourceNotFoundException("ViolationDetails doesnot exist !!"));
		ViolationDetails i1=violationDetailsRepository.findById(ViolationDetails.getViolationId()).get();
		ViolationDetails.setCreatedAt(i1.getCreatedAt());
		return this.violationDetailsRepository.save(ViolationDetails);
	}

	@Override
	public Set<ViolationDetails> getAllViolationDetails() {
		// TODO Auto-generated method stub
		return new LinkedHashSet<>(this.violationDetailsRepository.findAll());
	}

	@Override
	public ViolationDetails getViolationDetails(Long ViolationDetailsId) {
		// TODO Auto-generated method stub
		this.violationDetailsRepository.findById(ViolationDetailsId).orElseThrow(() -> new ResourceNotFoundException("ViolationDetails not found here "));
		return this.violationDetailsRepository.findById(ViolationDetailsId).get();
	}

	@Override
	public void deleteViolationDetails(Long ViolationDetailsId) {
		// TODO Auto-generated method stub
		ViolationDetails local=this.violationDetailsRepository.findById(ViolationDetailsId).orElseThrow(() -> new ResourceNotFoundException("ViolationDetails not found"));
		this.violationDetailsRepository.delete(local);
	}

	@Override
	public Set<ViolationDetails> getByLicenceNo(String keyword) {
		// TODO Auto-generated method stub
		return violationDetailsRepository.findByLicenceNo(keyword);
	}
	

}

