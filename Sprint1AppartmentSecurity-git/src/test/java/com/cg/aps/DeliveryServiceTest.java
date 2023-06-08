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

import com.cg.aps.entities.Delivery;
import com.cg.aps.entities.Flat;
import com.cg.aps.entities.Vehicle;
import com.cg.aps.exception.RecordNotFoundException;
import com.cg.aps.repository.DeliveryDAO;
import com.cg.aps.service.DeliveryService;
import com.cg.aps.service.DeliveryServiceImp;

@SpringBootTest
@ContextConfiguration(classes = Sprint1AppartmentSecurityApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DeliveryServiceTest {

Logger logger = LoggerFactory.getLogger(GuardServiceTest.class);
	
	@Mock
	DeliveryDAO deliveryDAO;
	
	@InjectMocks
	DeliveryService service = new DeliveryServiceImp();
	
	List<Delivery> dList;
	Delivery delivery;
	
	@BeforeEach
	public void beforeTest() {
		dList = new ArrayList<>();
		List<Flat> flats = new ArrayList<Flat>();
		Flat f1 = new Flat("kkk","12c","1234","9347854655");
		flats.add(f1);
		Vehicle vehicle = new Vehicle("11","AP123","2W");
		//v.setVehicle(vehicle);
		delivery = new Delivery("swiggy","12");
		dList.add(delivery);
	}
	@Test
	@Order(1)
	void testinsertDeliveryRecord() {
		logger.info("Testing Inserting DeliveryRecord");
		
		// When record is inserted then return list with that DomesticHelpRecord
		when(deliveryDAO.save(delivery)).thenReturn(delivery);
		when(deliveryDAO.findAll()).thenReturn(dList);
		List<Delivery> l1=service.save(delivery);
		// service will call DomesticHelpDAO save method
		assertTrue(l1.size()>0);
		assertEquals(1,l1.size());
		//assertEquals("Raj",l1.get(0).getName());
		
		// verify that DomesticHelpDAO.save method got executed at least once
		verify(deliveryDAO,times(1)).save(delivery);
		verify(deliveryDAO,times(1)).findAll();
	}
	
//	@Test
//	@Order(2)
//	void testupdateDeliveryRecord() {
//		logger.info("Testing Update DeliveryRecord");
//		
//		Delivery p = dList.get(0);
//		p.setName("jani");
//		dList.set(0, p);
//
//		// When record is update then return list with updated DomesticHelpRecord
//		when(deliveryDAO.save(delivery)).thenReturn(p);
//		when(deliveryDAO.findAll()).thenReturn(dList);
//		
//		List<Delivery> l1=service.update(delivery);
//		// service will call DomesticHelpDAO update
//		assertTrue(l1.size()>0);
//		assertEquals(1,l1.size());
//		assertEquals("jani",l1.get(0).getName());
//		
//		// verify that DomesticHelpDAO.update method got executed at least once
//		verify(deliveryDAO,times(1)).save(delivery);
//		verify(deliveryDAO,times(1)).findAll();
//		
//	}
	@Test
	@Order(4)
	void testdeleteDomesticHelpRecord() {
		logger.info("Testing Delete DomesticHelpRecord");
		
		dList.remove(delivery);
		// When record is deleted then return list without that DomesticHelpRecord
		doNothing().when(deliveryDAO).deleteById("12");
		when(deliveryDAO.findAll()).thenReturn(dList);
		
		List<Delivery> l1=service.deleteById("12");
		// service will call DomesticHelpDAO delete 
		assertTrue(l1.size()==0);
		assertEquals(0,l1.size());
		assertThrows(IndexOutOfBoundsException.class, ()->l1.get(0));
		
		// verify that DomesticHelpDAO.delete method got executed at least once
		verify(deliveryDAO,times(1)).deleteById("12");
		verify(deliveryDAO,times(1)).findAll();
		
		
	}
	
//	@Test
//	@Order(3)
//	void testfindDomesticHelpRecord() throws RecordNotFoundException {
//		logger.info("Testing find DomesticHelpRecord by id");
//		
//		Optional<Delivery> op=Optional.of(delivery);
//		// When record is findById then return  DomesticHelpRecord of id "4888765"
//		if(op.isPresent()) {
//			when(deliveryDAO.findById("12")).thenReturn(op);
//		}
//		
//		Delivery p=service.findDeliveryById("12");
//		logger.info(p.toString());
//		// service will call DomesticHelpDAO findById 
//		assertNotNull(p);
//		assertEquals("9347854655",p.getVisitorMobileNo());
//		assertEquals("Raj",p.getName());
//		assertThrows(RecordNotFoundException.class, ()->service.findDeliveryById("488876577"));
//		Delivery dHelp1 = new Delivery();
//		assertThrows(RecordNotFoundException.class, ()->service.search(dHelp1));
//		
//		
//		// verify that domesticHelpDAO.findById(1001) method got executed at least once
//		verify(deliveryDAO,times(1)).findById("12");
//		
//		
//	}
	


}
