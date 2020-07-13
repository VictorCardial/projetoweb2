package br.gov.sp.fatec.projetoweb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="nov_novela")
public class Novela extends Filmagem{
	
	@Column (name="nov_capitulo")
	private Long capitulo;
	
	@Column (name="nov_desc_cap", length = 100)
	private String descricaoCap;

	public Long getCapitulo() {
		return capitulo;
	}

	public void setCapitulo(Long capitulo) {
		this.capitulo = capitulo;
	}

	public String getDescricaoCap() {
		return descricaoCap;
	}

	public void setDescricaoCap(String descricaoCap) {
		this.descricaoCap = descricaoCap;
	}
	
	
}
