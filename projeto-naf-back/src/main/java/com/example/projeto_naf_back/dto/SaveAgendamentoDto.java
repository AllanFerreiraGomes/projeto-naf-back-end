package com.example.projeto_naf_back.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class SaveAgendamentoDto {
	private LocalDateTime dataHora;
	
	private Long id;

}
