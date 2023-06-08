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

import com.cg.aps.entities.Delivery;
import com.cg.aps.exception.RecordNotFoundException;
import com.cg.aps.repository.DeliveryDAO;

@Service("deliveryService")
@Transactional
public class DeliveryServiceImp implements DeliveryService{
	
	Logger logger = LoggerFactory.getLogger(GuardServiceImp.class);
	@Autowired
	private DeliveryDAO deliveryDAO;

	@Override
	public List<Delivery> getAllDeliveryRecords() {
		return deliveryDAO.findAll();
	}

	@Override
	public List<Delivery> add(Delivery bean) {
		Delivery obj = deliveryDAO.save(bean);
		if(obj !=null) {
			return getAllDeliveryRecords();
		}
		else {
			return null;
		}
	}

	@Override
	public List<Delivery> update(Delivery bean) {
		Delivery p=deliveryDAO.save(bean);
		if(p!=null) {
			return getAllDeliveryRecords();
		}
		else {
			return null;
		}
	}

	@Override		//id is visitor mobile number
	public List<Delivery> deleteById(String id) {
		deliveryDAO.deleteById(id);		
		return getAllDeliveryRecords();
	}

//	@Override		//name is visitor name
//	public List<Delivery> findByName(String name) {
//		logger.info("Guard is found and retrieved from database for Guard name "+name);
//		List<Delivery> list = new ArrayList<Delivery>();
//		List<Delivery> allGuards = getAllDeliveryRecords();
//		ListIterator<Delivery> lItr = allGuards.listIterator();
//		while(lItr.hasNext()) {
//			Delivery guard = lItr.next();
//			if(guard.getName().equals(name)) {
//				list.add(guard);
//			}
//		}
//		return list;
//	}

	@Override		//id is nothing but visitor mobile no
	public Delivery findDeliveryById(String id) throws RecordNotFoundException {
		Optional<Delivery> op = deliveryDAO.findById(id);
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
//	public Delivery search(Delivery bean) throws RecordNotFoundException {
//		Optional<Delivery> op = deliveryDAO.findById(bean.getVisitorMobileNo());
//		if(op.isPresent()) {
//			logger.info("Guard is Not there in database for Guard info "+bean);
//			return op.get();
//		}
//		else
//		{
//			logger.error("Guard is NOT retrieved from database for GuardID "+bean);
//			throw new RecordNotFoundException("Guard with "+bean + " not found");
//		}
//	}

	@Override
	public List<Delivery> save(Delivery bean) {
		Delivery p = deliveryDAO.save(bean);
		if(p!=null) {
			return getAllDeliveryRecords();
		}
		else {
			return null;
		}
	}

//	@Override
//	public Delivery search(Delivery bean) throws RecordNotFoundException {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	@Override
//	public List<Delivery> getByName(String name) {
//		List<Delivery> list = new ArrayList<Delivery>();
//		List<Delivery> allGuards = getAllDeliveryRecords();
//		ListIterator<Delivery> lItr = allGuards.listIterator();
//		while(lItr.hasNext()) {
//			Delivery guard = lItr.next();
//			if(guard.getName().equals(name)) {
//				list.add(guard);
//			}
//		}
//		return list;
//	}
	

}
