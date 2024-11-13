package com.example.projeto_naf_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projeto_naf_back.model.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
}