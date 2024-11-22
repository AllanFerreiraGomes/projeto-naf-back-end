package com.example.projeto_naf_back.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackResponseDto {
    private Long id;
    private String comentario;
    private int nota;
    private LocalDateTime dataHora;
    private Long usuarioId;
}
