
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

import com.cg.aps.entities.Flat;
import com.cg.aps.entities.Vehicle;

import com.cg.aps.entities.Visitor;
import com.cg.aps.exception.RecordNotFoundException;
import com.cg.aps.repository.VisitorDAO;
import com.cg.aps.service.VisitorService;
import com.cg.aps.service.VisitorServiceImp;


@SpringBootTest
@ContextConfiguration(classes = Sprint1AppartmentSecurityApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class VisitorServiceTest {
	
	Logger logger = LoggerFactory.getLogger(VisitorServiceTest.class);
	
	@Mock
	VisitorDAO visitorDAO;
	
	@InjectMocks
	VisitorService service = new VisitorServiceImp();
	
	List<Visitor> vList;
	Visitor visitor;
	
	@BeforeEach
	public void beforeTest() {
		vList = new ArrayList<>();
		List<Flat> flats = new ArrayList<Flat>();
		Flat f1 = new Flat("kkk","12c","1234","9347854655");
		flats.add(f1);
		Vehicle vehicle = new Vehicle("11","AP123","2W");
		visitor = new Visitor("12","9347854655","Raj",LocalTime.of(12,30,00),LocalTime.of(13,30,00),LocalDate.of(2023, 3, 31),"f1","v1");
		vList.add(visitor);
	}

	@Test
	@Order(1)
	void testinsertVisitor() {
		logger.info("Testing Inserting Visitor");
		
		// When record is inserted then return list with that visitor
		when(visitorDAO.save(visitor)).thenReturn(visitor);
		when(visitorDAO.findAll()).thenReturn(vList);
		List<Visitor> l1=service.save(visitor);
		// service will call visitorDAO save method
		assertTrue(l1.size()>0);
		assertEquals(1,l1.size());
		assertEquals("Raj",l1.get(0).getName());
		
		// verify that visitorDAO.save method got executed at least once
		verify(visitorDAO,times(1)).save(visitor);
		verify(visitorDAO,times(1)).findAll();
	}
	
	@Test
	@Order(2)
	void testupdateVisitor() {
		logger.info("Testing Update Visitor");
		
		Visitor p = vList.get(0);
		p.setName("jani");
		vList.set(0, p);

		// When record is update then return list with updated visitor
		when(visitorDAO.save(visitor)).thenReturn(p);
		when(visitorDAO.findAll()).thenReturn(vList);
		
		List<Visitor> l1=service.update(visitor);
		// service will call visitorDAO update
		assertTrue(l1.size()>0);
		assertEquals(1,l1.size());
		assertEquals("jani",l1.get(0).getName());
		
		// verify that visitorDAO.update method got executed at least once
		verify(visitorDAO,times(1)).save(visitor);
		verify(visitorDAO,times(1)).findAll();
		
	}
	@Test
	@Order(4)
	void testdeleteVisitor() {
		logger.info("Testing Delete Visitor");
		
		vList.remove(visitor);
		// When record is deleted then return list without that Visitor
		doNothing().when(visitorDAO).deleteById("12");
		when(visitorDAO.findAll()).thenReturn(vList);
		
		List<Visitor> l1=service.deleteById("12");
		// service will call VisitorDAO delete 
		assertTrue(l1.size()==0);
		assertEquals(0,l1.size());
		assertThrows(IndexOutOfBoundsException.class, ()->l1.get(0));
		
		// verify that VisitorDao.delete method got executed at least once
		verify(visitorDAO,times(1)).deleteById("12");
		verify(visitorDAO,times(1)).findAll();
		
		
	}
	
	@Test
	@Order(3)
	void testfindVisitor() throws RecordNotFoundException {
		logger.info("Testing find Visitor by id");
		
		Optional<Visitor> op=Optional.of(visitor);
		// When record is findById then return  Guard of id 1001
		if(op.isPresent()) {
			when(visitorDAO.findById("12")).thenReturn(op);
		}
		
		Visitor p=service.findById("12");
		logger.info(p.toString());
		// service will call VisitorDAO findById 
		assertNotNull(p);
		assertEquals("9347854655",p.getVisitorMobileNo());
		assertEquals("Raj",p.getName());
		assertThrows(RecordNotFoundException.class, ()->service.findById("1347854655"));
		
		
		// verify that VisitorDAO.findById(1001) method got executed at least once
		verify(visitorDAO,times(1)).findById("12");
		
		
	}
	
}
