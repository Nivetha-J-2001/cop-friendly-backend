package com.copapp.repo;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.copapp.model.AdditionalCop;


public interface AdditionalCopRepo extends JpaRepository<AdditionalCop,Long> {

	@Query(value = "select * from additional_cop where status like CONCAT('%',:keyword, '%') or priority like CONCAT('%',:keyword, '%')", nativeQuery = true)	
	public Set<AdditionalCop> findByStatus(String keyword);

}
