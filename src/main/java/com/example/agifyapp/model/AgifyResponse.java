package com.example.agifyapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AgifyResponse {
    private String name;
    private Integer age;
    private Integer count;
    
    @JsonProperty("country_id")
    private String countryId;

    // Default constructor
    public AgifyResponse() {}

    // Constructor with parameters
    public AgifyResponse(String name, Integer age, Integer count, String countryId) {
        this.name = name;
        this.age = age;
        this.count = count;
        this.countryId = countryId;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    @Override
    public String toString() {
        return "AgifyResponse{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", count=" + count +
                ", countryId='" + countryId + '\'' +
                '}';
    }
}
