package com.example.agifyapp.service;

import com.example.agifyapp.model.AgifyResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(properties = {
    "agify.api.url=https://api.agify.io"
})
class AgifyServiceTest {

    @Test
    void testAgifyResponseModel() {
        // Test the AgifyResponse model
        AgifyResponse response = new AgifyResponse();
        response.setName("John");
        response.setAge(30);
        response.setCount(1000);
        response.setCountryId("US");

        assertEquals("John", response.getName());
        assertEquals(30, response.getAge());
        assertEquals(1000, response.getCount());
        assertEquals("US", response.getCountryId());
    }

    @Test
    void testAgifyResponseToString() {
        AgifyResponse response = new AgifyResponse("Jane", 25, 500, "CA");
        String result = response.toString();
        
        assertTrue(result.contains("Jane"));
        assertTrue(result.contains("25"));
        assertTrue(result.contains("500"));
        assertTrue(result.contains("CA"));
    }

}
