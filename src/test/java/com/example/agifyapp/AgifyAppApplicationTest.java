package com.example.agifyapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class AgifyAppApplicationTest {

    @Test
    void contextLoads() {
        // This test ensures that the Spring Boot application context loads successfully
        // If there are any configuration issues, this test will fail
    }

    @Test
    void applicationStarts() {
        // This test verifies that the main application class can be instantiated
        // and that all required beans are properly configured
        AgifyAppApplication.main(new String[]{});
    }
}
