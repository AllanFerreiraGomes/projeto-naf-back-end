package com.example.projeto_naf_back.service;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;


    public void enviarEmailSimples(String destinatario, String assunto, LocalDateTime horaDia ) {
        SimpleMailMessage email = new SimpleMailMessage();
        
        // Criando um DateTimeFormatter para formatar a data e hora
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        
        // Formatando a data e hora em uma string
        String dataHoraFormatada = horaDia.format(formatter);
        
        // Montando a mensagem do e-mail com a data formatada
        String mensagem = "Olá! Seu agendamento foi confirmado:\n" + "Data: " + dataHoraFormatada;
        
        // Configurando o e-mail
        email.setTo(destinatario);
        email.setSubject(assunto);
        email.setText(mensagem);
        email.setFrom("allanunifeso@gmail.com");
        
        // Enviando o e-mail
        mailSender.send(email);
    }
}