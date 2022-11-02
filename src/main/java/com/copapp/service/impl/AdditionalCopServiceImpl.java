package com.copapp.service.impl;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.copapp.exception.ResourceNotFoundException;
import com.copapp.model.AdditionalCop;
import com.copapp.repo.AdditionalCopRepo;
import com.copapp.service.AdditionalCopService;

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
		Optional<AdditionalCop> additionalCopList=additionalCopRepository.findById(additionalCop.getAdditionalId());
		if(additionalCopList.isEmpty()) {
			throw new ResourceNotFoundException("Additional Cop doesn't exist with an id : "+additionalCop.getAdditionalId());
		}
		else {
		AdditionalCop i1=additionalCopList.get();
		additionalCop.setCreatedAt(i1.getCreatedAt());
		additionalCop.setMessageSend(i1.getMessageSend());
		}
		return this.additionalCopRepository.save(additionalCop);
	}

	@Override
	public Set<AdditionalCop> getAllAdditionalCop() {
		return new LinkedHashSet<>(this.additionalCopRepository.findAll());
	}

	@Override
	public AdditionalCop getAdditionalCop(Long additionalId) {
		Optional<AdditionalCop> additionalCopList=this.additionalCopRepository.findById(additionalId);
		if(additionalCopList.isEmpty()) {
			throw new ResourceNotFoundException("Additional Cop not found here with an id : "+additionalId);
		}
		else {
			return additionalCopList.get();
		}
	}

	@Override
	public void deleteAdditionalCop(Long additionalId) {
		Optional<AdditionalCop> additionalCopList=this.additionalCopRepository.findById(additionalId);
		if(additionalCopList.isEmpty()) {
			throw new ResourceNotFoundException("Additional Cop not found here with an id : "+additionalId);
		}
		else {
			this.additionalCopRepository.delete(additionalCopList.get());
		}
	}

	@Override
	public Set<AdditionalCop> getByStatus(String keyword) {
		return additionalCopRepository.findByStatus(keyword);
	}
	
}
