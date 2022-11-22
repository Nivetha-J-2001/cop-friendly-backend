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

import com.copapp.model.MedicalEmergency;
import com.copapp.repo.MedicalEmergencyRepo;
import com.copapp.service.impl.MedicalEmergencyServiceImpl;

@SpringBootTest(classes= {MedicalEmergencyServiceTest.class})
class MedicalEmergencyServiceTest {
	@Mock
	MedicalEmergencyRepo medicalEmergencyCopRepo;
	
	@InjectMocks
	MedicalEmergencyServiceImpl medicalEmergencyService;
	

	@Test
	void test_GetAllAdditionalCop() {
		List<MedicalEmergency> records = new ArrayList<>();
		records.add(new MedicalEmergency(1L, 4, "Applied", "Nivetha J", "6369369678","Paramathi","Low"));
		records.add(new MedicalEmergency(2L, 4, "Send", "Thirilosana J", "9369369678","Velur","High"));
		records.add(new MedicalEmergency(3L, 4, "Rejected", "Bharath J", "8869369678","Mohanur","Moderate"));
		when(medicalEmergencyCopRepo.findAll()).thenReturn(records);
		assertEquals(3,medicalEmergencyService.getAllMedicalEmergency().size());
		
	}
	
	@Test
	void test_addAdditionalCop() {
		MedicalEmergency m1=(new MedicalEmergency(1L, 4, "Applied", "Nivetha J", "6369369678","Paramathi","Low"));
		when(medicalEmergencyCopRepo.save(m1)).thenReturn(m1);
		assertEquals(m1,medicalEmergencyService.addMedicalEmergency(m1));
	}
	
	@Test
	void test_deleteAdditionalCop() {
		MedicalEmergency m1=(new MedicalEmergency(1L, 4, "Applied", "Nivetha J", "6369369678","Paramathi","Low"));
		medicalEmergencyCopRepo.delete(m1);
		assertNotEquals(0,medicalEmergencyService.getAllMedicalEmergency().size());
		
	}
}
