package com.example.agifyapp;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

/**
 * Test Suite for Agify Age Estimator Application
 * 
 * This suite runs all test classes to ensure comprehensive coverage:
 * - Unit tests for individual components
 * - Integration tests for service layer
 * - Controller tests with mocked dependencies
 * - Model tests for data validation
 * - Application context tests
 */
@Suite
@SelectClasses({
    // Application Tests
    AgifyAppApplicationTest.class,
    AgifyAppApplicationTests.class,
    
    // Model Tests
    com.example.agifyapp.model.AgifyResponseTest.class,
    
    // Service Tests
    com.example.agifyapp.service.AgifyServiceTest.class,
    com.example.agifyapp.service.AgifyServiceUnitTest.class,
    com.example.agifyapp.service.AgifyServiceIntegrationTest.class,
    
    // Controller Tests
    com.example.agifyapp.controller.AgifyControllerTest.class
})
public class TestSuite {
    // This class serves as a test suite runner
    // All tests will be executed when this suite is run
}
