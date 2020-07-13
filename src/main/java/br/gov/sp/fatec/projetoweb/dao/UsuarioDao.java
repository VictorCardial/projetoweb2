package br.gov.sp.fatec.projetoweb.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import br.gov.sp.fatec.projetoweb.entity.Usuario;

public class UsuarioDao {
    
    private EntityManager manager;
	
	public UsuarioDao() {
        manager = PersistenceManager
        		.getInstance().getEntityManager();
    }
	
	public UsuarioDao(EntityManager manager) {
        this.manager = manager;
    }
	
	public Usuario buscar(Long id) {
		return manager.find(Usuario.class, id);
	}
	
	public void salvar(Usuario user) throws RollbackException {
        try {
            manager.getTransaction().begin();
            salvarSemCommit(user);
            manager.flush();
            manager.getTransaction().commit();
        }
        catch(RollbackException e) {
            manager.getTransaction().rollback();
            throw e;
        }
    }
    
    public void salvarSemCommit(Usuario user) {
        if(user.getId() == null) {
            manager.persist(user);
        }
        else {
            manager.merge(user);
        }
    }

    @PersistenceContext
    public Usuario update(Usuario user){
       return manager.merge(user);
    }
    
    public void excluir(Long id) throws RollbackException {
        Usuario user = manager.find(Usuario.class, id);
        try {
            manager.getTransaction().begin();
            manager.remove(user);
            manager.getTransaction().commit();
        }
        catch(RollbackException e) {
            manager.getTransaction().rollback();
            throw e;
        }
    }
         
    public List<Usuario> listaUsuarios() {
        String consulta = "SELECT * FROM usr_usuario";
        TypedQuery<Usuario> query = manager.createQuery(consulta, Usuario.class);
        return query.getResultList();
    }
  
}