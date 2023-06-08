package com.cg.aps.service;

import java.util.ArrayList;
//import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
//import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.aps.entities.Guard;
import com.cg.aps.exception.RecordNotFoundException;
import com.cg.aps.repository.GuardDAO;


@Service("guardService")
@Transactional
public class GuardServiceImp implements GuardService{
	
	Logger logger = LoggerFactory.getLogger(GuardServiceImp.class);
	@Autowired
	private GuardDAO guardDAO;
	
	@Override
	public List<Guard> getAllGuards(){
	
		return guardDAO.findAll();
	}

	@Override
	public List<Guard> add(Guard bean) {
		Guard obj = guardDAO.save(bean);
		if(obj !=null) {
			return getAllGuards();
		}
		else {
			return null;
		}
	}

	@Override
	public List<Guard> update(Guard bean) {
		Guard p=guardDAO.save(bean);
		if(p!=null) {
			return getAllGuards();
		}
		else {
			return null;
		}
		
	}

	@Override
	public List<Guard> deleteById(String guardId) {
		guardDAO.deleteById(guardId);		
		return getAllGuards();
		
	}

	@Override
	public Guard findByName(String name) {
		logger.info("Guard is found and retrieved from database for Guard name "+name);
		return guardDAO.findByGuardName(name);
	}

	@Override
	public Guard findGuard(String id) throws RecordNotFoundException {
		Optional<Guard> op = guardDAO.findById(id);
		if(op.isPresent()) {
			logger.info("Guard is retrieved from database for GuardID "+id);
			return op.get();
		}
		else
		{
			logger.error("Guard is NOT retrieved from database for GuardID "+id);
			throw new RecordNotFoundException("Guard with "+id + " not found");
		}			
	}

//	@Override
//	public Guard search(Guard bean) throws RecordNotFoundException {
//		Optional<Guard> op = guardDAO.findById(bean.getGuardMobileNo());
//		if(op.isPresent()) {
//			logger.info("Guard is Not there in database for Guard info "+bean);
//			return op.get();
//		}
//		else
//		{
//			logger.error("Guard is NOT retrieved from database for GuardID "+bean);
//			throw new RecordNotFoundException("Guard with "+bean + " not found");
//		}	
//
//	}
	@Override
	public List<Guard> save(Guard bean) {
		Guard p = guardDAO.save(bean);
		if(p!=null) {
			return getAllGuards();
		}
		else {
			return null;
		}
	}

	@Override
	public List<Guard> getByName(String name) {
		List<Guard> list = new ArrayList<Guard>();
		List<Guard> allGuards = getAllGuards();
		ListIterator<Guard> lItr = allGuards.listIterator();
		while(lItr.hasNext()) {
			Guard guard = lItr.next();
			if(guard.getGuardName().equals(name)) {
				list.add(guard);
			}
		}
		return list;
	}
	
	/*@Override
	public List<Guard> saveAll() {
		
		AtomicInteger guardId=new AtomicInteger(1000);
		List<Guard> guards=new ArrayList<>();
		guards.add(new Guard(guardId.incrementAndGet(), "King",9347854655,"12:10",new Date(2025-1900, 3, 12)));
		guards.add(new Guard(guardId.incrementAndGet(), "King",9347854655,"12:10", new Date(2019-1900, 3, 28)));
		guards.add(new Guard(guardId.incrementAndGet(), "King",9347854655,"12:10", new Date(2021-1900, 2, 10)));
		guards.add(new Guard(guardId.incrementAndGet(), "King",9347854655,"12:10", new Date(2020-1900, 1, 12)));
		guards.add(new Guard(guardId.incrementAndGet(), "King",9347854655,"12:10", new Date(2022-1900, 7, 22)));
		guards.add(new Guard(guardId.incrementAndGet(), "King",9347854655,"12:10", new Date(2025-1900, 3, 2)));
		guards.add(new Guard(guardId.incrementAndGet(), "King",9347854655,"12:10", new Date(2025-1900, 3, 1)));
		
		guardDAO.saveAll(guards);
		return getAllGuards();
	}*/


}
