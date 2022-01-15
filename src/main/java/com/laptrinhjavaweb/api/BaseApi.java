package com.laptrinhjavaweb.api;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.laptrinhjavaweb.entity.FirebaseToken;
import com.laptrinhjavaweb.model.FirebaseTokenRequest;
import com.laptrinhjavaweb.model.MailRequest;
import com.laptrinhjavaweb.model.NotificationRequest;
import com.laptrinhjavaweb.repository.TokensRepository;
import com.laptrinhjavaweb.service.FirebaseService;
import com.laptrinhjavaweb.service.SendMailService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BaseApi {

	private final FirebaseService firebaseService;
	
	private final SendMailService sendMailService;
	
	private final TokensRepository tokenRepository;
	
	@PutMapping("/register-token")
	public FirebaseToken registerToken(@RequestBody FirebaseTokenRequest tokenRequest) {
		System.out.println(tokenRequest);
		return this.firebaseService.saveToken(tokenRequest);
	}
	
	@PostMapping("/send-notification")
	public String sendNotification(@RequestBody NotificationRequest request, @RequestParam("token") String token) {
		return this.firebaseService.sendNotification(token, request);
	}
	
	@PostMapping("/send-batch-notification")
	public int sendBatchNotification(@RequestBody NotificationRequest request) throws FirebaseMessagingException {
		System.out.println(request);
		return this.firebaseService.sendAllNotification(request);
	}
	
	@PostMapping("/send-mail")
	public void sendMail(@RequestBody MailRequest request) throws MessagingException {
		System.out.println(request);
		this.sendMailService.sendHtml(request.getMailTo(), request.getSubject(), request.getContent());
	
	}
	
	@GetMapping("get-token")
	public List<String> getTokens() {
		return tokenRepository.getTokens();
	}
	
}







