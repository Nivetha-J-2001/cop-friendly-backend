package com.copapp.service;
import java.util.Set;
import com.copapp.model.ViolationDetails;

public interface ViolationDetailsService {

	public ViolationDetails addViolationDetails(ViolationDetails violationDetails);

	public ViolationDetails updateViolationDetails(ViolationDetails violationDetails);

	public Set<ViolationDetails> getAllViolationDetails();

	public ViolationDetails getViolationDetails(Long violationId);

	public void deleteViolationDetails(Long violationId);

	public Set<ViolationDetails> getByLicenceNo(String keyword);

}
