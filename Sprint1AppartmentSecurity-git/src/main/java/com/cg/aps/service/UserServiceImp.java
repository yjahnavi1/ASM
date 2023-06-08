package com.cg.aps.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.aps.entities.User;
import com.cg.aps.exception.RecordNotFoundException;
import com.cg.aps.repository.UserDAO;

@Service("userService")
@Transactional
public class UserServiceImp implements UserService{
	
	Logger logger = LoggerFactory.getLogger(GuardServiceImp.class);
	@Autowired
	private UserDAO userDAO;

	@Override
	public List<User> getAllUserRecords() {
		return userDAO.findAll();
	}

	@Override
	public List<User> add(User bean) {
		User obj = userDAO.save(bean);
		if(obj !=null) {
			return getAllUserRecords();
		}
		else {
			return null;
		}
	}

	@Override
	public List<User> update(User bean) {
		User p=userDAO.save(bean);
		if(p!=null) {
			return getAllUserRecords();
		}
		else {
			return null;
		}
	}

	@Override
	public List<User> deleteById(String userId) {
		userDAO.deleteById(userId);		
		return getAllUserRecords();
	}

	@Override
	public User findUser(String id) throws RecordNotFoundException {
		Optional<User> op = userDAO.findById(id);
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
	public User search(User bean) throws RecordNotFoundException {
		Optional<User> op = userDAO.findById(bean.getLoginId());
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
	public List<User> save(User bean) {
		User p = userDAO.save(bean);
		if(p!=null) {
			return getAllUserRecords();
		}
		else {
			return null;
		}
	}

	@Override
	public List<User> getByMobileNo(String mobileNo) {
		logger.info("Guard is found and retrieved from database for Guard name "+mobileNo);
		return userDAO.findByMobileNo(mobileNo);
	}

}
