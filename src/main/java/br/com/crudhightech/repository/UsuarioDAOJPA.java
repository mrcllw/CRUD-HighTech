package br.com.crudhightech.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.crudhightech.entity.Usuario;
import br.com.crudhightech.exception.DAOException;
import br.com.crudhightech.interfaces.UsuarioDAO;

@Repository
public class UsuarioDAOJPA implements UsuarioDAO{
	
	@PersistenceContext
	private EntityManager em;
	
	public UsuarioDAOJPA() {
	}
	
	public UsuarioDAOJPA(EntityManager em) {
		this.em = em;
	}
	
	@Transactional
	public Usuario save(Usuario usu){
		Usuario u = em.merge(usu);
		return u;
	}
	
	@Transactional
	public void delete(Usuario usu) throws DAOException{
		try{
			Usuario usuManaged = em.getReference(Usuario.class, usu.getId());
			em.remove(usuManaged);
		}catch (Exception e){
			throw new DAOException("Não foi possivel remover.", e);
		}
	}
	
	public List<Usuario> findAll(){
		Query findAll = em.createQuery("SELECT u FROM Usuario u");
		return findAll.getResultList();
	}
	
	public Usuario findById(int id){
		return em.find(Usuario.class, id);
	}

	@Override
	public Usuario findByEmail(String email) {
		try{
			Query findByEmail = em.createQuery("SELECT u FROM Usuario u WHERE u.email=:emailParam");
			findByEmail.setParameter("emailParam", email);
			findByEmail.setMaxResults(1);
			return (Usuario)findByEmail.getSingleResult();
		}catch (Exception e){
			//throw new DAOException("Email não encontrado.", e);
			return null;
		}
	}
}
