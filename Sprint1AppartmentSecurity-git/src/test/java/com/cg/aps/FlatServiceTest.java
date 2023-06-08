package com.cg.aps;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

//import com.cg.aps.ctrl.FlatController;
import com.cg.aps.entities.Flat;
import com.cg.aps.exception.RecordNotFoundException;
import com.cg.aps.repository.FlatDAO;
import com.cg.aps.service.FlatService;
import com.cg.aps.service.FlatServiceImp;
@SpringBootTest
@ContextConfiguration(classes = Sprint1AppartmentSecurityApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class FlatServiceTest {
Logger logger = LoggerFactory.getLogger(FlatServiceTest.class);
	
	@Mock
	FlatDAO flatDAO;
	
	@InjectMocks
	FlatService service = new FlatServiceImp();
	
	List<Flat> fList;
	Flat flat;
	
	@BeforeEach
	public void beforeTest() {
		fList = new ArrayList<>();
		flat = new Flat("12","Jhon","201","9347854655");
		fList.add(flat);
	}

	@Test
	@Order(1)
	void testinsertFlat() {
		logger.info("Testing Inserting Flat");
		
		// When record is inserted then return list with that flat
		when(flatDAO.save(flat)).thenReturn(flat);
		when(flatDAO.findAll()).thenReturn(fList);
		List<Flat> l1=service.save(flat);
		// service will call flatDAO save method
		assertTrue(l1.size()>0);
		assertEquals(1,l1.size());
		assertEquals("Jhon",l1.get(0).getOwnerName());
		
		// verify that flatDAO.save method got executed at least once
		verify(flatDAO,times(1)).save(flat);
		verify(flatDAO,times(1)).findAll();
	}
	
	@Test
	@Order(2)
	void testupdateFlat() {
		logger.info("Testing Update Flat");
		
		Flat p = fList.get(0);
		p.setOwnerName("jani");
		fList.set(0, p);

		// When record is update then return list with updated flat
		when(flatDAO.save(flat)).thenReturn(p);
		when(flatDAO.findAll()).thenReturn(fList);
		
		List<Flat> l1=service.update(flat);
		// service will call flatDAO update
		assertTrue(l1.size()>0);
		assertEquals(1,l1.size());
		assertEquals("jani",l1.get(0).getOwnerName());
		
		// verify that flatDAO.update method got executed at least once
		verify(flatDAO,times(1)).save(flat);
		verify(flatDAO,times(1)).findAll();
		
	}
	@Test
	@Order(4)
	void testdeleteFlat() {
		logger.info("Testing Delete Flat");
		
		fList.remove(flat);
		// When record is deleted then return list without that Flat
		doNothing().when(flatDAO).deleteById("12");
		when(flatDAO.findAll()).thenReturn(fList);
		
		List<Flat> l1=service.deleteById("12");
		// service will call FlatDAO delete 
		assertTrue(l1.size()==0);
		assertEquals(0,l1.size());
		assertThrows(IndexOutOfBoundsException.class, ()->l1.get(0));
		
		// verify that FlatDao.delete method got executed at least once
		verify(flatDAO,times(1)).deleteById("12");
		verify(flatDAO,times(1)).findAll();
		
		
	}
	
	@Test
	@Order(3)
	void testfindFlat() throws RecordNotFoundException {
		logger.info("Testing find Flat by id");
		
		Optional<Flat> op=Optional.of(flat);
		// When record is findById then return  Flat of id 1001
		if(op.isPresent()) {
			when(flatDAO.findById("12")).thenReturn(op);
		}
		
		Flat p=service.findFlat("12");
		logger.info(p.toString());
		// service will call FlatDAO findById 
		assertNotNull(p);
		assertEquals("12",p.getFlatId());
		assertEquals("Jhon",p.getOwnerName());
		assertThrows(RecordNotFoundException.class, ()->service.findFlat("123"));
		Flat flat1 = new Flat();
		assertThrows(RecordNotFoundException.class, ()->service.search(flat1));		
		
		// verify that flatDAO.findById(1001) method got executed at least once
		verify(flatDAO,times(1)).findById("12");
		
		
	}
	
}
