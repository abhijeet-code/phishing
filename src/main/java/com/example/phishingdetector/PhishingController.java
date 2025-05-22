package com.example.phishingdetector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/phishing")
public class PhishingController {

    private final VirusTotalService virusTotalService;

    @Autowired
    public PhishingController(VirusTotalService virusTotalService) {
        this.virusTotalService = virusTotalService;
    }

    @PostMapping("/detect-sms")
    public ResponseEntity<String> detectPhishingSMS(@RequestBody String smsContent) {
    
        String result = virusTotalService.analyzeContent(smsContent);
        return ResponseEntity.ok(result);
    }
}
