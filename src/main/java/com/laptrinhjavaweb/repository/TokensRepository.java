package com.laptrinhjavaweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.laptrinhjavaweb.entity.FirebaseToken;

public interface TokensRepository extends JpaRepository<FirebaseToken, String>{
	
	@Query("select u.token from FirebaseToken u")
	List<String> getTokens();

}
