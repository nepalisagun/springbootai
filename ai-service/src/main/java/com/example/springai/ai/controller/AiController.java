package com.example.springai.ai.controller;

import com.example.springai.common.dto.ApiResponse;
import com.example.springai.common.dto.AiRequestDto;
import com.example.springai.common.dto.AiResponseDto;
import com.example.springai.ai.service.AiService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
@CrossOrigin(origins = "*")
public class AiController {
    
    @Autowired
    private AiService aiService;
    
    @PostMapping("/generate")
    public ResponseEntity<ApiResponse<AiResponseDto>> generateResponse(@Valid @RequestBody AiRequestDto request) {
        ApiResponse<AiResponseDto> response = aiService.generateResponse(request);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    
    @PostMapping("/chat")
    public ResponseEntity<ApiResponse<AiResponseDto>> chat(@RequestBody String prompt) {
        ApiResponse<AiResponseDto> response = aiService.generateResponse(prompt);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("AI Service is running");
    }
}
