package com.laptrinhjavaweb.model;

import javax.persistence.Id;

import lombok.Data;

@Data
public class FirebaseTokenRequest {


	private String token;
	
	private String deviceInfo;
	
}
