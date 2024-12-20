package com.example.projeto_naf_back.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projeto_naf_back.dto.UsuarioDto;
import com.example.projeto_naf_back.enuns.PerfilUsuario;
import com.example.projeto_naf_back.exceptions.UnmatchingIdsException;
import com.example.projeto_naf_back.model.Usuario;
import com.example.projeto_naf_back.repository.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;

    public List<UsuarioDto> findAll() {
        return usuarioRepository.findAll()
                .stream()
                .map(usuario -> new UsuarioDto(
                        usuario.getId(),
                        usuario.getNome(),
                        usuario.getEmail(),
                        usuario.getPerfil().toString()
                ))
                .collect(Collectors.toList());
    }

	public Usuario save(Usuario usuario) {
		// Verifica se o e-mail já existe no banco
		if (!usuarioRepository.existsByEmail(usuario.getEmail())) {
			return usuarioRepository.save(usuario); // Salva se o e-mail não existir
		}

		// Retorna o usuário não salvo para indicar que o e-mail já está em uso
		throw new UnmatchingIdsException("O e-mail já está cadastrado: " + usuario.getEmail());
	}
	
	public void deleteUsuario(Long usuarioId, Long solicitanteId) {
	    // Busca o usuário que será excluído
	    Usuario usuario = usuarioRepository.findById(usuarioId)
	            .orElseThrow(() -> new UnmatchingIdsException("Usuário com ID " + usuarioId + " não encontrado."));

	    // Busca o solicitante (quem está tentando excluir o usuário)
	    Usuario solicitante = usuarioRepository.findById(solicitanteId)
	            .orElseThrow(() -> new UnmatchingIdsException("Solicitante com ID " + solicitanteId + " não encontrado."));

	    // Verifica se o solicitante tem permissão para excluir usuários
	    if (solicitante.getPerfil() != PerfilUsuario.ADMINISTRACAO) {
	        throw new UnmatchingIdsException("Apenas administradores podem excluir usuários.");
	    }

	    // Exclui o usuário
	    usuarioRepository.deleteById(usuarioId);
	}


	  public List<Usuario> findByPerfil(PerfilUsuario perfil) {
	        return usuarioRepository.findByPerfil(perfil);
	    }
	
}