package com.joy.controller;

import com.joy.model.Joy;
import com.joy.repository.JoyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class JoyController {

    @Autowired
    private JoyRepository joyRepository;

    @GetMapping("/joy")
    public Joy getRandomJoy() {
        return joyRepository.findRandomJoy();
    }
}