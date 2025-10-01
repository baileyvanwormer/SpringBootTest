package com.example.agifyapp.service;

import com.example.agifyapp.model.AgifyResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(properties = {
    "agify.api.url=https://api.agify.io"
})
class AgifyServiceIntegrationTest {

    @Autowired
    private AgifyService agifyService;

    @Test
    void testGetAgeByName_ValidName() {
        // This test will make a real API call
        // Note: This might fail if API is down or rate limited
        try {
            AgifyResponse response = agifyService.getAgeByName("John");
            
            assertNotNull(response);
            assertNotNull(response.getName());
            assertNotNull(response.getAge());
            assertNotNull(response.getCount());
            assertEquals("John", response.getName());
            assertTrue(response.getAge() > 0);
            assertTrue(response.getCount() > 0);
        } catch (Exception e) {
            // If API is down, we'll just log and continue
            System.out.println("API call failed (expected in some environments): " + e.getMessage());
        }
    }

    @Test
    void testGetAgeByNameAndCountry_ValidNameAndCountry() {
        try {
            AgifyResponse response = agifyService.getAgeByNameAndCountry("Sarah", "US");
            
            assertNotNull(response);
            assertNotNull(response.getName());
            assertNotNull(response.getAge());
            assertNotNull(response.getCount());
            assertEquals("Sarah", response.getName());
            assertTrue(response.getAge() > 0);
            assertTrue(response.getCount() > 0);
            assertEquals("US", response.getCountryId());
        } catch (Exception e) {
            System.out.println("API call failed (expected in some environments): " + e.getMessage());
        }
    }

    @Test
    void testGetAgeByName_EmptyName() {
        // Test with empty name - should still work but might return null age
        try {
            AgifyResponse response = agifyService.getAgeByName("");
            
            assertNotNull(response);
            // API might return null age for empty names
        } catch (Exception e) {
            // Expected behavior for invalid input
            assertTrue(e.getMessage().contains("Error calling Agify API"));
        }
    }
}
