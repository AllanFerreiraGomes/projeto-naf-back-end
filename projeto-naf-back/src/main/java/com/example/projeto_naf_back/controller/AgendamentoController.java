package com.example.projeto_naf_back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.projeto_naf_back.dto.AgendamentoDto;
import com.example.projeto_naf_back.dto.SaveAgendamentoDto;
import com.example.projeto_naf_back.model.Agendamento;
import com.example.projeto_naf_back.service.AgendamentoService;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {
    @Autowired
    private AgendamentoService agendamentoService;

    @GetMapping
    public List<AgendamentoDto> getAllAgendamentos() {
        return agendamentoService.findAll();
    }

    @PostMapping
    public ResponseEntity<Agendamento> createAgendamento(@RequestBody SaveAgendamentoDto saveAgendamentoDto) {
        Agendamento agendamentoSalvo = agendamentoService.save(saveAgendamentoDto);
        return ResponseEntity.ok(agendamentoSalvo);
    }

    @DeleteMapping("/{agendamentoId}/usuario/{usuarioId}")
    public ResponseEntity<Void> deleteAgendamento(@PathVariable Long agendamentoId, @PathVariable Long usuarioId) {
    	System.out.println("bati");
        agendamentoService.deleteAgendamento(agendamentoId, usuarioId);
        return ResponseEntity.noContent().build();
    }

}