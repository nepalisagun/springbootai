package com.example.springai.common.dto;

public class AiResponseDto {
    
    private String response;
    private String model;
    private Integer tokensUsed;
    private Long processingTimeMs;

    // Constructors
    public AiResponseDto() {}

    public AiResponseDto(String response) {
        this.response = response;
    }

    public AiResponseDto(String response, String model, Integer tokensUsed, Long processingTimeMs) {
        this.response = response;
        this.model = model;
        this.tokensUsed = tokensUsed;
        this.processingTimeMs = processingTimeMs;
    }

    // Getters and Setters
    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getTokensUsed() {
        return tokensUsed;
    }

    public void setTokensUsed(Integer tokensUsed) {
        this.tokensUsed = tokensUsed;
    }

    public Long getProcessingTimeMs() {
        return processingTimeMs;
    }

    public void setProcessingTimeMs(Long processingTimeMs) {
        this.processingTimeMs = processingTimeMs;
    }
}
