package com.example.agifyapp.controller;

import com.example.agifyapp.model.AgifyResponse;
import com.example.agifyapp.service.AgifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AgifyController {

    @Autowired
    private AgifyService agifyService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @PostMapping("/getAge")
    public String getAge(@RequestParam("name") String name, 
                        @RequestParam(value = "country", required = false) String country,
                        Model model) {
        try {
            AgifyResponse response;
            if (country != null && !country.trim().isEmpty()) {
                response = agifyService.getAgeByNameAndCountry(name, country);
            } else {
                response = agifyService.getAgeByName(name);
            }
            model.addAttribute("response", response);
            return "result";
        } catch (Exception e) {
            model.addAttribute("error", "Error fetching age data: " + e.getMessage());
            return "error";
        }
    }

    @GetMapping("/back")
    public String backToHome() {
        return "redirect:/";
    }
}
