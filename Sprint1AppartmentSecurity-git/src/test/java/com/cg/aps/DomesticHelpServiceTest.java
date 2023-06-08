package com.cg.aps;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.cg.aps.entities.DomesticHelp;
import com.cg.aps.entities.Flat;
import com.cg.aps.entities.Vehicle;
import com.cg.aps.exception.RecordNotFoundException;
import com.cg.aps.repository.DomesticHelpDAO;
import com.cg.aps.service.DomesticHelpService;
import com.cg.aps.service.DomesticHelpServiceImp;

@SpringBootTest
@ContextConfiguration(classes = Sprint1AppartmentSecurityApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DomesticHelpServiceTest {

	Logger logger = LoggerFactory.getLogger(DomesticHelpServiceTest.class);
	
	@Mock
	DomesticHelpDAO domesticHelpDAO;
	
	@InjectMocks
	DomesticHelpService service = new DomesticHelpServiceImp();
	
	List<DomesticHelp> list;
	DomesticHelp dHelp;
	
	@BeforeEach
	public void beforeTest() {
		list = new ArrayList<>();
//		v.setId("12");
//		v.setName("Raj");
		List<Flat> flats = new ArrayList<Flat>();
		Flat f1 = new Flat("kkk","12c","1234","9347854655");
		flats.add(f1);
//		v.setFlats(flats);
//		v.setDepartureTime(LocalTime.of(13,30,00));
//		v.setVisitorMobileNo("9347854655");
//		v.setArrivalTime(LocalTime.of(12,30,00));
		Vehicle vehicle = new Vehicle("11","AP123","2W");
		//v.setVehicle(vehicle);
		dHelp = new DomesticHelp("sweeping","12");

		list.add(dHelp);
	}

	@Test
	@Order(1)
	void testinsertDomesticHelpRecord() {
		logger.info("Testing Inserting DomesticHelpRecord");
		
		// When record is inserted then return list with that DomesticHelpRecord
		when(domesticHelpDAO.save(dHelp)).thenReturn(dHelp);
		when(domesticHelpDAO.findAll()).thenReturn(list);
		List<DomesticHelp> l1=service.save(dHelp);
		// service will call DomesticHelpDAO save method
		assertTrue(l1.size()>0);
		assertEquals(1,l1.size());
		//assertEquals("Raj",l1.get(0).getName());
		
		// verify that DomesticHelpDAO.save method got executed at least once
		verify(domesticHelpDAO,times(1)).save(dHelp);
		verify(domesticHelpDAO,times(1)).findAll();
	}
	
//	@Test
//	@Order(2)
//	void testupdateDomesticHelpRecord() {
//		logger.info("Testing Update DomesticHelpRecord");
//		
//		DomesticHelp p = list.get(0);
//		p.setName("jani");
//		list.set(0, p);
//
//		// When record is update then return list with updated DomesticHelpRecord
//		when(domesticHelpDAO.save(dHelp)).thenReturn(p);
//		when(domesticHelpDAO.findAll()).thenReturn(list);
//		
//		List<DomesticHelp> l1=service.updateDomesticHelpRecord(dHelp);
//		// service will call DomesticHelpDAO update
//		assertTrue(l1.size()>0);
//		assertEquals(1,l1.size());
//		assertEquals("jani",l1.get(0).getName());
//		
//		// verify that DomesticHelpDAO.update method got executed at least once
//		verify(domesticHelpDAO,times(1)).save(dHelp);
//		verify(domesticHelpDAO,times(1)).findAll();
//		
//	}
	@Test
	@Order(4)
	void testdeleteDomesticHelpRecord() {
		logger.info("Testing Delete DomesticHelpRecord");
		
		list.remove(dHelp);
		// When record is deleted then return list without that DomesticHelpRecord
		doNothing().when(domesticHelpDAO).deleteById("12");
		when(domesticHelpDAO.findAll()).thenReturn(list);
		
		List<DomesticHelp> l1=service.deleteDomesticHelpRecordById("12");
		// service will call DomesticHelpDAO delete 
		assertTrue(l1.size()==0);
		assertEquals(0,l1.size());
		assertThrows(IndexOutOfBoundsException.class, ()->l1.get(0));
		
		// verify that DomesticHelpDAO.delete method got executed at least once
		verify(domesticHelpDAO,times(1)).deleteById("12");
		verify(domesticHelpDAO,times(1)).findAll();
		
		
	}
	
	@Test
	@Order(3)
	void testfindDomesticHelpRecord() throws RecordNotFoundException {
		logger.info("Testing find DomesticHelpRecord by id");
		
		Optional<DomesticHelp> op=Optional.of(dHelp);
		// When record is findById then return  DomesticHelpRecord of id "4888765"
		if(op.isPresent()) {
			when(domesticHelpDAO.findById("12")).thenReturn(op);
		}
		
//		DomesticHelp p=service.findDomesticHelpRecordById("12");
//		logger.info(p.toString());
//		// service will call DomesticHelpDAO findById 
//		assertNotNull(p);
//		assertEquals("9347854655",p.getVisitorMobileNo());
//		assertEquals("Raj",p.getName());
//		assertThrows(RecordNotFoundException.class, ()->service.findDomesticHelpRecordById("488876577"));
		DomesticHelp dHelp1 = new DomesticHelp();
		assertThrows(RecordNotFoundException.class, ()->service.searchDomesticHelpRecord(dHelp1));
		
		
		// verify that domesticHelpDAO.findById(1001) method got executed at least once
		verify(domesticHelpDAO,times(1)).findById("12");
		
		
	}
	

}
