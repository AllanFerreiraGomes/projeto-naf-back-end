package com.example.projeto_naf_back.service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projeto_naf_back.dto.SaveAgendamentoDto;
import com.example.projeto_naf_back.enuns.PerfilUsuario;
import com.example.projeto_naf_back.exceptions.UnmatchingIdsException;
import com.example.projeto_naf_back.model.Agendamento;
import com.example.projeto_naf_back.model.Usuario;
import com.example.projeto_naf_back.repository.AgendamentoRepository;
import com.example.projeto_naf_back.repository.UsuarioRepository;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Agendamento> findAll() {
        return agendamentoRepository.findAll();
    }

    public Agendamento save(SaveAgendamentoDto saveAgendamentoDto) {
        // Busca o usuário pelo ID
        Usuario usuario = usuarioRepository.findById(saveAgendamentoDto.getId())
                .orElseThrow(() -> new RuntimeException("Usuário com ID " + saveAgendamentoDto.getId() + " não encontrado."));

        // Cria um novo objeto de agendamento
        Agendamento agendamento = new Agendamento();
        agendamento.setUsuario(usuario);
        agendamento.setDataHora(saveAgendamentoDto.getDataHora());

        try {
            // Validações de lógica de negócio
            validarAgendamento(agendamento);

            // Salva o agendamento no banco de dados
            Agendamento savedAgendamento = agendamentoRepository.save(agendamento);

            // Envia o e-mail de confirmação
            emailService.enviarEmailSimples(
                    usuario.getEmail(),
                    "Confirmação de Agendamento",
                    agendamento.getDataHora()
            );

            // Retorna o agendamento salvo
            return savedAgendamento;

        } catch (UnmatchingIdsException e) {
            throw new RuntimeException("Erro ao salvar o agendamento: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Ocorreu um erro inesperado ao salvar o agendamento.", e);
        }
    }

    private void validarAgendamento(Agendamento agendamento) {
        if (agendamento == null) {
            throw new UnmatchingIdsException("O agendamento não pode ser nulo.");
        }
        if (agendamento.getUsuario() == null) {
            throw new UnmatchingIdsException("O agendamento deve estar associado a um usuário.");
        }
        if (agendamento.getDataHora() == null) {
            throw new UnmatchingIdsException("A data e hora do agendamento são obrigatórias.");
        }
        if (agendamento.getDataHora().isBefore(LocalDateTime.now())) {
            throw new UnmatchingIdsException("Não é possível criar um agendamento para uma data no passado.");
        }

        // Verificar se o agendamento é em um dia da semana permitido (segunda a sexta)
        if (agendamento.getDataHora().getDayOfWeek() == DayOfWeek.SATURDAY
                || agendamento.getDataHora().getDayOfWeek() == DayOfWeek.SUNDAY) {
            throw new UnmatchingIdsException("O agendamento deve ser feito de segunda a sexta-feira.");
        }

        // Verificar se o horário está dentro da janela de 8h às 16h
        if (agendamento.getDataHora().getHour() < 8 || agendamento.getDataHora().getHour() >= 16) {
            throw new UnmatchingIdsException("O agendamento deve ser feito entre 08:00 e 16:00.");
        }

        // Verificar se o agendamento é múltiplo de 1 hora (deve ser no início da hora)
        if (agendamento.getDataHora().getMinute() != 0) {
            throw new UnmatchingIdsException("O agendamento deve ser feito no início da hora.");
        }

        // Verificar se já existe um agendamento no mesmo horário
        if (agendamentoRepository.existsByDataHora(agendamento.getDataHora())) {
            throw new UnmatchingIdsException("Já existe um agendamento neste horário.");
        }
    }

    public void deleteAgendamento(Long agendamentoId, Long usuarioId) {
        // Recupera o agendamento
        Agendamento agendamento = agendamentoRepository.findById(agendamentoId)
                .orElseThrow(() -> new UnmatchingIdsException("Agendamento não encontrado."));

        // Recupera o usuário que está tentando apagar
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new UnmatchingIdsException("Usuário não encontrado."));

        // Verifica se o usuário tem permissão para excluir
        if (!agendamento.getUsuario().getId().equals(usuario.getId())
                && usuario.getPerfil() != PerfilUsuario.ADMINISTRACAO) {
            throw new UnmatchingIdsException("Você não tem permissão para excluir este agendamento.");
        }

        // Apaga o agendamento
        agendamentoRepository.deleteById(agendamentoId);
    }
}
