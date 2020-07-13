package br.gov.sp.fatec.projetoweb.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "fmg_filmagem")
@Inheritance(strategy = InheritanceType.JOINED)
@AttributeOverride(name = "id", column = @Column(name = "fmg_id"))

public class Filmagem extends Main {
	@Column (name="fmg_nome", length = 50)
	private String nome;
	
	@Column (name="fmg_ano")
	private Date ano;
	
	@Column (name="fmg_duracao")
	private float duracao;
    
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "atu_atuacao",
			joinColumns = { @JoinColumn(name = "fmg_id") },
			inverseJoinColumns = { @JoinColumn(name = "pes_id") })
	private Set<Pessoa> pessoas;
    
    @JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "diretor")
	private Diretor diretor;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getAno() {
		return ano;
	}

	public void setAno(Date ano) {
		this.ano = ano;
	}

	public float getDuracao() {
		return duracao;
	}

	public void setDuracao(float duracao) {
		this.duracao = duracao;
	}

	public Set<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(Set<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public Diretor getDiretor() {
		return diretor;
	}

	public void setDiretor(Diretor diretor) {
		this.diretor = diretor;
	}	
}
