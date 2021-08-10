package br.org.generation.projetointegrador.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name ="tb_postagem")
public class Postagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private long id_usuario;
	
	@NotNull
	private long tipoPostagem;
	
	@NotNull
	private String titulo;
	
	@NotNull
	private String texto;
	
	@NotNull
	private String agenda; // horario de funcionamento
	
	@NotNull
	private String contato;
	
	@PositiveOrZero
	private int curtidas;
	
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Tema tema; 
	
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Usuario usuario; 

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(long id_usuario) {
		this.id_usuario = id_usuario;
	}

	public long getTipoPostagem() {
		return tipoPostagem;
	}

	public void setTipoPostagem(long tipoPostagem) {
		this.tipoPostagem = tipoPostagem;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getAgenda() {
		return agenda;
	}

	public void setAgenda(String agenda) {
		this.agenda = agenda;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getCurtidas() {
		return curtidas;
	}

	public void setCurtidas(int curtidas) {
		this.curtidas = curtidas;
	} 
	
}



