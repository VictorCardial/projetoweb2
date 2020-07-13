package br.gov.sp.fatec.projetoweb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="flm_filme")
public class Filme extends Filmagem{
	
	@Column (name="flm_descricao", nullable = false, length = 100)
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}