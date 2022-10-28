package com.copapp.service.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.copapp.exception.ResourceNotFoundException;
import com.copapp.model.AdditionalCop;
import com.copapp.service.AdditionalCopService;
import com.copapp.repo.AdditionalCopRepo;

@Service 
public class AdditionalCopServiceImpl implements AdditionalCopService {
	@Autowired
	private AdditionalCopRepo additionalCopRepository;
	
	@Override
	public AdditionalCop addAdditionalCop(AdditionalCop additionalCop ) {
		additionalCop.setStatus("Applied");
		return this.additionalCopRepository.save(additionalCop);
	}

	@Override
	public AdditionalCop updateAdditionalCop(AdditionalCop additionalCop) {
		additionalCopRepository.findById(additionalCop.getAdditionalId()).orElseThrow(()->new ResourceNotFoundException("Additional Cop doesn't exist with an id : "+additionalCop.getAdditionalId()));
		AdditionalCop i1=this.additionalCopRepository.findById(additionalCop.getAdditionalId()).get();
		additionalCop.setCreatedAt(i1.getCreatedAt());
		additionalCop.setMessageSend(i1.getMessageSend());
		return this.additionalCopRepository.save(additionalCop);
	}

	@Override
	public Set<AdditionalCop> getAllAdditionalCop() {
		return new LinkedHashSet<>(this.additionalCopRepository.findAll());
	}

	@Override
	public AdditionalCop getAdditionalCop(Long additionalId) {
		this.additionalCopRepository.findById(additionalId).orElseThrow(() -> new ResourceNotFoundException("Additional Cop not found here with an id : "+additionalId));
		return this.additionalCopRepository.findById(additionalId).get();
	}

	@Override
	public void deleteAdditionalCop(Long additionalId) {
		AdditionalCop local=this.additionalCopRepository.findById(additionalId).orElseThrow(() -> new ResourceNotFoundException("AdditionalCop not found with an id : "+additionalId));
		this.additionalCopRepository.delete(local);
	}

	@Override
	public Set<AdditionalCop> getByStatus(String keyword) {
		return additionalCopRepository.findByStatus(keyword);
	}
	
}
