package com.laptrinhjavaweb.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.BatchResponse;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.MulticastMessage;
import com.laptrinhjavaweb.entity.FirebaseToken;
import com.laptrinhjavaweb.model.FirebaseTokenRequest;
import com.laptrinhjavaweb.model.NotificationRequest;
import com.laptrinhjavaweb.repository.TokensRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class FirebaseService {

	
	@Value("${firebase.config-file}")
	private String configFilePath;
	
	private final TokensRepository tokenRepository;
	
    @PostConstruct
    private void initialize() {
        try {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(new ClassPathResource(configFilePath).getInputStream()))
   //                 .setDatabaseUrl("http://")
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            } 
        } catch (IOException e) {
            log.error("Create FirebaseApp Error", e);
        }
    }
    
	public String sendNotification(String token, NotificationRequest request)  {
		Message message = Message.builder()
				.putData("content", request.getContent())
				.putData("title", request.getTitle())
				.putData("content", request.getLink())
				.setToken(token)
				.build();
//		String response = org.apache.commons.lang3.StringUtils.EMPTY;
		String response = null;
		try {
			response = FirebaseMessaging.getInstance().send(message);
			
		}catch(FirebaseMessagingException e){
			e.printStackTrace();
		}
		return response;
	}
	
	
	public int sendBatchNotification(List<String>token, NotificationRequest request) throws FirebaseMessagingException{
		MulticastMessage message = MulticastMessage.builder()
				.putData("content", request.getContent())
				.putData("title", request.getTitle())
				.putData("content", request.getLink())
				.addAllTokens(token)
				.build();
			BatchResponse response = FirebaseMessaging.getInstance().sendMulticast(message);
			return response.getSuccessCount();
	}

	
	public int sendAllNotification(NotificationRequest request) throws FirebaseMessagingException{
//		List<String> tokens = this.tokenRepository.findAll().parallelStream().map(FirebaseToken::getToken).collect(Collectors.toList());
		List<String> tokens2 = this.tokenRepository.getTokens();
		MulticastMessage message = MulticastMessage.builder()
				.putData("content", request.getContent())
				.putData("title", request.getTitle())
				.putData("link", request.getLink())
				.addAllTokens(tokens2)
				.build();
			BatchResponse response = FirebaseMessaging.getInstance().sendMulticast(message);
			return response.getSuccessCount();
	}
	public FirebaseToken saveToken(FirebaseTokenRequest tokenRequest) {
		FirebaseToken firebaseToken = this.tokenRepository.findById(tokenRequest.getToken()).orElse(new FirebaseToken());
		firebaseToken.setToken(tokenRequest.getToken());
		firebaseToken.setDeviceInfo(tokenRequest.getDeviceInfo());
		return this.tokenRepository.save(firebaseToken); 
	}
}










