package com.example.projeto_naf_back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projeto_naf_back.enuns.PerfilUsuario;
import com.example.projeto_naf_back.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	boolean existsByEmail(String email);
	
	  List<Usuario> findByPerfil(PerfilUsuario perfil);

}
