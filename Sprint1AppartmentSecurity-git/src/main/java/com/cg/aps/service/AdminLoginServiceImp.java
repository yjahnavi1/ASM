package com.cg.aps.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.aps.entities.AdminLogin;
import com.cg.aps.exception.RecordNotFoundException;
import com.cg.aps.repository.AdminLoginDAO;


@Service("adminLoginService")
@Transactional
public class AdminLoginServiceImp implements AdminLoginService{
	
	Logger logger = LoggerFactory.getLogger(GuardServiceImp.class);
	@Autowired
	private AdminLoginDAO adminLoginDAO;

	@Override
	public List<AdminLogin> getAllAdminLogins() {
		return adminLoginDAO.findAll();
	}

	@Override
	public List<AdminLogin> add(AdminLogin bean) {
		AdminLogin obj = adminLoginDAO.save(bean);
		if(obj !=null) {
			return getAllAdminLogins();
		}
		else {
			return null;
		}
	}

	@Override
	public AdminLogin findAdminLogin(String id) throws RecordNotFoundException {
		Optional<AdminLogin> op = adminLoginDAO.findById(id);
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
	public List<AdminLogin> save(AdminLogin bean) {
		AdminLogin p = adminLoginDAO.save(bean);
		if(p!=null) {
			return getAllAdminLogins();
		}
		else {
			return null;
		}
	}

}
