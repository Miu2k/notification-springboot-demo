package com.laptrinhjavaweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseController {

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("title", "Trang chủ");
		return "index";
	}
	
	@GetMapping("/send-notification")
	public String sendNotiPage(Model model) {
		model.addAttribute("title", "Gửi thông báo");
		return "send-notification";
	}
	
	@GetMapping("/send-mail")
	public String sendMail(Model model) {
		model.addAttribute("title","Gửi mail");
		return "mail";
	}
}
