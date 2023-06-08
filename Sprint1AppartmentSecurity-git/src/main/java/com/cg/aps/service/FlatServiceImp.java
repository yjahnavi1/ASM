package com.cg.aps.service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.aps.entities.Flat;
import com.cg.aps.exception.RecordNotFoundException;
import com.cg.aps.repository.FlatDAO;

@Service("flatService")
@Transactional
public class FlatServiceImp implements FlatService{
	
	Logger logger = LoggerFactory.getLogger(FlatServiceImp.class);
	@Autowired
	private FlatDAO flatDAO;
	
	@Override
	public List<Flat> getAllFlatRecords() {
		return flatDAO.findAll();
	}

	@Override
	public List<Flat> add(Flat bean) {
		Flat obj = flatDAO.save(bean);
		if(obj !=null) {
			return getAllFlatRecords();
		}
		else {
			return null;
		}
	}

	@Override
	public List<Flat> update(Flat bean) {
		Flat p=flatDAO.save(bean);
		if(p!=null) {
			return getAllFlatRecords();
		}
		else {
			return null;
		}
	}

	@Override
	public List<Flat> deleteById(String id) {
		flatDAO.deleteById(id);		
		return getAllFlatRecords();
		
	}

//	@Override
//	public Flat findByOwnerName(String ownerName) {
//		logger.info("Guard is found and retrieved from database for Guard name "+ownerName);
//		return flatDAO.findByOwnerName(ownerName);
//	}

	@Override
	public Flat search(Flat bean) throws RecordNotFoundException {
		Optional<Flat> op = flatDAO.findById(bean.getFlatId());
		if(op.isPresent()) {
			logger.info("Guard is Not there in database for Guard info "+bean);
			return op.get();
		}
		else
		{
			logger.error("Guard is NOT retrieved from database for GuardID "+bean);
			throw new RecordNotFoundException("Guard with "+bean + " not found");
		}	
	}

	@Override
	public Flat findFlat(String id) throws RecordNotFoundException {
		Optional<Flat> op = flatDAO.findById(id);
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

	@Override
	public List<Flat> getByName(String name) {
		List<Flat> list = new ArrayList<Flat>();
		List<Flat> allFlats = getAllFlatRecords();
		ListIterator<Flat> lItr = allFlats.listIterator();
		while(lItr.hasNext()) {
			Flat guard = lItr.next();
			if(guard.getOwnerName().equals(name)) {
				list.add(guard);
			}
		}
		return list;
	}

	@Override
	public List<Flat> save(Flat bean) {
		Flat p = flatDAO.save(bean);
		if(p!=null) {
			return getAllFlatRecords();
		}
		else {
			return null;
		}
	}

}
