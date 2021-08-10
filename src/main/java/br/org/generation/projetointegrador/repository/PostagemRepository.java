package br.org.generation.projetointegrador.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.generation.projetointegrador.model.Postagem;

@Repository
public interface PostagemRepository extends JpaRepository <Postagem, Long> {
	
public List<Postagem> findAllByTituloContainingIgnoreCase(String titulo);

public List<Postagem> findAllByTextoContainingIgnoreCase(String texto);

public List<Postagem> findAllByAgendaContainingIgnoreCase(String agenda);

public List<Postagem> findAllByContatoContainingIgnoreCase(String contato);
	

	
	

}
