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

import com.copapp.model.AdditionalCop;
import com.copapp.repo.AdditionalCopRepo;
import com.copapp.service.impl.AdditionalCopServiceImpl;

@SpringBootTest(classes= {AdditionalCopServiceTest.class})
class AdditionalCopServiceTest {

	@Mock
	AdditionalCopRepo additionalCopRepo;
	
	@InjectMocks
	AdditionalCopServiceImpl additionalCopService;
	

	@Test
	void test_GetAllAdditionalCop() {
		List<AdditionalCop> records = new ArrayList<>();
		records.add(new AdditionalCop(1L, 4, "Applied", "Nivetha J", "6369369678","Paramathi","Low"));
		records.add(new AdditionalCop(2L, 4, "Send", "Thirilosana J", "9369369678","Velur","High"));
		records.add(new AdditionalCop(3L, 4, "Rejected", "Bharath J", "8869369678","Mohanur","Moderate"));
		when(additionalCopRepo.findAll()).thenReturn(records);
		assertEquals(3,additionalCopService.getAllAdditionalCop().size());
		
	}
	
	@Test
	void test_addAdditionalCop() {
		AdditionalCop a1=(new AdditionalCop(1L, 4, "Applied", "Nivetha J", "6369369678","Paramathi","Low"));
		when(additionalCopRepo.save(a1)).thenReturn(a1);
		assertEquals(a1,additionalCopService.addAdditionalCop(a1));
	}
	
	@Test
	void test_deleteAdditionalCop() {
		AdditionalCop a1=(new AdditionalCop(1L, 4, "Applied", "Nivetha J", "6369369678","Paramathi","Low"));
		additionalCopRepo.delete(a1);
		assertNotEquals(0,additionalCopService.getAllAdditionalCop().size());
		
	}
}
