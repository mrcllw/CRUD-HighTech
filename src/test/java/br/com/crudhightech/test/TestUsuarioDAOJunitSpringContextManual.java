package br.com.crudhightech.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.crudhightech.entity.Usuario;
import br.com.crudhightech.exception.DAOException;
import br.com.crudhightech.repository.UsuarioDAOJPA;

public class TestUsuarioDAOJunitSpringContextManual {

	private ClassPathXmlApplicationContext ctx;
	private EntityManager em;
	private UsuarioDAOJPA uDAO;
	
	@Before
	public void init(){
		ctx = new ClassPathXmlApplicationContext("file:src/main/webapp/WEB-INF/springbeans.xml");
		EntityManagerFactory emf = (EntityManagerFactory)ctx.getBean("entityManagerFactory");
		em = emf.createEntityManager();
		uDAO = new UsuarioDAOJPA(em);
	}
	
	@After
	public void end(){
		ctx.close();
	}

	@Test
	@Ignore
	public void testSave(){	
		Usuario usu = new Usuario();
		usu.setEmail("testSaveJunit");
		usu.setPassword("123");
		
		Usuario usuSave = uDAO.save(usu);
		
		assertNotNull(usuSave.getId());
	}
	
	@Test
	public void testEdit(){
		Usuario usu = new Usuario();
		usu.setEmail("testFindForEditJunit");
		usu.setPassword("123");
		
		Usuario usuSave = uDAO.save(usu);
		
		Usuario usuFind = uDAO.findById(usuSave.getId());
		usuFind.setEmail("testEditJunit");
		
		Usuario usuEdit = uDAO.save(usuFind);
		
		Assert.assertNotEquals(usuSave, usuEdit);
	}
	
	@Test
	@Ignore
	public void testDelete() throws DAOException{
		Usuario usu = new Usuario();
		usu.setEmail("testDeleteJunit");
		usu.setPassword("123");
		
		Usuario usuSave = uDAO.save(usu);
		
		uDAO.delete(usuSave);
		
		Usuario usuFind = uDAO.findById(usuSave.getId());
		
		assertEquals(usuFind, null);
	}
	
	@Test
	@Ignore
	public void testFindById(){	
		Usuario usu = new Usuario();
		usu.setEmail("testFindByIdJunit");
		usu.setPassword("123");
		
		Usuario usuSave = uDAO.save(usu);
		Integer usuIdFind = usuSave.getId();
		
		Usuario usuFind = uDAO.findById(usuIdFind);
		
		assertEquals(usuFind, usuSave);
	}
	
	@Test
	@Ignore
	public void testFindAll(){
		Usuario usu = new Usuario();
		usu.setEmail("testFindAllJunit");
		usu.setPassword("123");
		
		uDAO.save(usu);
		
		List<Usuario> listTest = uDAO.findAll();
		
		assertTrue(listTest.size() > 0);
	}
}
