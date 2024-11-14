package com.example.projeto_naf_back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.projeto_naf_back.model.Agendamento;
import com.example.projeto_naf_back.service.AgendamentoService;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {
    @Autowired
    private AgendamentoService agendamentoService;

    @GetMapping
    public List<Agendamento> getAllAgendamentos() {
        return agendamentoService.findAll();
    }

    @PostMapping
    public ResponseEntity<Agendamento> createAgendamento(@RequestBody Agendamento agendamento) {
        return ResponseEntity.ok(agendamentoService.save(agendamento));
    }
}