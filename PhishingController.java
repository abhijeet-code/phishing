package com.example.phishingdetector;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/phishing")
public class PhishingController {

    @PostMapping("/detect-sms")
    public ResponseEntity<String> detectPhishingSMS(@RequestBody String smsContent) {

        boolean isPhishing = smsContent.contains("suspiciouslink.com");
        return ResponseEntity.ok(isPhishing ? "Phishing detected" : "Safe SMS");
    }
}
