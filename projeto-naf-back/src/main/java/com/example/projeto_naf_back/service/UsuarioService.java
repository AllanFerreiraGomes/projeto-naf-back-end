package com.example.projeto_naf_back.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projeto_naf_back.exceptions.UnmatchingIdsException;
import com.example.projeto_naf_back.model.Usuario;
import com.example.projeto_naf_back.repository.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;

	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	public Usuario save(Usuario usuario) {
		// Verifica se o e-mail já existe no banco
		if (!usuarioRepository.existsByEmail(usuario.getEmail())) {
			return usuarioRepository.save(usuario); // Salva se o e-mail não existir
		}

		// Retorna o usuário não salvo para indicar que o e-mail já está em uso
		throw new UnmatchingIdsException("O e-mail já está cadastrado: " + usuario.getEmail());
	}
	
	public void delete(Long usuarioId) {
	    // Verifica se o usuário existe antes de tentar excluir
	    Usuario usuario = usuarioRepository.findById(usuarioId)
	            .orElseThrow(() -> new RuntimeException("Usuário com ID " + usuarioId + " não encontrado."));

	    // Remove o usuário do banco de dados
	    usuarioRepository.delete(usuario);
	}

}