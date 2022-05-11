package com.laptrinhjavaweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class Test1Controller {

    @GetMapping("/123")
    public String index(Model model) {
        model.addAttribute("title", "Gửi thông báo");
        return "send-notification";
    }

    @GetMapping("/send-notification123")
    public String sendNotiPage(Model model) {
        model.addAttribute("title","Gửi mail");
        return "mail";
    }


}

