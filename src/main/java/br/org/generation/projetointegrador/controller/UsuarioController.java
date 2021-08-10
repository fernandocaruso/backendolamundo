package br.org.generation.projetointegrador.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.generation.projetointegrador.model.Usuario;
import br.org.generation.projetointegrador.model.UsuarioLogin;
import br.org.generation.projetointegrador.repository.UsuarioRepository;
import br.org.generation.projetointegrador.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioService usuarioService; 
	
	@GetMapping("/all")
    public ResponseEntity<List<Usuario>> GetAll() {
        return ResponseEntity.ok(usuarioRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> GetById(@PathVariable long id){
        return usuarioRepository.findById(id).map(resp -> ResponseEntity.ok(resp))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Usuario>> GetByNome(@PathVariable String nome){
    	return ResponseEntity.ok(usuarioRepository.findAllByNomeContainingIgnoreCase(nome));
    }
	
	@PostMapping("/logar")
	public ResponseEntity<UsuarioLogin> Authentication(@RequestBody Optional<UsuarioLogin> usuarioLogin){
		return usuarioService.Logar(usuarioLogin).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> Post(@RequestBody Usuario usuario){
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.CadastrarUsuario(usuario));
	}
	
	@PutMapping("/alterar")
    public ResponseEntity<Usuario> putUsuario(@RequestBody Usuario usuario){
        Optional<Usuario> updateUsuario = usuarioService.atualizarUsuario(usuario);
        try {
            return ResponseEntity.ok(updateUsuario.get());
        } catch (Exception erro) {
            return ResponseEntity.badRequest().build();
        	
        }
        
			}
				}
