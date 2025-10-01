package com.example.agifyapp.controller;

import com.example.agifyapp.model.AgifyResponse;
import com.example.agifyapp.service.AgifyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class AgifyControllerTest {

    @Mock
    private AgifyService agifyService;

    @Mock
    private Model model;

    @InjectMocks
    private AgifyController agifyController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(agifyController).build();
    }

    @Test
    void testHomePage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    void testGetAgeWithValidName() throws Exception {
        // Arrange
        AgifyResponse mockResponse = new AgifyResponse("John", 30, 1000, "US");
        when(agifyService.getAgeByName(anyString())).thenReturn(mockResponse);

        // Act & Assert
        mockMvc.perform(post("/getAge")
                .param("name", "John"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"));

        verify(agifyService).getAgeByName("John");
    }

    @Test
    void testGetAgeWithNameAndCountry() throws Exception {
        // Arrange
        AgifyResponse mockResponse = new AgifyResponse("John", 30, 1000, "US");
        when(agifyService.getAgeByNameAndCountry(anyString(), anyString())).thenReturn(mockResponse);

        // Act & Assert
        mockMvc.perform(post("/getAge")
                .param("name", "John")
                .param("country", "US"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"));

        verify(agifyService).getAgeByNameAndCountry("John", "US");
    }

    @Test
    void testGetAgeWithServiceException() throws Exception {
        // Arrange
        when(agifyService.getAgeByName(anyString())).thenThrow(new RuntimeException("API Error"));

        // Act & Assert
        mockMvc.perform(post("/getAge")
                .param("name", "John"))
                .andExpect(status().isOk())
                .andExpect(view().name("error"));

        verify(agifyService).getAgeByName("John");
    }

    @Test
    void testBackToHome() throws Exception {
        mockMvc.perform(get("/back"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }
}
