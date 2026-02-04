package com.joy.controller;

import com.joy.dto.ModerationRequest;
import com.joy.model.Joy;
import com.joy.model.PendingJoy;
import com.joy.dto.ModerationRequest;
import com.joy.repository.JoyRepository;
import com.joy.repository.PendingJoyRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.transaction.annotation.Transactional;
import com.joy.service.ModerationService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class JoyController {

    @Autowired
    private JoyRepository joyRepository;
    @Autowired
    private PendingJoyRepository pendingRepository;
    @Autowired
    private ModerationService moderationService;

    @GetMapping("/joy")
    public Joy getRandomJoy() {
        Joy joy = joyRepository.findRandomJoy();
        if (joy == null) {
            throw new RuntimeException("We do not have any joys to display for now, try again later!");
        }
        return joy;
    }

    @PostMapping("/submit")
    public PendingJoy submitJoy(@Valid @RequestBody PendingJoy newJoy) {
        String cleanContent = Jsoup.clean(newJoy.getContent(), Safelist.none());
        newJoy.setContent(cleanContent);
        return pendingRepository.save(newJoy);
    }

    // See what's in the moderation queue
    @GetMapping("/moderate/queue")
    public List<PendingJoy> getModerationQueue() {
        return pendingRepository.findAllByIsDeletedFalse();
    }

    @PostMapping("/moderate/approve")
    public String approveJoy(@Valid @RequestBody ModerationRequest request, HttpServletRequest httpRequest) {
        String clientIp = getClientIp(httpRequest);
        return moderationService.approveWithRateLimit(request, clientIp);
    }

    @PostMapping("/moderate/reject")
    public String rejectJoy(@Valid @RequestBody ModerationRequest request, HttpServletRequest httpRequest) {
        String clientIp = getClientIp(httpRequest);
        return moderationService.rejectWithRateLimit(request, clientIp);
    }

    // Helper to get IP behind proxies like Codespaces/Cloudflare
    private String getClientIp(HttpServletRequest request) {
        String xf = request.getHeader("X-Forwarded-For");
        return (xf == null) ? request.getRemoteAddr() : xf.split(",")[0];
    }

}