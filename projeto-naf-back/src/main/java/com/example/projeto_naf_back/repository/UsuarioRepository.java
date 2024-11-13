package com.example.projeto_naf_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projeto_naf_back.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}