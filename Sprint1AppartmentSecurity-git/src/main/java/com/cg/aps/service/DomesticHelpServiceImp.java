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

import com.cg.aps.entities.DomesticHelp;
import com.cg.aps.exception.RecordNotFoundException;
import com.cg.aps.repository.DomesticHelpDAO;


@Service("domesticHelpService")
@Transactional
public class DomesticHelpServiceImp implements DomesticHelpService{
	
	Logger logger = LoggerFactory.getLogger(GuardServiceImp.class);
	
	@Autowired
	private DomesticHelpDAO domesticHelpDAO;

	@Override
	public List<DomesticHelp> getAllDomesticHelpRecords() {
		
		return domesticHelpDAO.findAll();
	}

	@Override
	public List<DomesticHelp> addDomesticHelpRecord(DomesticHelp bean) {
		DomesticHelp obj = domesticHelpDAO.save(bean);
		if(obj !=null) {
			return getAllDomesticHelpRecords();
		}
		else {
			return null;
		}
	}

	@Override
	public List<DomesticHelp> updateDomesticHelpRecord(DomesticHelp bean) {
		DomesticHelp p=domesticHelpDAO.save(bean);
		if(p!=null) {
			return getAllDomesticHelpRecords();
		}
		else {
			return null;
		}
	}

	@Override
	public List<DomesticHelp> deleteDomesticHelpRecordById(String helperId) {
		domesticHelpDAO.deleteById(helperId);		
		return getAllDomesticHelpRecords();
	}

//	@Override
//	public List<DomesticHelp> findRecordByHelperName(String helperName) {
//		logger.info("DomesticHelpRecord is found and retrieved from database for Guard name "+helperName);
//		List<DomesticHelp> list = new ArrayList<DomesticHelp>();
//		List<DomesticHelp> allDomesticHelpRecords = getAllDomesticHelpRecords();
//		ListIterator<DomesticHelp> lItr = allDomesticHelpRecords.listIterator();
//		while(lItr.hasNext()) {
//			DomesticHelp helper = lItr.next();
//			if(helper.getName().equals(helperName)) {
//				list.add(helper);
//			}
//		}
//		return list;
//	}

	@Override    //helperId is visitor mobile number 
	public DomesticHelp findDomesticHelpRecordById(String helperId) throws RecordNotFoundException {
		Optional<DomesticHelp> op = domesticHelpDAO.findById(helperId);
		if(op.isPresent()) {
			logger.info("DomesticHelpRecord is retrieved from database for DomesticHelpRecordID "+helperId);
			return op.get();
		}
		else
		{
			logger.error("DomesticHelpRecord is NOT retrieved from database for DomesticHelpRecordID "+helperId);
			throw new RecordNotFoundException("DomesticHelpRecord with "+helperId + " not found");
		}	
	}

	@Override
	public DomesticHelp searchDomesticHelpRecord(DomesticHelp bean) throws RecordNotFoundException {
		Optional<DomesticHelp> op = domesticHelpDAO.findById(bean.getId());
		if(op.isPresent()) {
			logger.info("DomesticHelpRecord is there in database for DomesticHelpRecord info "+bean);
			return op.get();
		}
		else
		{
			logger.error("DomesticHelpRecord is NOT retrieved from database for DomesticHelpRecord "+bean);
			throw new RecordNotFoundException("Guard with "+bean + " not found");
		}	
	}

	@Override
	public List<DomesticHelp> save(DomesticHelp bean) {
		DomesticHelp p = domesticHelpDAO.save(bean);
		if(p!=null) {
			return getAllDomesticHelpRecords();
		}
		else {
			return null;
		}
	}

	@Override
	public List<DomesticHelp> getByHelpType(String helpType) {
		List<DomesticHelp> list = new ArrayList<DomesticHelp>();
		List<DomesticHelp> allHelpers = getAllDomesticHelpRecords();
		ListIterator<DomesticHelp> lItr = allHelpers.listIterator();
		while(lItr.hasNext()) {
			DomesticHelp help = lItr.next();
			if(help.getHelpType().equals(helpType)) {
				list.add(help);
			}
		}
		return list;
	}
	

}
