package com.example.springai.common.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AiRequestDto {
    
    @NotBlank(message = "Prompt is required")
    @Size(max = 1000, message = "Prompt must not exceed 1000 characters")
    private String prompt;
    
    private String model = "gpt-3.5-turbo";
    private Integer maxTokens = 150;
    private Double temperature = 0.7;

    // Constructors
    public AiRequestDto() {}

    public AiRequestDto(String prompt) {
        this.prompt = prompt;
    }

    public AiRequestDto(String prompt, String model, Integer maxTokens, Double temperature) {
        this.prompt = prompt;
        this.model = model;
        this.maxTokens = maxTokens;
        this.temperature = temperature;
    }

    // Getters and Setters
    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getMaxTokens() {
        return maxTokens;
    }

    public void setMaxTokens(Integer maxTokens) {
        this.maxTokens = maxTokens;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }
}
