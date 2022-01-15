package com.laptrinhjavaweb.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SendMailService {

	private final JavaMailSender javaMailSender;
	
	public void sendText(String mailTo, String subject, String content) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(message, true,"utf-8");
		
		message.setContent(content,"text/html;charset=utf-8");
		
		helper.setFrom("thao@gmail.com");
		
		helper.setTo(mailTo);
		
		helper.setSubject(subject);
		
		javaMailSender.send(message);
	}
	
	public void sendHtml(String mailTo, String subject, String content) throws MessagingException{
		
		MimeMessage message = javaMailSender.createMimeMessage();
		
		boolean multipart = true;
			
		MimeMessageHelper helper = new MimeMessageHelper(message, multipart,"utf-8");
		
		helper.setFrom("random@gmail.com");
		
		message.setContent(content,"text/html;charset=utf-8");
		
		helper.setTo(mailTo);
		
		helper.setSubject(subject);
		
		this.javaMailSender.send(message);
	}

}
