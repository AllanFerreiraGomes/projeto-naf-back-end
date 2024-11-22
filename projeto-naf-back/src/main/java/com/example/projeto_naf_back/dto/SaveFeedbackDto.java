package com.example.projeto_naf_back.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveFeedbackDto {
    private Long usuarioId; // ID do usuário associado
    private String comentario;
    private int nota; // Nota de 1 a 5
}
