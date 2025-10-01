package com.example.agifyapp.service;

import com.example.agifyapp.model.AgifyResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;

@Service
public class AgifyService {

    @Value("${agify.api.url:https://api.agify.io}")
    private String agifyApiUrl;

    private final RestTemplate restTemplate;

    public AgifyService() {
        this.restTemplate = new RestTemplate();
    }

    public AgifyResponse getAgeByName(String name) {
        try {
            String url = agifyApiUrl + "?name=" + name;
            return restTemplate.getForObject(url, AgifyResponse.class);
        } catch (RestClientException e) {
            throw new RuntimeException("Error calling Agify API: " + e.getMessage(), e);
        }
    }

    public AgifyResponse getAgeByNameAndCountry(String name, String countryId) {
        try {
            String url = agifyApiUrl + "?name=" + name + "&country_id=" + countryId;
            return restTemplate.getForObject(url, AgifyResponse.class);
        } catch (RestClientException e) {
            throw new RuntimeException("Error calling Agify API: " + e.getMessage(), e);
        }
    }
}
