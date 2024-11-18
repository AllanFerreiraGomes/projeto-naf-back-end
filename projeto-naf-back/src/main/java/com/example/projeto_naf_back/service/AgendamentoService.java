package com.example.projeto_naf_back.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public Agendamento save(Agendamento agendamento) {
		Agendamento savedAgendamento = agendamentoRepository.save(agendamento);

		// Enviar notificação por e-mail
		
		emailService.enviarEmailSimples("allanunifeso@gmail.com", // Trocar pelo e-mail real
				"Confirmação de Agendamento", agendamento.getDataHora());

		return savedAgendamento;
	}
}