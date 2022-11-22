package com.copapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.copapp.model.ViolationDetails;
import com.copapp.repo.ViolationDetailsRepo;
import com.copapp.service.impl.ViolationDetailsServiceImpl;

@SpringBootTest(classes= {ViolationDetailsServiceTest.class})
class ViolationDetailsServiceTest {
	
	@Mock
	ViolationDetailsRepo violationDetailsRepo;
	
	@InjectMocks
	ViolationDetailsServiceImpl violationDetailsService;
	
	
	@Test
	void test_GetAllViolationDetails() {
		List<ViolationDetails> records = new ArrayList<>();
		records.add(new ViolationDetails(1L,"Nivetha J","AA1234567890000","Accident","tn 28 a 1234","Namakkal","nivethajayaraj2001@gmail.com","9874563215","13/11/2022","2:00pm",400L,"cash","success"));
		records.add(new ViolationDetails(2L,"Monika K", "BB8745914758452","Accident","tn 28 a 8745","Mohanur","monikamur23102000@gmail.com","6369874526","30/11/2022","5:00pm",0L,"",""));
		records.add(new ViolationDetails(3L,"Monika K", "BB8745914758452","Accident","tn 28 a 8745","Mohanur","monikamur23102000@gmail.com","6369874526","30/11/2022","5:00pm",800L,"cash","failure"));

		when(violationDetailsRepo.findAll()).thenReturn(records);
		assertEquals(3,violationDetailsService.getAllViolationDetails().size());
		
	}
	
	@Test
	void test_addViolationDetails() {
		ViolationDetails v1=new ViolationDetails(3L,"Monika K", "BB8745914758452","Accident","tn 28 a 8745","Mohanur","monikamur23102000@gmail.com","6369874526","30/11/2022","5:00pm",800L,"cash","failure");

		when(violationDetailsRepo.save(v1)).thenReturn(v1);
		assertEquals(v1,violationDetailsService.addViolationDetails(v1));
	}
	
	@Test
	void test_deleteViolationDetails() {
		ViolationDetails v1=new ViolationDetails(3L,"Monika K", "BB8745914758452","Accident","tn 28 a 8745","Mohanur","monikamur23102000@gmail.com","6369874526","30/11/2022","5:00pm",800L,"cash","failure");
		violationDetailsRepo.delete(v1);
		assertNotEquals(0,violationDetailsService.getAllViolationDetails().size());
		
	}
	
}