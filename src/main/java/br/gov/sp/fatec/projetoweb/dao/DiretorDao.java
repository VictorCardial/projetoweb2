package br.gov.sp.fatec.projetoweb.dao;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import br.gov.sp.fatec.projetoweb.entity.Diretor;


public class DiretorDao {
	
	private EntityManager manager;
	
	public DiretorDao() {
        manager = PersistenceManager
        		.getInstance().getEntityManager();
    }
	
	public DiretorDao(EntityManager manager) {
        this.manager = manager;
    }
	
	public Diretor buscar(Long id) {
		return manager.find(Diretor.class, id);
	}
	
	public void salvar(Diretor diretor) throws RollbackException {
        try {
            manager.getTransaction().begin();
            salvarSemCommit(diretor);
            manager.flush();
            manager.getTransaction().commit();
        }
        catch(RollbackException e) {
            manager.getTransaction().rollback();
            throw e;
        }
    }
    
    public void salvarSemCommit(Diretor diretor) {
        if(diretor.getId() == null) {
            manager.persist(diretor);
        }
        else {
            manager.merge(diretor);
        }
    }
    
    public void excluir(Long id) throws RollbackException {
        Diretor diretor = manager.find(Diretor.class, id);
        try {
            manager.getTransaction().begin();
            manager.remove(diretor);
            manager.getTransaction().commit();
        }
        catch(RollbackException e) {
            manager.getTransaction().rollback();
            throw e;
        }
    }
    
    public Diretor buscarFilmagensPorDiretorPorTitulo(String diretor) {
    	String queryText = "SELECT d FROM Diretor d INNER JOIN d.filmagensDirigidas f WHERE d.nome LIKE :nome";

		TypedQuery<Diretor> query = manager.createQuery(queryText, Diretor.class);
		query.setParameter("nome", diretor);

		return query.getSingleResult();
    }
}
