package com.laptrinhjavaweb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="firebase_token")
public class FirebaseToken {

	@Id
	private String token;
	
	@Column(name="device_info")
	private String deviceInfo;
}
