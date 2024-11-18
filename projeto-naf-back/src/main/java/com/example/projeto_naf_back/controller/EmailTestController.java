package com.example.projeto_naf_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.projeto_naf_back.service.EmailService;

@RestController
@RequestMapping("/test")
public class EmailTestController {

    @Autowired
    private EmailService emailService;

//    @GetMapping("/send-email")
//    public ResponseEntity<String> sendTestEmail() {
//        try {
//        	System.out.println("aquii");
//            emailService.enviarEmailSimples(
//                "allanunifeso@gmail.com", // Enviar para o próprio e-mail
//                "Teste de Configuração",
//                "Este é um teste de envio de e-mail utilizando o Gmail."
//            );
//            return ResponseEntity.ok("E-mail enviado com sucesso!");
//        } catch (Exception e) {
//            return ResponseEntity.status(500).body("Erro ao enviar e-mail: " + e.getMessage());
//        }
//    }
}
