package br.org.generation.projetointegrador.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.generation.projetointegrador.model.Postagem;
import br.org.generation.projetointegrador.repository.PostagemRepository;
import br.org.generation.projetointegrador.service.PostagemService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/postagem")
public class PostagemController {

	@Autowired
	private PostagemRepository repository;
	
	@Autowired
	private PostagemService postagemService;

	@GetMapping
	public ResponseEntity<List<Postagem>> GetAll() {
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{id}")

	public ResponseEntity<Postagem> GetById(@PathVariable long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());

	}

	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> GetByTitulo(@PathVariable String titulo) {
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));

	}

	@GetMapping("/texto/{texto}")
	public ResponseEntity<List<Postagem>> GetByTexto(@PathVariable String texto) {
		return ResponseEntity.ok(repository.findAllByTextoContainingIgnoreCase(texto));

	}

	@GetMapping("/agenda/{agenda}")
	public ResponseEntity<List<Postagem>> GetByAgenda(@PathVariable String agenda) {
		return ResponseEntity.ok(repository.findAllByAgendaContainingIgnoreCase(agenda));

	}

	@GetMapping("/contato/{contato}")
	public ResponseEntity<List<Postagem>> GetByContato(@PathVariable String contato) {
		return ResponseEntity.ok(repository.findAllByContatoContainingIgnoreCase(contato));

	}

	@PostMapping
	public ResponseEntity<Postagem> post(@RequestBody Postagem postagem) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));

	}

	@PutMapping
	public ResponseEntity<Postagem> put(@RequestBody Postagem postagem) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(postagem));

	}

	@PutMapping("/curtir/{id}") // metodo para curtir postagem
	public ResponseEntity<Postagem> putCurtirPostagemId(@PathVariable Long id) {

		return ResponseEntity.status(HttpStatus.OK).body(postagemService.curtir(id));

	}

	@PutMapping("/descurtir/{id}") // metodo para descurtir postagem
	public ResponseEntity<Postagem> putDescurtirPostagemId(@PathVariable Long id) {

		return ResponseEntity.status(HttpStatus.OK).body(postagemService.descurtir(id));

	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}
}
