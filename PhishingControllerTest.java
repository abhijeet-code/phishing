package com.example.phishingdetector;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PhishingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testDetectPhishingSMS() throws Exception {
        mockMvc.perform(post("/api/phishing/detect-sms")
                .content("Get your free prize now! Visit suspiciouslink.com"))
                .andExpect(status().isOk())
                .andExpect(content().string("Phishing detected"));

        mockMvc.perform(post("/api/phishing/detect-sms")
                .content("Hello, your order has been shipped."))
                .andExpect(status().isOk())
                .andExpect(content().string("Safe SMS"));
    }
}
