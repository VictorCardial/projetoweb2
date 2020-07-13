package br.gov.sp.fatec.projetoweb.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.gov.sp.fatec.projetoweb.dao.AtorDao;
import br.gov.sp.fatec.projetoweb.dao.DiretorDao;
import br.gov.sp.fatec.projetoweb.dao.DubleDao;
import br.gov.sp.fatec.projetoweb.dao.FilmeDao;
import br.gov.sp.fatec.projetoweb.dao.NovelaDao;
import br.gov.sp.fatec.projetoweb.entity.Ator;
import br.gov.sp.fatec.projetoweb.entity.Diretor;
import br.gov.sp.fatec.projetoweb.entity.Duble;
import br.gov.sp.fatec.projetoweb.entity.Filme;
import br.gov.sp.fatec.projetoweb.entity.Novela;
import br.gov.sp.fatec.projetoweb.entity.Pessoa;

public class MainController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
            
        Ator ator = new Ator();
		ator.setNome("Luciano Santos");
		ator.setCpf(46990126809l);
		ator.setFama("Musica");
		
		Ator ator2 = new Ator();
		ator2.setNome("Leandro Flavio");
		ator2.setCpf(95897465213l);
		ator2.setFama("Musica");
		
		Duble duble = new Duble();
		duble.setNome("Victor Cardial");
		duble.setCpf(45698763205l);
		duble.setEspecialidade("Luta corpo a corpo");

		Duble duble2 = new Duble();
		duble2.setNome("Henrique Alfredo");
		duble2.setCpf(598745665l);
		duble2.setEspecialidade("dança");
		
		Diretor diretor = new Diretor();
		diretor.setNome("Lula da Silva");
		diretor.setCpf(157157157l);
		
		Diretor diretor2 = new Diretor();
		diretor2.setNome("Sabha Santos");
		diretor2.setCpf(7898945627l);
		
		Novela novela = new Novela();
		novela.setAno(new Date(01 / 02 / 2015));
		novela.setNome("Carminha");
		novela.setDuracao(2.30f);
		novela.setCapitulo(1l);
		novela.setDescricaoCap("A inveja mata");
		novela.setDiretor(diretor);
		novela.setPessoas(new HashSet<Pessoa>());
		novela.getPessoas().add(ator);
		novela.getPessoas().add(duble);
		
		Novela novela2 = new Novela();
		novela2.setAno(new Date(01 / 02 / 2015));
		novela2.setNome("The run");
		novela2.setDuracao(4.8f);
		novela2.setCapitulo(5l);
		novela2.setDescricaoCap("Last in world");
		novela2.setDiretor(diretor2);
		novela2.setPessoas(new HashSet<Pessoa>());
		novela2.getPessoas().add(ator);
		novela2.getPessoas().add(ator2);
		novela2.getPessoas().add(duble2);
		novela2.getPessoas().add(duble);
		
		Filme filme = new Filme();
		filme.setAno(new Date(01 / 06 / 2020));
		filme.setNome("Super Dog");
		filme.setDuracao(4.30f);
		filme.setDescricao("Um cachorro pelos outros");
		filme.setDiretor(diretor);
		filme.setPessoas(new HashSet<Pessoa>());
		filme.getPessoas().add(ator);
		filme.getPessoas().add(ator2);
		filme.getPessoas().add(duble2);
		
		Filme filme2 = new Filme();
		filme2.setAno(new Date(01 / 06 / 2020));
		filme2.setNome("Super Man");
		filme2.setDuracao(2.2f);
		filme2.setDescricao("Um super Heroi");
		filme2.setDiretor(diretor);
		filme2.setPessoas(new HashSet<Pessoa>());
		filme2.getPessoas().add(ator);
		filme2.getPessoas().add(duble);
		
		AtorDao atorDao = new AtorDao();
		atorDao.salvar(ator);
		atorDao.salvar(ator2);
		
		DubleDao dubleDao = new DubleDao();
		dubleDao.salvar(duble);
		dubleDao.salvar(duble2);
		
		DiretorDao diretorDao = new DiretorDao();
		diretorDao.salvar(diretor);
		diretorDao.salvar(diretor2);
		
		NovelaDao novelaDao = new NovelaDao();
		novelaDao.salvar(novela);
		novelaDao.salvar(novela2);
		
		FilmeDao filmeDao = new FilmeDao();
		filmeDao.salvar(filme);
		filmeDao.salvar(filme2);
		
		//novelaDao.buscarNovelaPorDiretorPorDuracao("Lula da Silva", 3.5f);
		
		//filmeDao.buscarFilmePorDublePorDuracao("Victor Cardial", 3f);
		
		//atorDao.buscarAtorPorFilmagensParticipadasPorFama("Carminha", "Musica");
		
		
		//List<Filme> filmes;
		        
    }
    
}