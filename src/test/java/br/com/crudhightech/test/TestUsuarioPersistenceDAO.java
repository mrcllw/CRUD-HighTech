package br.com.crudhightech.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.crudhightech.entity.Usuario;
import br.com.crudhightech.exception.DAOException;
import br.com.crudhightech.repository.UsuarioDAOJPA;

public class TestUsuarioPersistenceDAO {
	
	public static void main(String[] args) throws DAOException {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("crudHightech");
		EntityManager em = emf.createEntityManager();
		
		Usuario usu = new Usuario();
		usu.setEmail("m@m.com");
		usu.setPassword("123");
		
		UsuarioDAOJPA usuarioDAO = new UsuarioDAOJPA(em);
		
		//SALVAR
		usuarioDAO.save(usu);
		
		//BUSCAR
		Usuario usuMod = usuarioDAO.findById(2);
		System.out.println(usuMod);
		
		//EDITAR
		//usuMod.setEmail("mm@mm.com");
		//usuarioDAO.edit(usuMod);
		//System.out.println(usuMod);
		
		//EXCLUIR
		//usuarioDAO.delete(usuMod);
	}
}
