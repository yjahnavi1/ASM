package com.cg.aps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

//import static org.junit.jupiter.api.Assertions.*;

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
//import com.cg.aps.ctrl.GuardController;
import com.cg.aps.entities.Guard;
import com.cg.aps.exception.RecordNotFoundException;
import com.cg.aps.repository.GuardDAO;
import com.cg.aps.service.GuardService;
import com.cg.aps.service.GuardServiceImp;


@SpringBootTest
@ContextConfiguration(classes = Sprint1AppartmentSecurityApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GuardServiceTest {
	
	Logger logger = LoggerFactory.getLogger(GuardServiceTest.class);
	
	@Mock
	GuardDAO guardDAO;
	
	@InjectMocks
	GuardService service = new GuardServiceImp();
	
	List<Guard> gList;
	Guard guard;
	
	@BeforeEach
	public void beforeTest() {
		gList = new ArrayList<>();
		guard = new Guard("1001","Jhon","9347854655",LocalTime.of(12,30,00),LocalDate.of(2023, 3, 31));
		gList.add(guard);
	}

	@Test
	@Order(1)
	void testinsertGuard() {
		logger.info("Testing Inserting Guard");
		
		// When record is inserted then return list with that guard
		when(guardDAO.save(guard)).thenReturn(guard);
		when(guardDAO.findAll()).thenReturn(gList);
		List<Guard> l1=service.save(guard);
		// service will call guardDAO save method
		assertTrue(l1.size()>0);
		assertEquals(1,l1.size());
		assertEquals("Jhon",l1.get(0).getGuardName());
		
		// verify that guardDAO.save method got executed at least once
		verify(guardDAO,times(1)).save(guard);
		verify(guardDAO,times(1)).findAll();
	}
	
	@Test
	@Order(2)
	void testupdateGuard() {
		logger.info("Testing Update Guard");
		
		Guard p = gList.get(0);
		p.setGuardName("jani");
		gList.set(0, p);

		// When record is update then return list with updated guard
		when(guardDAO.save(guard)).thenReturn(p);
		when(guardDAO.findAll()).thenReturn(gList);
		
		List<Guard> l1=service.update(guard);
		// service will call guardDAO update
		assertTrue(l1.size()>0);
		assertEquals(1,l1.size());
		assertEquals("jani",l1.get(0).getGuardName());
		
		// verify that guardDAO.update method got executed at least once
		verify(guardDAO,times(1)).save(guard);
		verify(guardDAO,times(1)).findAll();
		
	}
	@Test
	@Order(4)
	void testdeleteGuard() {
		logger.info("Testing Delete Guard");
		
		gList.remove(guard);
		// When record is deleted then return list without that Guard
		doNothing().when(guardDAO).deleteById("9347854655");
		when(guardDAO.findAll()).thenReturn(gList);
		
		List<Guard> l1=service.deleteById("9347854655");
		// service will call GuardDAO delete 
		assertTrue(l1.size()==0);
		assertEquals(0,l1.size());
		assertThrows(IndexOutOfBoundsException.class, ()->l1.get(0));
		
		// verify that GuardDao.delete method got executed at least once
		verify(guardDAO,times(1)).deleteById("9347854655");
		verify(guardDAO,times(1)).findAll();
		
		
	}
	
	@Test
	@Order(3)
	void testfindGuard() throws RecordNotFoundException {
		logger.info("Testing find Guard by id");
		
		Optional<Guard> op=Optional.of(guard);
		// When record is findById then return  Guard of id 1001
		if(op.isPresent()) {
			when(guardDAO.findById("9347854655")).thenReturn(op);
		}
		
		Guard p=service.findGuard("9347854655");
		logger.info(p.toString());
		// service will call GuardDAO findById 
		assertNotNull(p);
		assertEquals("9347854655",p.getGuardMobileNo());
		assertEquals("Jhon",p.getGuardName());
		assertThrows(RecordNotFoundException.class, ()->service.findGuard("934785465599"));
		
		
		// verify that guardDAO.findById(1001) method got executed at least once
		verify(guardDAO,times(1)).findById("9347854655");
		
		
	}
	
}
