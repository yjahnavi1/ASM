package com.cg.aps.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.aps.entities.User;

@Repository
public interface UserDAO extends JpaRepository<User,String>{


	List<User> findByMobileNo(String mobileNo);

}
