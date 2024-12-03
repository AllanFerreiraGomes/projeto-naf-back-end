package com.example.projeto_naf_back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.projeto_naf_back.dto.UsuarioDto;
import com.example.projeto_naf_back.enuns.PerfilUsuario;
import com.example.projeto_naf_back.model.Usuario;
import com.example.projeto_naf_back.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;


//    @GetMapping
//    public List<Usuario> getAllUsuarios() {
//    	System.out.println("bateu 222");
//
//        return usuarioService.findAll();
//    }
	@GetMapping
	public ResponseEntity<List<UsuarioDto>> getAllUsers() {
		System.out.println("Bati");
		return new ResponseEntity<>(usuarioService.findAll(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
		Usuario UserResponse = usuarioService.save(usuario);
		if (UserResponse == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
		} else {
			return new ResponseEntity<>(UserResponse, HttpStatus.OK);
		}
	}

	@GetMapping("/perfil/{perfil}")
    public ResponseEntity<List<Usuario>> getUsuariosByPerfil(@PathVariable PerfilUsuario perfil) {
        return ResponseEntity.ok(usuarioService.findByPerfil(perfil));
    }
	
	 @DeleteMapping("/{usuarioId}/admin/{adminId}")
	    public ResponseEntity<Void> deleteUsuario(@PathVariable Long usuarioId, @PathVariable Long adminId) {
	        usuarioService.deleteUsuario(usuarioId, adminId);
	        return ResponseEntity.noContent().build();
	    }

}