package com.example.springai.ai.service;

import com.example.springai.common.dto.ApiResponse;
import com.example.springai.common.dto.AiRequestDto;
import com.example.springai.common.dto.AiResponseDto;
// TODO: Re-enable Spring AI imports once dependency is properly configured
// import org.springframework.ai.chat.ChatClient;
// import org.springframework.ai.chat.ChatResponse;
// import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AiService {
    
    // TODO: Re-enable ChatClient once Spring AI dependency is properly configured
    // @Autowired
    // private ChatClient chatClient;
    
    public ApiResponse<AiResponseDto> generateResponse(AiRequestDto request) {
        try {
            long startTime = System.currentTimeMillis();
            
            // TODO: Replace with actual Spring AI implementation
            // Prompt prompt = new Prompt(request.getPrompt());
            // ChatResponse response = chatClient.call(prompt);
            
            // Mock response for now
            String mockResponse = "This is a mock AI response for: " + request.getPrompt();
            long processingTime = System.currentTimeMillis() - startTime;
            
            AiResponseDto responseDto = new AiResponseDto(
                mockResponse,
                request.getModel(),
                100, // Mock token count
                processingTime
            );
            
            return ApiResponse.success(responseDto, "Mock AI response generated successfully");
            
        } catch (Exception e) {
            return ApiResponse.error("Failed to generate AI response: " + e.getMessage());
        }
    }
    
    public ApiResponse<AiResponseDto> generateResponse(String prompt) {
        AiRequestDto request = new AiRequestDto(prompt);
        return generateResponse(request);
    }
}
