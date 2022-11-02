package com.copapp.service;

import java.util.Set;
import com.copapp.model.AdditionalCop;

public interface AdditionalCopService {

	public AdditionalCop addAdditionalCop(AdditionalCop additionalCop);

	public AdditionalCop updateAdditionalCop(AdditionalCop additionalCop);

	public Set<AdditionalCop> getAllAdditionalCop();

	public AdditionalCop getAdditionalCop(Long additionalId);

	void deleteAdditionalCop(Long additionalId);

	Set<AdditionalCop> getByStatus(String keyword);

}
