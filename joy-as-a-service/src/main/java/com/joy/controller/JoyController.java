package com.joy.controller;

import com.joy.model.Joy;
import com.joy.model.PendingJoy;
import com.joy.repository.JoyRepository;
import com.joy.repository.PendingJoyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api")
public class JoyController {

    @Autowired
    private JoyRepository joyRepository;
    @Autowired
    private PendingJoyRepository pendingRepository;

    @GetMapping("/joy")
    public Joy getRandomJoy() {
        return joyRepository.findRandomJoy();
    }

    @PostMapping("/submit")
    public PendingJoy submitJoy(@RequestBody PendingJoy newJoy) {
        return pendingRepository.save(newJoy);
    }
}