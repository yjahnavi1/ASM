package com.cg.aps.service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
//import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.aps.entities.Flat;
import com.cg.aps.entities.Visitor;
import com.cg.aps.exception.RecordNotFoundException;
import com.cg.aps.repository.VisitorDAO;


@Service("visitorService")
@Transactional
public class VisitorServiceImp implements VisitorService{
	
	Logger logger = LoggerFactory.getLogger(VisitorServiceImp.class);
	@Autowired
	private VisitorDAO visitorDAO;
	
	@Override
	public List<Visitor> getAllVisitorRecords(){
	
		return visitorDAO.findAll();
	}

	@Override
	public List<Visitor> add(Visitor bean) {
		Visitor obj = visitorDAO.save(bean);
		if(obj !=null) {
			return getAllVisitorRecords();
		}
		else {
			return null;
		}
	}

	@Override
	public List<Visitor> update(Visitor bean) {
		Visitor p=visitorDAO.save(bean);
		if(p!=null) {
			return getAllVisitorRecords();
		}
		else {
			return null;
		}
		
	}

	@Override
	public List<Visitor> deleteById(String id) {
		visitorDAO.deleteById(id);		
		return getAllVisitorRecords();
		
	}

	@Override
	public List<Visitor> findByName(String visitorName ) {
		logger.info("Visitor is found and retrieved from database for Visitor name "+visitorName);
		return visitorDAO.findByName(visitorName);
	}

	@Override
	public Visitor findById(String id) throws RecordNotFoundException {
		Optional<Visitor> op = visitorDAO.findById(id);
		if(op.isPresent()) {
			logger.info("visitor is retrieved from database for visitorMobileNo "+id);
			return op.get();
		}
		else
		{
			logger.error("Visitor is NOT retrieved from database for visitorMobileNo "+id);
			throw new RecordNotFoundException("Visitor "+id + " not found");
		}			
	}


	@Override
	public List<Visitor> save(Visitor bean) {
		Visitor p = visitorDAO.save(bean);
		if(p!=null) {
			return getAllVisitorRecords();
		}
		else {
			return null;
		}
	}

	@Override
	public List<Visitor> getByVisitorName(String name) {
		List<Visitor> list = new ArrayList<Visitor>();
		List<Visitor> allVisitor = getAllVisitorRecords();
		ListIterator<Visitor> lItr = allVisitor.listIterator();
		while(lItr.hasNext()) {
			Visitor visitor= lItr.next();
			if(visitor.getName().equals(name)) {
				list.add(visitor);
			}
		}
		return list;
	}

//	@Override
//	public List<Flat> flatsVisitedByVisitor(String id) throws RecordNotFoundException{
//		Optional<Visitor> op = visitorDAO.findById(id);
//		if(op.isPresent()) {
//			logger.info("visitor is retrieved from database for visitorMobileNo "+id);
//			Visitor visitor = op.get();
//			return visitor.getFlats();
//		}
//		else
//		{
//			logger.error("Visitor is NOT retrieved from database for visitorMobileNo "+id);
//			throw new RecordNotFoundException("Visitor "+id + " not found");
//		}
//		
//	}



	/*@Override
	public List<Visitor> saveAll() {
		
		AtomicInteger VisitorId=new AtomicInteger(9347854655);
		List<Visitor> visitor=new ArrayList<>();
		visitor.add(new Visitor(VisitorId.incrementAndGet(), 9347854655,"king","Ram","120",new Date(2025-1900, 3, 12),"12:00","2:00"));
		visitor.add(new Visitor(VisitorId.incrementAndGet(), 9347854656,"king","Ram","121", new Date(2019-1900, 3, 28)"12:00","2:00"));
		visitor.add(new Visitor(VisitorId.incrementAndGet(), 9347854657,"king","Ram","122", new Date(2021-1900, 2, 10)"12:00","2:00"));
		visitor.add(new Visitor(VisitorId.incrementAndGet(), 9347854658,"king","Ram","123", new Date(2020-1900, 1, 12)"12:00","2:00"));
		visitor.add(new Visitor(VisitorId.incrementAndGet(), 9347854659,"king","Ram","124", new Date(2022-1900, 7, 22)"12:00","2:00"));
		visitor.add(new Visitor(VisitorId.incrementAndGet(), 9347854651,"king","Ram","125", new Date(2025-1900, 3, 2)"12:00","2:00"));
		visitor.add(new Visitor(VisitorId.incrementAndGet(), "9347854650,"king","Ram","126", new Date(2025-1900, 3, 1)"12:00","2:00"));
		
		VisitorDAO.saveAll(visitor);
		return getAllVisitor();
	}*/


}