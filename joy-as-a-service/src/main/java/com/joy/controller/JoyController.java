package com.joy.controller;

import com.joy.dto.ModerationRequest;
import com.joy.model.Joy;
import com.joy.model.PendingJoy;
import com.joy.dto.ModerationRequest;
import com.joy.repository.JoyRepository;
import com.joy.repository.PendingJoyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

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

    // See what's in the moderation queue
    @GetMapping("/moderate/queue")
    public List<PendingJoy> getModerationQueue() {
        return pendingRepository.findAll();
    }

    // approve an entry from moderation queue
    @Transactional
    @PostMapping("/moderate/approve")
    public String approveJoy(@RequestBody ModerationRequest request) {
        // 1. Find the pending joy
        Long id = request.getId();
        PendingJoy pending = pendingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Could not find Joy entry with id #" + id));
        // 2. Map to the main Joy entity
        Joy approvedJoy = new Joy();
        approvedJoy.setContent(pending.getContent());
        // 3. Save to main table & delete from pending
        joyRepository.save(approvedJoy);
        pendingRepository.delete(pending);
        return "Joy entry #" + id + " has been added to approved Joys!!";
    }

    // reject an entry from moderation queue
    @Transactional
    @PostMapping("/moderate/reject")
    public String rejectJoy(@RequestBody ModerationRequest request) {
        Long id = request.getId();
        if (pendingRepository.existsById(id)) {
            pendingRepository.deleteById(id);
            return "Joy #" + id + " has been rejected and removed from the queue.";
        } else {
            return "Error: Pending Joy #" + id + " not found.";
        }
    }

}