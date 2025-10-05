package com.example.springai.ai.service;

import com.example.springai.common.dto.ApiResponse;
import com.example.springai.common.dto.AiRequestDto;
import com.example.springai.common.dto.AiResponseDto;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AiService {
    
    @Autowired
    private ChatClient chatClient;
    
    public ApiResponse<AiResponseDto> generateResponse(AiRequestDto request) {
        try {
            long startTime = System.currentTimeMillis();
            
            Prompt prompt = new Prompt(request.getPrompt());
            ChatResponse response = chatClient.call(prompt);
            
            long processingTime = System.currentTimeMillis() - startTime;
            
            AiResponseDto responseDto = new AiResponseDto(
                response.getResult().getOutput().getContent(),
                request.getModel(),
                response.getMetadata().getUsage().getTotalTokens(),
                processingTime
            );
            
            return ApiResponse.success(responseDto, "AI response generated successfully");
            
        } catch (Exception e) {
            return ApiResponse.error("Failed to generate AI response: " + e.getMessage());
        }
    }
    
    public ApiResponse<AiResponseDto> generateResponse(String prompt) {
        AiRequestDto request = new AiRequestDto(prompt);
        return generateResponse(request);
    }
}
