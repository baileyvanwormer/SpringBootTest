package com.example.agifyapp.service;

import com.example.agifyapp.model.AgifyResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("AgifyService Unit Tests")
public class AgifyServiceUnitTest {

    @Mock
    private RestTemplate restTemplate;

    private AgifyService agifyService;

    @BeforeEach
    void setUp() {
        agifyService = new AgifyService();
        // Inject the mock RestTemplate using reflection
        ReflectionTestUtils.setField(agifyService, "restTemplate", restTemplate);
        ReflectionTestUtils.setField(agifyService, "agifyApiUrl", "https://api.agify.io");
    }

    @Test
    @DisplayName("Should return age for a valid name")
    void testGetAgeByName_Success() {
        // Arrange
        AgifyResponse expectedResponse = new AgifyResponse("John", 30, 1000, null);
        when(restTemplate.getForObject(anyString(), eq(AgifyResponse.class)))
                .thenReturn(expectedResponse);

        // Act
        AgifyResponse result = agifyService.getAgeByName("John");

        // Assert
        assertNotNull(result);
        assertEquals("John", result.getName());
        assertEquals(30, result.getAge());
        assertEquals(1000, result.getCount());
        assertNull(result.getCountryId());
        verify(restTemplate).getForObject("https://api.agify.io?name=John", AgifyResponse.class);
    }

    @Test
    @DisplayName("Should return age for a valid name with country")
    void testGetAgeByNameAndCountry_Success() {
        // Arrange
        AgifyResponse expectedResponse = new AgifyResponse("John", 30, 1000, "US");
        when(restTemplate.getForObject(anyString(), eq(AgifyResponse.class)))
                .thenReturn(expectedResponse);

        // Act
        AgifyResponse result = agifyService.getAgeByNameAndCountry("John", "US");

        // Assert
        assertNotNull(result);
        assertEquals("John", result.getName());
        assertEquals(30, result.getAge());
        assertEquals(1000, result.getCount());
        assertEquals("US", result.getCountryId());
        verify(restTemplate).getForObject("https://api.agify.io?name=John&country_id=US", AgifyResponse.class);
    }

    @Test
    @DisplayName("Should throw exception when API call fails")
    void testGetAgeByName_ApiException() {
        // Arrange
        when(restTemplate.getForObject(anyString(), eq(AgifyResponse.class)))
                .thenThrow(new RestClientException("API Error"));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            agifyService.getAgeByName("John");
        });

        assertTrue(exception.getMessage().contains("Error calling Agify API"));
        verify(restTemplate).getForObject("https://api.agify.io?name=John", AgifyResponse.class);
    }

    @Test
    @DisplayName("Should throw exception when API call fails with country")
    void testGetAgeByNameAndCountry_ApiException() {
        // Arrange
        when(restTemplate.getForObject(anyString(), eq(AgifyResponse.class)))
                .thenThrow(new RestClientException("API Error"));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            agifyService.getAgeByNameAndCountry("John", "US");
        });

        assertTrue(exception.getMessage().contains("Error calling Agify API"));
        verify(restTemplate).getForObject("https://api.agify.io?name=John&country_id=US", AgifyResponse.class);
    }

    @Test
    @DisplayName("Should handle special characters in name")
    void testGetAgeByName_WithSpecialCharacters() {
        // Arrange
        AgifyResponse expectedResponse = new AgifyResponse("José", 25, 500, null);
        when(restTemplate.getForObject(anyString(), eq(AgifyResponse.class)))
                .thenReturn(expectedResponse);

        // Act
        AgifyResponse result = agifyService.getAgeByName("José");

        // Assert
        assertNotNull(result);
        assertEquals("José", result.getName());
        assertEquals(25, result.getAge());
        assertEquals(500, result.getCount());
        assertNull(result.getCountryId());
        verify(restTemplate).getForObject("https://api.agify.io?name=José", AgifyResponse.class);
    }
}
