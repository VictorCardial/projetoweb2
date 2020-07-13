package br.gov.sp.fatec.projetoweb.dao;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import br.gov.sp.fatec.projetoweb.entity.Duble;

public class DubleDao {
	
    private EntityManager manager;
	
	public DubleDao() {
        manager = PersistenceManager
        		.getInstance().getEntityManager();
    }
	
	public DubleDao(EntityManager manager) {
        this.manager = manager;
    }
	
	public Duble buscar(Long id) {
		return manager.find(Duble.class, id);
	}
	
	public void salvar(Duble duble) throws RollbackException {
        try {
            manager.getTransaction().begin();
            salvarSemCommit(duble);
            manager.flush();
            manager.getTransaction().commit();
        }
        catch(RollbackException e) {
            manager.getTransaction().rollback();
            throw e;
        }
    }
    
    public void salvarSemCommit(Duble duble) {
        if( duble.getId() == null) {
            manager.persist( duble);
        }
        else {
            manager.merge(duble);
        }
    }
	
    public void excluir(Long id) throws RollbackException {
        Duble duble = manager.find(Duble.class, id);
        try {
            manager.getTransaction().begin();
            manager.remove(duble);
            manager.getTransaction().commit();
        }
        catch(RollbackException e) {
            manager.getTransaction().rollback();
            throw e;
        }
    }
    
    public Duble buscarPorNome(String nome) {
        String consulta = "select d from Duble d where pes_nome = :nome";
        TypedQuery<Duble> query = manager.createQuery(consulta, Duble.class);
        query.setParameter("nome", nome);
        
        return query.getSingleResult();
    }
}
