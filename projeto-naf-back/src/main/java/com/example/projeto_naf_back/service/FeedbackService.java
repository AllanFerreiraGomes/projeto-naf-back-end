package com.example.projeto_naf_back.service;

import com.example.projeto_naf_back.dto.FeedbackResponseDto;
import com.example.projeto_naf_back.dto.SaveFeedbackDto;
import com.example.projeto_naf_back.model.Feedback;
import com.example.projeto_naf_back.model.Usuario;
import com.example.projeto_naf_back.repository.FeedbackRepository;
import com.example.projeto_naf_back.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public FeedbackResponseDto save(SaveFeedbackDto saveFeedbackDto) {
        // Verifica se o usuário existe
        Usuario usuario = usuarioRepository.findById(saveFeedbackDto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário com ID " + saveFeedbackDto.getUsuarioId() + " não encontrado."));

        // Cria o objeto Feedback
        Feedback feedback = new Feedback();
        feedback.setComentario(saveFeedbackDto.getComentario());
        feedback.setNota(saveFeedbackDto.getNota());
        feedback.setDataHora(LocalDateTime.now());
        feedback.setUsuario(usuario);

        // Salva no banco de dados
        Feedback savedFeedback = feedbackRepository.save(feedback);

        // Retorna o DTO de resposta
        return new FeedbackResponseDto(
                savedFeedback.getId(),
                savedFeedback.getComentario(),
                savedFeedback.getNota(),
                savedFeedback.getDataHora(),
                savedFeedback.getUsuario().getId()
        );
    }

    public List<FeedbackResponseDto> findAll() {
        return feedbackRepository.findAll().stream()
                .map(feedback -> new FeedbackResponseDto(
                        feedback.getId(),
                        feedback.getComentario(),
                        feedback.getNota(),
                        feedback.getDataHora(),
                        feedback.getUsuario().getId()
                ))
                .collect(Collectors.toList());
    }

    public List<FeedbackResponseDto> findByUsuarioId(Long usuarioId) {
        return feedbackRepository.findByUsuarioId(usuarioId).stream()
                .map(feedback -> new FeedbackResponseDto(
                        feedback.getId(),
                        feedback.getComentario(),
                        feedback.getNota(),
                        feedback.getDataHora(),
                        feedback.getUsuario().getId()
                ))
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        if (!feedbackRepository.existsById(id)) {
            throw new RuntimeException("Feedback com ID " + id + " não encontrado.");
        }
        feedbackRepository.deleteById(id);
    }
}
