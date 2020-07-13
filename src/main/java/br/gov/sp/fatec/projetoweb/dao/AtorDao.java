package br.gov.sp.fatec.projetoweb.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import br.gov.sp.fatec.projetoweb.entity.Ator;


public class AtorDao {
	
	private EntityManager manager;
	
	public AtorDao() {
        manager = PersistenceManager
        		.getInstance().getEntityManager();
    }
	
	public AtorDao(EntityManager manager) {
        this.manager = manager;
    }
	
	public Ator buscar(Long id) {
		return manager.find(Ator.class, id);
	}
	
	public void salvar(Ator ator) throws RollbackException {
        try {
            manager.getTransaction().begin();
            salvarSemCommit(ator);
            manager.flush();
            manager.getTransaction().commit();
        }
        catch(RollbackException e) {
            manager.getTransaction().rollback();
            throw e;
        }
    }
    
    public void salvarSemCommit(Ator ator) {
        if(ator.getId() == null) {
            manager.persist(ator);
        }
        else {
            manager.merge(ator);
        }
    }

    @PersistenceContext
    public Ator update(Ator ator){
       return manager.merge(ator);
    }
    
    public void excluir(Long id) throws RollbackException {
        Ator ator = manager.find(Ator.class, id);
        try {
            manager.getTransaction().begin();
            manager.remove(ator);
            manager.getTransaction().commit();
        }
        catch(RollbackException e) {
            manager.getTransaction().rollback();
            throw e;
        }
    }
        
    public Ator buscarPorNome(String nome) {
        String consulta = "SELECT a FROM Ator a WHERE pes_nome = :nome";
        TypedQuery<Ator> query = manager.createQuery(consulta, Ator.class);
        query.setParameter("nome", nome);
        
        return query.getSingleResult();
    }
    
    public Ator buscarAtorPorFamaPorNome(String fama, String nome) {
    	String consulta = "SELECT a FROM Ator a WHERE fama = :fama AND nome LIKE :nome";
        TypedQuery<Ator> query = manager.createQuery(consulta, Ator.class);
        query.setParameter("fama", fama).setParameter("nome", nome);
        
        return query.getSingleResult();
    }
    
    public List<Ator> buscarAtorPorFilmagensParticipadasPorFama(String filmagem, String fama) {
    	String consulta = "SELECT a FROM Ator a INNER JOIN a.filmagemParticipadas f WHERE f.nome = :filmagem AND a.fama = :fama";
        TypedQuery<Ator> query = manager.createQuery(consulta, Ator.class);
        query.setParameter("filmagem", filmagem).setParameter("fama", fama);
        
        return query.getResultList();
    }
}