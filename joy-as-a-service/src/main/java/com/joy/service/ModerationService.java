package com.joy.service;

import com.joy.dto.ModerationRequest;
import com.joy.model.Joy;
import com.joy.model.PendingJoy;
import com.joy.repository.JoyRepository;
import com.joy.repository.PendingJoyRepository;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;

@Service
public class ModerationService {

    @Autowired
    private JoyRepository joyRepository;
    @Autowired
    private PendingJoyRepository pendingRepository;
    @Autowired
    private RateLimiterRegistry rateLimiterRegistry;

    private final RateLimiterConfig config = RateLimiterConfig.custom()
            .limitRefreshPeriod(Duration.ofSeconds(10))
            .limitForPeriod(5)
            .timeoutDuration(Duration.ZERO)
            .build();

    public String approveWithRateLimit(ModerationRequest request, String ip) {
        // By passing 'config' as the second argument, we create it if it doesn't exist
        RateLimiter limiter = rateLimiterRegistry.rateLimiter("mod-" + ip, config);
        return RateLimiter.decorateSupplier(limiter, () -> approve(request.getId())).get();
    }

    public String rejectWithRateLimit(ModerationRequest request, String ip) {
        RateLimiter limiter = rateLimiterRegistry.rateLimiter("mod-" + ip, config);
        return RateLimiter.decorateSupplier(limiter, () -> reject(request.getId())).get();
    }

    @Transactional
    public String approve(Long id) {
        PendingJoy pending = pendingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Joy not found with ID: " + id));

        Joy joy = new Joy();
        joy.setContent(pending.getContent());
        joyRepository.save(joy);
        pending.setDeleted(true);
        pendingRepository.save(pending);
        return "Joy #" + id + " approved!";
    }

    @Transactional
    public String reject(Long id) {
        PendingJoy pending = pendingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Joy not found with ID: " + id));
        if (!pendingRepository.existsById(id))
            return "Joy not found with ID: " + id;
        pending.setDeleted(true);
        pendingRepository.save(pending);
        return "Joy #" + id + " rejected.";
    }
}