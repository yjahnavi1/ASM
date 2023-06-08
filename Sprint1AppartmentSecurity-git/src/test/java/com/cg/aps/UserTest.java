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

import com.cg.aps.entities.User;
import com.cg.aps.exception.RecordNotFoundException;
import com.cg.aps.repository.UserDAO;
import com.cg.aps.service.UserService;
import com.cg.aps.service.UserServiceImp;

@SpringBootTest
@ContextConfiguration(classes = Sprint1AppartmentSecurityApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserTest {

Logger logger = LoggerFactory.getLogger(GuardServiceTest.class);
	
	@Mock
	UserDAO userdDAO;
	
	@InjectMocks
	UserService service = new UserServiceImp();
	
	List<User> uList;
	User user;
	
	@BeforeEach
	public void beforeTest() {
		uList = new ArrayList<>();
		user = new User("123","Admin","janu@123","9347854655","admin");
		uList.add(user);
	}

	@Test
	@Order(1)
	void testinsertGuard() {
		logger.info("Testing Inserting Guard");
		
		// When record is inserted then return list with that guard
		when(userdDAO.save(user)).thenReturn(user);
		when(userdDAO.findAll()).thenReturn(uList);
		List<User> l1=service.save(user);
		// service will call guardDAO save method
		assertTrue(l1.size()>0);
		assertEquals(1,l1.size());
		assertEquals("Admin",l1.get(0).getLoginId());
		
		// verify that guardDAO.save method got executed at least once
		verify(userdDAO,times(1)).save(user);
		verify(userdDAO,times(1)).findAll();
	}
	
	@Test
	@Order(2)
	void testupdateGuard() {
		logger.info("Testing Update Guard");
		
		User p = uList.get(0);
		p.setLoginId("jani");
		uList.set(0, p);

		// When record is update then return list with updated guard
		when(userdDAO.save(user)).thenReturn(p);
		when(userdDAO.findAll()).thenReturn(uList);
		
		List<User> l1=service.update(user);
		// service will call guardDAO update
		assertTrue(l1.size()>0);
		assertEquals(1,l1.size());
		assertEquals("jani",l1.get(0).getLoginId());
		
		// verify that guardDAO.update method got executed at least once
		verify(userdDAO,times(1)).save(user);
		verify(userdDAO,times(1)).findAll();
		
	}
	@Test
	@Order(4)
	void testdeleteGuard() {
		logger.info("Testing Delete Guard");
		
		uList.remove(user);
		// When record is deleted then return list without that Guard
		doNothing().when(userdDAO).deleteById("9347854655");
		when(userdDAO.findAll()).thenReturn(uList);
		
		List<User> l1=service.deleteById("9347854655");
		// service will call GuardDAO delete 
		assertTrue(l1.size()==0);
		assertEquals(0,l1.size());
		assertThrows(IndexOutOfBoundsException.class, ()->l1.get(0));
		
		// verify that GuardDao.delete method got executed at least once
		verify(userdDAO,times(1)).deleteById("9347854655");
		verify(userdDAO,times(1)).findAll();
		
		
	}
	
	@Test
	@Order(3)
	void testfindGuard() throws RecordNotFoundException {
		logger.info("Testing find Guard by id");
		
		Optional<User> op=Optional.of(user);
		// When record is findById then return  Guard of id 1001
		if(op.isPresent()) {
			when(userdDAO.findById("Admin")).thenReturn(op);
		}
		
		User p=service.findUser("Admin");
		logger.info(p.toString());
		// service will call GuardDAO findById 
		assertNotNull(p);
		assertEquals("9347854655",p.getMobileNo());
		assertEquals("Admin",p.getLoginId());
		assertThrows(RecordNotFoundException.class, ()->service.findUser("Admin123"));
		User guard1 = new User();
		assertThrows(RecordNotFoundException.class, ()->service.search(guard1));
		
		
		// verify that guardDAO.findById(1001) method got executed at least once
		verify(userdDAO,times(1)).findById("Admin");
		
	}
}
