package com.copapp.service;

import java.util.Set;

import com.copapp.model.ViolationDetails;

public interface ViolationDetailsService {

	public ViolationDetails addViolationDetails(ViolationDetails ViolationDetails);

	public ViolationDetails updateViolationDetails(ViolationDetails ViolationDetails);

	public Set<ViolationDetails> getAllViolationDetails();

	public ViolationDetails getViolationDetails(Long violationId);

	public void deleteViolationDetails(Long violationId);

	public Set<ViolationDetails> getByLicenceNo(String keyword);

}
