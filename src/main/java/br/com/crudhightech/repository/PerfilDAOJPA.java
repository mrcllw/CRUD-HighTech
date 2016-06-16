package br.com.crudhightech.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.crudhightech.entity.Perfil;
import br.com.crudhightech.exception.DAOException;
import br.com.crudhightech.interfaces.PerfilDAO;

@Repository
public class PerfilDAOJPA implements PerfilDAO{

	@PersistenceContext
	private EntityManager em;
	
	public PerfilDAOJPA() {
	}
	
	public PerfilDAOJPA(EntityManager em) {
		this.em = em;
	}
	
	@Transactional
	@Override
	public Perfil save(Perfil perfil) {
		Perfil perfilSave = em.merge(perfil);
		return perfilSave;
	}

	@Transactional
	@Override
	public void delete(Perfil perfil) throws DAOException {
		try{
			Perfil perfilManaged = em.getReference(Perfil.class, perfil.getId());
			em.remove(perfilManaged);
		}catch(Exception e){
			throw new DAOException("Não foi possivel remover.", e);
		}
	}

	@Override
	public List<Perfil> findAll() {
		Query findAll = em.createQuery("SELECT p FROM Perfil p");
		return findAll.getResultList();
	}

	@Override
	public Perfil findById(int id) {
		return em.find(Perfil.class, id);
	}

	@Override
	public Perfil findByDesc(String desc) {
		try{
			Query findByDesc = em.createQuery("SELECT p FROM Perfil p WHERE p.descricao=:descParam");
			findByDesc.setParameter("descParam", desc);
			findByDesc.setMaxResults(1);
			return (Perfil)findByDesc.getSingleResult();
		}catch(Exception e){
			//throw new DAOException("Descricão não encontrada.", e);
			return null;
		}
	}
}
