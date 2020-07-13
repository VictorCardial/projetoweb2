package br.gov.sp.fatec.projetoweb.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import br.gov.sp.fatec.projetoweb.entity.Ator;
import br.gov.sp.fatec.projetoweb.entity.Duble;
import br.gov.sp.fatec.projetoweb.entity.Filme;
import br.gov.sp.fatec.projetoweb.entity.Pessoa;

public class FilmeDao {
	
	private EntityManager manager;
    private AtorDao atorDao;
    private DubleDao dubleDao;
    private DiretorDao diretorDao;
    
    public FilmeDao() {
        this(PersistenceManager.getInstance().getEntityManager());
    }
    
    public FilmeDao(EntityManager manager) {
        this.manager = manager;
        atorDao = new AtorDao(manager);
        dubleDao = new DubleDao(manager);
        diretorDao = new DiretorDao();
    }     
    
    public Filme buscar(Long id) {
        return manager.find(Filme.class, id);
    }
    
    public void salvar(Filme filme) throws RollbackException {
        try {
            manager.getTransaction().begin();
            salvarSemCommit(filme);
            manager.flush();
            manager.getTransaction().commit();
        }
        catch(RollbackException e) {
            manager.getTransaction().rollback();
            throw e;
        }
    }
    
    public void salvarSemCommit(Filme filme) {

    	for(Pessoa pessoa: filme.getPessoas()) {
            if(pessoa.getId() == null) {
                if(pessoa instanceof Ator) {
                	atorDao.salvarSemCommit((Ator)pessoa);
                }
                else if (pessoa instanceof Duble) {
                	dubleDao.salvarSemCommit((Duble)pessoa);
                }
            }
        }
    	
    	if(filme.getDiretor() != null &&
    			filme.getDiretor().getId() == null) {
    		diretorDao.salvarSemCommit(filme.getDiretor());
    	}
    	
    	if(filme.getId() == null) {
    		manager.persist(filme);
    	}
    	
    	else {
    		manager.merge(filme);
    	}
    }
	
	public void excluir(Long id) throws RollbackException, RuntimeException{
		Filme filme = manager.find(Filme.class, id);
		if(filme != null) {
			try {
				manager.getTransaction().begin();
				manager.remove(filme);
				manager.getTransaction().commit();
			}
			catch(RollbackException e) {
				manager.getTransaction().rollback();
				throw e;
			}
		}
		else {
			throw new RuntimeException("Filme não encontrado!");
		}
	}
	
	public Filme buscarFilmePorNome(String nome) {
		String queryText = "select f from Filme f where f.nome like :nome";
		TypedQuery<Filme> query = manager.createQuery(queryText, Filme.class);
		query.setParameter("nome", nome);
		
		return query.getSingleResult();
	}	
	
	public List<Filme> buscarFilmesPorDiretor(String diretor) {
		String queryText = "SELECT f FROM Filme f INNER JOIN f.diretor d WHERE d.nome = :diretor";

		TypedQuery<Filme> query = manager.createQuery(queryText, Filme.class);
		query.setParameter("diretor", diretor);
		
		return query.getResultList();
	}
	
	public List<Filme> buscarFilmePorDublePorDuracao(String duble, float duracao) {
    	String consulta = "SELECT f FROM Filme f INNER JOIN f.pessoas p WHERE p.nome = :duble AND f.duracao <= :duracao";
    	TypedQuery<Filme> query = manager.createQuery(consulta, Filme.class);
    	
    	query.setParameter("duble", duble).setParameter("duracao", duracao);
 
    	return query.getResultList();
    }
}
