package br.gov.sp.fatec.projetoweb.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@DiscriminatorValue(value = "D")
public class Duble extends Pessoa {
	
	@Column(name = "dub_especialidade")
	private String especialidade;
    
    @JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "pessoas")
	private Set<Filmagem> filmagemParticipadas;

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public Set<Filmagem> getFilmagemParticipadas() {
		return filmagemParticipadas;
	}

	public void setFilmagemParticipadas(Set<Filmagem> filmagemParticipadas) {
		this.filmagemParticipadas = filmagemParticipadas;
	}
}
