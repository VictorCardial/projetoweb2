package br.gov.sp.fatec.projetoweb.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.gov.sp.fatec.projetoweb.dao.AtorDao;
import br.gov.sp.fatec.projetoweb.dao.UsuarioDao;
import br.gov.sp.fatec.projetoweb.entity.Ator;
import br.gov.sp.fatec.projetoweb.entity.Usuario;


public class CriarAtorController extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        // Recupera o parâmetro id (de trabalho?id=<valor>)
        Long id = Long.valueOf(req.getParameter("id"));
        // Busca trabalho com o id
        AtorDao atorDao = new AtorDao();
        Ator ator = atorDao.buscar(id);

        UsuarioDao usuarioDao = new UsuarioDao();
        List<Usuario> user = usuarioDao.listaUsuarios();
        // Usamos o Jackson para transformar o objeto em um JSON (String)
        ObjectMapper mapper = new ObjectMapper();
        String atorJson = mapper.writeValueAsString(ator);
        // Formatamos a resposta
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.setStatus(200);
        PrintWriter out = resp.getWriter();
        System.out.println(user);
        out.print(atorJson);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        // Recuperamos o corpo da requisição e transformamos o JSON em objeto
        try{
        ObjectMapper mapper = new ObjectMapper();
        Ator ator = mapper.readValue(req.getReader(), Ator.class);
        // Salvamos no Banco de Dados
        AtorDao atorDao = new AtorDao();
        atorDao.salvar(ator);
        // Retornamos o registro gerado
        String atorJson = mapper.writeValueAsString(ator);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        // O código 201 requer que retornemos um header de Location
        resp.setStatus(201);
        String location = req.getServerName() + ":" + req.getServerPort() 
                + req.getContextPath() + "/ator?id=" + ator.getId();
        resp.setHeader("Location", location);
        PrintWriter out = resp.getWriter();
        out.print(atorJson);
        out.flush();
        }
        catch(Exception e) {
            resp.setStatus(400);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) 
        throws ServletException, IOException {
        try{
        ObjectMapper mapper = new ObjectMapper();
        Ator ator = mapper.readValue(req.getReader(), Ator.class);
        AtorDao atorDao = new AtorDao();
        atorDao.excluir(ator.getId());
        
        String atorJson = mapper.writeValueAsString(ator);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.setStatus(201);
        String location = req.getServerName() + ":" + req.getServerPort() 
                + req.getContextPath() + "/ator?id=" + ator.getId();
        resp.setHeader("Location", location);
        PrintWriter out = resp.getWriter();
        out.print(atorJson);
        out.flush();
        }
        catch(Exception e) {
            resp.setStatus(400);
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) 
        throws ServletException, IOException {
        try{
        ObjectMapper mapper = new ObjectMapper();
        Ator ator = mapper.readValue(req.getReader(), Ator.class);
        AtorDao atorDao = new AtorDao();
        Ator atorInstancia = atorDao.update(ator); 
        atorDao.salvar(atorInstancia);
        resp.setStatus(204);
        // Retornamos o registro gerado
        /*String atorJson = mapper.writeValueAsString(ator);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        
        String location = req.getServerName() + ":" + req.getServerPort() 
                + req.getContextPath() + "/ator?id=" + atorInstancia.getId();
        resp.setHeader("Location", location);
        PrintWriter out = resp.getWriter();
        out.print(atorJson);
        out.flush();*/
        }
        catch(Exception e) {
            resp.setStatus(400);
        }

    }
}