package com.example.agifyapp.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("AgifyResponse Model Tests")
class AgifyResponseTest {

    private AgifyResponse agifyResponse;

    @BeforeEach
    void setUp() {
        agifyResponse = new AgifyResponse();
    }

    @Test
    @DisplayName("Should create AgifyResponse with default constructor")
    void testDefaultConstructor() {
        assertNotNull(agifyResponse);
        assertNull(agifyResponse.getName());
        assertNull(agifyResponse.getAge());
        assertNull(agifyResponse.getCount());
        assertNull(agifyResponse.getCountryId());
    }

    @Test
    @DisplayName("Should create AgifyResponse with parameterized constructor")
    void testParameterizedConstructor() {
        AgifyResponse response = new AgifyResponse("John", 30, 1000, "US");
        
        assertEquals("John", response.getName());
        assertEquals(30, response.getAge());
        assertEquals(1000, response.getCount());
        assertEquals("US", response.getCountryId());
    }

    @Test
    @DisplayName("Should set and get name correctly")
    void testNameSetterAndGetter() {
        agifyResponse.setName("Alice");
        assertEquals("Alice", agifyResponse.getName());
    }

    @Test
    @DisplayName("Should set and get age correctly")
    void testAgeSetterAndGetter() {
        agifyResponse.setAge(25);
        assertEquals(25, agifyResponse.getAge());
    }

    @Test
    @DisplayName("Should set and get count correctly")
    void testCountSetterAndGetter() {
        agifyResponse.setCount(500);
        assertEquals(500, agifyResponse.getCount());
    }

    @Test
    @DisplayName("Should set and get country ID correctly")
    void testCountryIdSetterAndGetter() {
        agifyResponse.setCountryId("CA");
        assertEquals("CA", agifyResponse.getCountryId());
    }

    @Test
    @DisplayName("Should handle null values correctly")
    void testNullValues() {
        agifyResponse.setName(null);
        agifyResponse.setAge(null);
        agifyResponse.setCount(null);
        agifyResponse.setCountryId(null);
        
        assertNull(agifyResponse.getName());
        assertNull(agifyResponse.getAge());
        assertNull(agifyResponse.getCount());
        assertNull(agifyResponse.getCountryId());
    }

    @Test
    @DisplayName("Should generate correct toString representation")
    void testToString() {
        agifyResponse.setName("Bob");
        agifyResponse.setAge(35);
        agifyResponse.setCount(750);
        agifyResponse.setCountryId("GB");
        
        String result = agifyResponse.toString();
        
        assertTrue(result.contains("Bob"));
        assertTrue(result.contains("35"));
        assertTrue(result.contains("750"));
        assertTrue(result.contains("GB"));
        assertTrue(result.contains("AgifyResponse"));
    }

    @Test
    @DisplayName("Should handle edge case values")
    void testEdgeCaseValues() {
        agifyResponse.setName("");
        agifyResponse.setAge(0);
        agifyResponse.setCount(1);
        agifyResponse.setCountryId("");
        
        assertEquals("", agifyResponse.getName());
        assertEquals(0, agifyResponse.getAge());
        assertEquals(1, agifyResponse.getCount());
        assertEquals("", agifyResponse.getCountryId());
    }

    @Test
    @DisplayName("Should handle negative values")
    void testNegativeValues() {
        agifyResponse.setAge(-1);
        agifyResponse.setCount(-5);
        
        assertEquals(-1, agifyResponse.getAge());
        assertEquals(-5, agifyResponse.getCount());
    }

    @Test
    @DisplayName("Should handle very large values")
    void testLargeValues() {
        agifyResponse.setAge(Integer.MAX_VALUE);
        agifyResponse.setCount(Integer.MAX_VALUE);
        
        assertEquals(Integer.MAX_VALUE, agifyResponse.getAge());
        assertEquals(Integer.MAX_VALUE, agifyResponse.getCount());
    }
}
