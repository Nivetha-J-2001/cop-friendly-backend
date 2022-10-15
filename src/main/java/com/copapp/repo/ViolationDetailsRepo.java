package com.copapp.repo;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.copapp.model.ViolationDetails;

public interface ViolationDetailsRepo extends JpaRepository<ViolationDetails,Long>{

	@Query(value = "select * from violation_details where licence_no like CONCAT('%',:keyword, '%')", nativeQuery = true)
	public Set<ViolationDetails> findByLicenceNo(String keyword);

}
