package com.example.projeto_naf_back.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projeto_naf_back.exceptions.UnmatchingIdsException;
import com.example.projeto_naf_back.model.Agendamento;
import com.example.projeto_naf_back.repository.AgendamentoRepository;

@Service
public class AgendamentoService {
	@Autowired
	private AgendamentoRepository agendamentoRepository;

	@Autowired
	private EmailService emailService;

	public List<Agendamento> findAll() {
		return agendamentoRepository.findAll();
	}

//	public Agendamento saave(Agendamento agendamento) {
//		Agendamento savedAgendamento = agendamentoRepository.save(agendamento);
//
//		// Enviar notificação por e-mail
//
//		emailService.enviarEmailSimples("allanunifeso@gmail.com", // Trocar pelo e-mail real
//				"Confirmação de Agendamento", agendamento.getDataHora());
//
//		return savedAgendamento;
//	}

	public Agendamento save(Agendamento agendamento) {
		try {
			// Validações de lógica de negócio
			validarAgendamento(agendamento);

			// Salva o agendamento no banco de dados
			Agendamento savedAgendamento = agendamentoRepository.save(agendamento);

			emailService.enviarEmailSimples("allanunifeso@gmail.com", // Trocar pelo e-mail real
					"Confirmação de Agendamento", agendamento.getDataHora());

			// Retorna o agendamento salvo
			return savedAgendamento;

		} catch (UnmatchingIdsException e) {
			// Tratar erros de validação
			throw new RuntimeException("Erro ao salvar o agendamento: " + e.getMessage(), e);
		} catch (Exception e) {
			// Tratar outros erros inesperados
			throw new RuntimeException("Ocorreu um erro inesperado ao salvar o agendamento.", e);
		}
	}

	private void validarAgendamento(Agendamento agendamento) {
		if (agendamento == null) {
			throw new UnmatchingIdsException("O agendamento não pode ser nulo.");
		}
		if (agendamento.getDataHora() == null) {
			throw new UnmatchingIdsException("A data e hora do agendamento são obrigatórias.");
		}
		if (agendamento.getDataHora().isBefore(LocalDateTime.now())) {
			throw new UnmatchingIdsException("Não é possível criar um agendamento para uma data no passado.");
		}
		// Adicione mais validações se necessário
	}

}