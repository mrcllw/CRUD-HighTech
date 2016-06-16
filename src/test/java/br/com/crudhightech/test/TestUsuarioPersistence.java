package br.com.crudhightech.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.crudhightech.entity.Usuario;

public class TestUsuarioPersistence {
	
	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("crudHightech");
		EntityManager em = emf.createEntityManager();
		
		Usuario usu = new Usuario();
		usu.setEmail("m@m.com");
		usu.setPassword("123");
		
		em.getTransaction().begin();
		
		em.persist(usu);
		
		em.getTransaction().commit();
		
		em.close();
	}
}
