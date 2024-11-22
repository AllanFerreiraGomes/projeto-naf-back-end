package com.example.projeto_naf_back.controller;

import com.example.projeto_naf_back.dto.FeedbackResponseDto;
import com.example.projeto_naf_back.dto.SaveFeedbackDto;
import com.example.projeto_naf_back.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<FeedbackResponseDto> createFeedback(@RequestBody SaveFeedbackDto saveFeedbackDto) {
        return ResponseEntity.ok(feedbackService.save(saveFeedbackDto));
    }

    @GetMapping
    public ResponseEntity<List<FeedbackResponseDto>> getAllFeedbacks() {
        return ResponseEntity.ok(feedbackService.findAll());
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<FeedbackResponseDto>> getFeedbacksByUsuarioId(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(feedbackService.findByUsuarioId(usuarioId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable Long id) {
        feedbackService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
