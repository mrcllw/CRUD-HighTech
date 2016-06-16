package br.com.crudhightech.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.crudhightech.entity.Perfil;
import br.com.crudhightech.exception.DAOException;
import br.com.crudhightech.repository.PerfilDAOJPA;

public class TestPerfilSpringContextManual {

	public static void main(String[] args) throws DAOException {
		//Carregando factory pelo contexto do spring
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("file:src/main/webapp/WEB-INF/springbeans.xml");
		EntityManagerFactory emf = (EntityManagerFactory)ctx.getBean("entityManagerFactory");
		EntityManager em = emf.createEntityManager();
		
		Perfil perfil = new Perfil();
		perfil.setDescricao("DEV");
		
		PerfilDAOJPA perfilDAO = new PerfilDAOJPA(em);
		
		//SALVAR
		perfilDAO.save(perfil);
		
		//BUSCAR
		Perfil perMod = perfilDAO.findById(4);
		System.out.println(perMod);
		
		//EDITAR
		//usuMod.setEmail("mm@mm.com");
		//usuarioDAO.edit(usuMod);
		//System.out.println(usuMod);
		
		//EXCLUIR
		//usuarioDAO.delete(usuMod);
		
		ctx.close();
		}
}
