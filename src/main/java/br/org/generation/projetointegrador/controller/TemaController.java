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

import br.org.generation.projetointegrador.model.Tema;
import br.org.generation.projetointegrador.repository.TemaRepository;

@RestController
@RequestMapping("/tema")
@CrossOrigin(origins="*", allowedHeaders="*")
public class TemaController {

	@Autowired
	private TemaRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Tema>> GetAll(){
		return ResponseEntity.ok(repository.findAll());
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Tema> GetById(@PathVariable long id){
		return repository.findById(id).map(resp->ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/temapostagem/{temapostagem}")
	public ResponseEntity<List<Tema>> GetByTemaPostagem(@PathVariable String temapostagem){
		return ResponseEntity.ok(repository.findAllByTemaPostagemContainingIgnoreCase(temapostagem));
		
	}
	
	@GetMapping("/ambientacao/{ambientacao}")
	public ResponseEntity<List<Tema>> GetByAmbientacao(@PathVariable String ambientacao){
		return ResponseEntity.ok(repository.findAllByAmbientacaoContainingIgnoreCase(ambientacao));
	}
	
	@GetMapping("/palavrachave/{palavrachave}")
	public ResponseEntity<List<Tema>> GetByPalavraChave(@PathVariable String palavrachave ){
		return ResponseEntity.ok(repository.findAllByPalavraChaveContainingIgnoreCase(palavrachave));
	}
	
	@PostMapping
	public ResponseEntity <Tema> post(@RequestBody Tema tema){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(tema));
	}
	
	@PutMapping
	public ResponseEntity<Tema> put(@RequestBody Tema tema){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(tema));
	}
	
	@DeleteMapping("/{id}")
	public void delete (@PathVariable long id) {
		repository.deleteById(id);
	}
}

