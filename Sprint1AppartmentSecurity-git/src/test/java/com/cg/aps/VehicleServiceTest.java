package com.cg.aps;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

import com.cg.aps.entities.Vehicle;
import com.cg.aps.exception.RecordNotFoundException;
import com.cg.aps.repository.VehicleDAO;
import com.cg.aps.service.VehicleService;
import com.cg.aps.service.VehicleServiceImp;

@SpringBootTest
@ContextConfiguration(classes = Sprint1AppartmentSecurityApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class VehicleServiceTest {

	Logger logger = LoggerFactory.getLogger(VehicleServiceTest.class);
	
	@Mock
	VehicleDAO vehicleDAO;
	
	@InjectMocks
	VehicleService service = new VehicleServiceImp();
	
	List<Vehicle> vlist;
	Vehicle vehicle;
	
	@BeforeEach
	public void beforeTest() {
		vlist = new ArrayList<>();
		vehicle = new Vehicle("11","AP123","2W");
		vlist.add(vehicle);
	}

	@Test
	@Order(1)
	void testinsertVehicle() {
		logger.info("Testing Inserting Vehicle");
		
		// When record is inserted then return list with that VehicleRecord
		when(vehicleDAO.save(vehicle)).thenReturn(vehicle);
		when(vehicleDAO.findAll()).thenReturn(vlist);
		List<Vehicle> l1=service.save(vehicle);
		// service will call VehicleDAO save method
		assertTrue(l1.size()>0);
		assertEquals(1,l1.size());
		assertEquals("AP123",l1.get(0).getVehicleNo());
		
		// verify that VehicleDAO.save method got executed at least once
		verify(vehicleDAO,times(1)).save(vehicle);
		verify(vehicleDAO,times(1)).findAll();
	}
	
	@Test
	@Order(2)
	void testupdateVehicle() {
		logger.info("Testing Update Vehicle");
		
		Vehicle p = vlist.get(0);
		p.setVehicleNo("AP555");
		vlist.set(0, p);

		// When record is update then return list with updated VehicleRecord
		when(vehicleDAO.save(vehicle)).thenReturn(p);
		when(vehicleDAO.findAll()).thenReturn(vlist);
		
		List<Vehicle> l1=service.update(vehicle);
		// service will call VehicleDAO update
		assertTrue(l1.size()>0);
		assertEquals(1,l1.size());
		assertEquals("AP555",l1.get(0).getVehicleNo());
		
		// verify that VehicleDAO.update method got executed at least once
		verify(vehicleDAO,times(1)).save(vehicle);
		verify(vehicleDAO,times(1)).findAll();
		
	}
	@Test
	@Order(4)
	void testdeleteVehicleRecord() {
		logger.info("Testing Delete VehicleRecord");
		
		vlist.remove(vehicle);
		// When record is deleted then return list without thatVehicleRecord
		doNothing().when(vehicleDAO).deleteById("11");
		when(vehicleDAO.findAll()).thenReturn(vlist);
		
		List<Vehicle> l1=service.deleteById("11");
		// service will call VehicleDAO delete 
		assertTrue(l1.size()==0);
		assertEquals(0,l1.size());
		assertThrows(IndexOutOfBoundsException.class, ()->l1.get(0));
		
		// verify that VehicleDAO.delete method got executed at least once
		verify(vehicleDAO,times(1)).deleteById("11");
		verify(vehicleDAO,times(1)).findAll();
		
		
	}
	
	@Test
	@Order(3)
	void testfindVehicleRecord() throws RecordNotFoundException {
		logger.info("Testing find VehicleRecord by id");
		
		Optional<Vehicle> op=Optional.of(vehicle);
		// When record is findById then return  VehicleRecord of id "4888766"
		if(op.isPresent()) {
			when(vehicleDAO.findById("11")).thenReturn(op);
		}
		
		Vehicle p=service.findById("11");
		logger.info(p.toString());
		// service will call VehicleDAO findById 
		assertNotNull(p);
		assertEquals("AP123",p.getVehicleNo());
		assertEquals("2W",p.getVehicleType());
		assertThrows(RecordNotFoundException.class, ()->service.findById("77"));
		Vehicle vehicle1 = new Vehicle();
		assertThrows(RecordNotFoundException.class, ()->service.search(vehicle1));
		
		
		// verify that VehicleDAO.findById(4888766) method got executed at least once
		verify(vehicleDAO,times(1)).findById("11");
		
		
	}
	

}
