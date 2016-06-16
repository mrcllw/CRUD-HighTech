package br.com.crudhightech.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import br.com.crudhightech.entity.Usuario;
import br.com.crudhightech.exception.DAOException;
import br.com.crudhightech.interfaces.UsuarioDAO;

//Declarando que Spring que executa os testes
@RunWith(SpringJUnit4ClassRunner.class)
//Carregando contexto Spring
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/springbeans.xml")
//Permite Spring gerenciar transações
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public class TestUsuarioJunitSpringInjection {

	@Autowired
	@Qualifier("usuarioDAOJPA")
	UsuarioDAO uDAO;
	
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
	@Transactional
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
