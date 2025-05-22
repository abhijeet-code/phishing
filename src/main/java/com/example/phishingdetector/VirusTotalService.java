package com.example.phishingdetector;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;



@Service
public class VirusTotalService {

    @Value("${virustotal.api.key}") 
    private String virusTotalApiKey;
    private final String VIRUS_TOTAL_URL = "https://www.virustotal.com/api/v3/urls/";
    private final RestTemplate restTemplate = new RestTemplate();

    public String analyzeContent(String smsContent) {
    
        String urlToCheck = extractUrlFromSMS(smsContent);
        if (urlToCheck == null) {
            return "No URL found in SMS.";
        }
        if (urlToCheck.startsWith("http://")) {
            urlToCheck = urlToCheck.replaceFirst("http://", "https://");}

        
        String encodedUrl = encodeUrl(urlToCheck);

        
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-apikey", virusTotalApiKey);

        
        HttpEntity<String> entity = new HttpEntity<>(headers);

    
        String apiUrl = VIRUS_TOTAL_URL + encodedUrl;
        try {
            ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class);

            
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                
                ObjectMapper mapper = new ObjectMapper();
                JsonNode rootNode = mapper.readTree(response.getBody());

                
                JsonNode dataNode = rootNode.get("data");

                if (dataNode != null) {
                    
                    JsonNode attributesNode = dataNode.get("attributes");

                    if (attributesNode != null) {
    
    JsonNode analysisStatsNode = attributesNode.get("last_analysis_stats");
    int maliciousCount = analysisStatsNode.get("malicious").asInt();
    int suspiciousCount = analysisStatsNode.get("suspicious").asInt();
    int harmlessCount = analysisStatsNode.get("harmless").asInt();


    JsonNode categoriesNode = attributesNode.get("categories");

    String categoryDescription = "";
    if (categoriesNode != null && categoriesNode.size() > 0) {
        StringBuilder categoryBuilder = new StringBuilder("Categories: ");
        categoriesNode.fieldNames().forEachRemaining(category -> {
            String value = categoriesNode.get(category).asText();
            categoryBuilder.append(category).append(" - ").append(value).append(", ");
        });
        categoryDescription = categoryBuilder.toString();
    } else {
        categoryDescription = "No specific categories found.";
    }


    if (maliciousCount > 0 || suspiciousCount > 0) {
        return "Phishing or Malicious content detected: " +
                maliciousCount + " malicious, " + suspiciousCount + " suspicious. " +
                categoryDescription;
    } else if (harmlessCount > 0) {
        return "Safe URL: " + harmlessCount + " engines marked it harmless. " + categoryDescription;
    } else {
        return "Unable to determine URL safety. " + categoryDescription;
    }
} else {
    return "Error: Missing attributes in VirusTotal response.";
}

} else {
return "Error: No data found in VirusTotal response.";
}
} else {
return "Error calling VirusTotal API.";
}
} catch (Exception e) {
return "Error connecting to VirusTotal API: " + e.getMessage();
}
}
    private String extractUrlFromSMS(String smsContent) {
        
        String regex = "(https?://[\\w-]+(\\.[\\w-]+)+(/[^\\s]*)?)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(smsContent);
        return matcher.find() ? matcher.group(0) : null;
    }

    private String encodeUrl(String url) {
        try {
        
            java.net.URL testUrl = new java.net.URL(url);  

    
            return Base64.getUrlEncoder().encodeToString(url.getBytes("UTF-8"));
        } catch (java.net.MalformedURLException e) {
            System.out.println("Invalid URL format: " + url);
            return null;
        } catch (Exception e) {
            System.out.println("Error encoding URL: " + url);
            e.printStackTrace(); 
            return null;
        }
    }
}