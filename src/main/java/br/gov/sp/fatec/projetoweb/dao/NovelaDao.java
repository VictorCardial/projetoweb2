package br.gov.sp.fatec.projetoweb.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import br.gov.sp.fatec.projetoweb.entity.Ator;
import br.gov.sp.fatec.projetoweb.entity.Duble;
import br.gov.sp.fatec.projetoweb.entity.Novela;
import br.gov.sp.fatec.projetoweb.entity.Pessoa;

public class NovelaDao{
	
	private EntityManager manager;
    private AtorDao atorDao;
    private DubleDao dubleDao;
    private DiretorDao diretorDao;
    
    public NovelaDao() {
        this(PersistenceManager.getInstance().getEntityManager());
    }
    
    public NovelaDao(EntityManager manager) {
        this.manager = manager;
        atorDao = new AtorDao(manager);
        dubleDao = new DubleDao(manager);
        diretorDao = new DiretorDao();
    }     
    
    public Novela buscar(Long id) {
        return manager.find(Novela.class, id);
    }
    
    public void salvar(Novela novela) throws RollbackException {
        try {
            manager.getTransaction().begin();
            salvarSemCommit(novela);
            manager.flush();
            manager.getTransaction().commit();
        }
        catch(RollbackException e) {
            manager.getTransaction().rollback();
            throw e;
        }
    }
    
    public void salvarSemCommit(Novela novela) {
    	for(Pessoa pessoa: novela.getPessoas()) {
            if(pessoa.getId() == null) {
                if(pessoa instanceof Ator) {
                	atorDao.salvarSemCommit((Ator)pessoa);
                }
                else if (pessoa instanceof Duble) {
                	dubleDao.salvarSemCommit((Duble)pessoa);
                }
            }
        }
    	
    	if(novela.getDiretor() != null &&
    			novela.getDiretor().getId() == null) {
    				diretorDao.salvarSemCommit(novela.getDiretor());
    	}
    	
    	if(novela.getId() == null) {
    		manager.persist(novela);
    	}
    	
    	else {
    		manager.merge(novela);
    	}
    }
    
    public void excluir(Long id) throws RollbackException, RuntimeException{
		Novela novela = manager.find(Novela.class, id);
		if(novela != null) {
			try {
				manager.getTransaction().begin();
				manager.remove(novela);
				manager.getTransaction().commit();
			}
			catch(RollbackException e) {
				manager.getTransaction().rollback();
				throw e;
			}
		}
		else {
			throw new RuntimeException("Novela não encontrada!");
		}
    }    
    
    public Novela buscarNovelaPorNome(String nome) {
    	String consulta = "select n from Novela n where fmg_nome = :nome";
        TypedQuery<Novela> query = manager.createQuery(consulta, Novela.class);
        query.setParameter("nome", nome);
        
        return query.getSingleResult();
    }
    
    public List<Novela> buscarNovelas() {
    	String consulta = "SELECT n FROM Novela n INNER JOIN n.diretor";
    	TypedQuery<Novela> query = manager.createQuery(consulta, Novela.class);
    	
    	return query.getResultList();
    }
    
    public List<Novela> buscarNovelaPorDiretorPorDuracao(String diretor, float duracao) {
    	String consulta = "SELECT n FROM Novela n INNER JOIN n.diretor d WHERE d.nome = :diretor AND n.duracao <= :duracao";
    	TypedQuery<Novela> query = manager.createQuery(consulta, Novela.class);
    	query.setParameter("diretor", diretor).setParameter("duracao", duracao);
 
    	return query.getResultList();
    }
}
