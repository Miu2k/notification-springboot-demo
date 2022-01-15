package com.laptrinhjavaweb.model;

import lombok.Data;

@Data
public class MailRequest {
	
	private String mailTo;
	
	private String subject;
	
	private String content;

}
