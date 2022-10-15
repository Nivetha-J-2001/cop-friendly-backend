package com.copapp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.copapp.model.MedicalEmergency;

public interface MedicalEmergencyRepo extends JpaRepository<MedicalEmergency,Long> {

	@Query(value = "select * from medical_emergency where status like CONCAT('%',:keyword, '%') or priority like CONCAT('%',:keyword, '%')", nativeQuery = true)	
	public List<MedicalEmergency> findByStatus(@Param("keyword") String keyword);

}
