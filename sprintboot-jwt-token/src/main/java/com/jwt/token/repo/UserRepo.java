package com.jwt.token.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jwt.token.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{
	
	User findByEmailIdIgnoreCaseAndPassword(String emailId, String password);

}
