package br.com.crudhightech.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Assert;
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
import br.com.crudhightech.exception.ServiceException;
import br.com.crudhightech.interfaces.UsuarioDAO;
import br.com.crudhightech.service.UsuarioService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/springbeans.xml")
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public class TestUsuarioServiceJunitSpringInjection {

	@Autowired
	UsuarioService uSer;
	
	@Autowired
	@Qualifier("usuarioDAOJPA")
	UsuarioDAO uDAO;
	
	@Transactional
	@Test(expected=ServiceException.class)
	public void testDontSave() throws ServiceException{
		Usuario usuario = new Usuario();
		usuario.setEmail("testDontSaveService");
		usuario.setPassword("123");
		
		uDAO.save(usuario);
		
		uSer.save(usuario);
	}
	
	@Transactional
	@Test
	public void testSave() throws ServiceException{
		Usuario usuario = new Usuario();
		usuario.setEmail("testSaveService");
		usuario.setPassword("123");
		
		Usuario usuSave = uSer.save(usuario);
		
		assertNotNull(usuSave.getId());
	}
	
	@Test
	@Transactional
	public void testEdit() throws ServiceException{
		Usuario usuario = new Usuario();
		usuario.setEmail("testEditService");
		usuario.setPassword("123");
		
		Usuario usuSave = uSer.save(usuario);
		
		Usuario usuFind = uSer.findById(usuSave.getId());
		usuFind.setEmail("serviceEditTest");
		
		Usuario usuFinal = uSer.save(usuFind);
		
		Assert.assertNotEquals(usuSave, usuFinal);
	}
	
	@Test(expected=ServiceException.class)
	@Transactional
	public void testDelete() throws ServiceException, DAOException{
		Usuario usuario = new Usuario();
		usuario.setEmail("testDelService");
		usuario.setPassword("123");
		
		Usuario usuDel = uSer.save(usuario);
		
		uSer.delete(usuDel);
		
		uSer.findById(usuDel.getId());
	}
	
	@Test
	@Transactional
	public void testFindById() throws ServiceException{
		Usuario usuario = new Usuario();
		usuario.setEmail("testFindByIdService");
		usuario.setPassword("123");
		
		Usuario usuSave = uSer.save(usuario);
		
		Usuario usuFind = uSer.findById(usuSave.getId());
		
		assertNotNull(usuFind);
	}
	
	@Test(expected=ServiceException.class)
	@Transactional
	public void testNotFindById() throws ServiceException, DAOException{
		Usuario usuario = new Usuario();
		usuario.setEmail("testFindByIdService");
		usuario.setPassword("123");
		
		Usuario usuSave = uSer.save(usuario);
		
		uSer.delete(usuSave);
		
		uSer.findById(usuSave.getId());
	}
	
	@Test
	@Transactional
	public void testFindByEmail() throws ServiceException{
		Usuario usuario = new Usuario();
		usuario.setEmail("testFindByIdService");
		usuario.setPassword("123");
		
		Usuario usuSave = uSer.save(usuario);
		
		Usuario usuFind = uSer.findByEmail(usuSave.getEmail());
		
		assertNotNull(usuFind);
	}
	
	@Test(expected=ServiceException.class)
	@Transactional
	public void testNotFindByEmail() throws ServiceException, DAOException{
		Usuario usuario = new Usuario();
		usuario.setEmail("testFindByIdService");
		usuario.setPassword("123");
		
		Usuario usuSave = uSer.save(usuario);
		
		uSer.delete(usuSave);
		
		uSer.findByEmail(usuSave.getEmail());
	}
	
	@Test
	@Transactional
	public void testFindAll() throws ServiceException{
		Usuario usuario = new Usuario();
		usuario.setEmail("testFindAllService");
		usuario.setPassword("123");
		
		uSer.save(usuario);
		
		List<Usuario> usuFind = uSer.findAll();
		
		assertTrue(usuFind.size() > 0);
	}
	
	@Test(expected=ServiceException.class)
	@Transactional
	public void testNotFindAll() throws ServiceException, DAOException{
		Usuario usuario = new Usuario();
		usuario.setEmail("testFindAllService");
		usuario.setPassword("123");
		
		Usuario usuSave = uSer.save(usuario);
		uSer.delete(usuSave);
		
		uSer.findAll();
	}
}
