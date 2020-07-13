package br.gov.sp.fatec.projetoweb.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.gov.sp.fatec.projetoweb.dao.FilmeDao;
import br.gov.sp.fatec.projetoweb.entity.Filme;



public class BuscarFilmePorDiretorController extends HttpServlet {

    private static final long serialVersionUID = 1L;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException { 
        

        // Recupera o parâmetro id (de trabalho?id=<valor>)
        String nomeDiretor = String.valueOf(req.getParameter("nomeDiretor"));
        // Busca trabalho com o id
        FilmeDao filmeDao = new FilmeDao();
        List<Filme> filme = filmeDao.buscarFilmesPorDiretor(nomeDiretor);
        // Usamos o Jackson para transformar o objeto em um JSON (String)
        ObjectMapper mapper = new ObjectMapper();
            
        String filmeJson = mapper.writeValueAsString(filme);
        
        // Formatamos a resposta
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.setStatus(200);
        PrintWriter out = resp.getWriter();
        out.print(filmeJson);
        out.flush();
        
        


    
            
    }
}