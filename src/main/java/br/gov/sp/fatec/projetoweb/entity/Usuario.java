package br.gov.sp.fatec.projetoweb.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;


import javax.persistence.Table;

@Entity
@Table(name = "usr_usuario")
@AttributeOverride(name = "id", column = @Column(name = "usr_id"))
public class Usuario extends Main{

	@Column(name = "usr_login")
	private String login;

	@Column(name = "usr_senha")
    private String senha;

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }
}
