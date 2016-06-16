package br.com.crudhightech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.crudhightech.entity.Usuario;
import br.com.crudhightech.exception.DAOException;
import br.com.crudhightech.exception.ServiceException;
import br.com.crudhightech.interfaces.UsuarioDAO;

@Service
public class UsuarioService {

	@Autowired
	@Qualifier("usuarioDAOJPA")
	UsuarioDAO usuarioDAO;
	
	public Usuario save(Usuario usuario) throws ServiceException{
		Usuario usuarioExistente = usuarioDAO.findByEmail(usuario.getEmail());
		if(usuarioExistente != null){
			throw new ServiceException("Usuario já cadastrado.");
		}
		return usuarioDAO.save(usuario);
	}
	
	public void delete(Usuario usuario) throws ServiceException{
		Usuario usuarioExistente = usuarioDAO.findByEmail(usuario.getEmail());
		if(usuarioExistente == null){
			throw new ServiceException("Usuario não existe.");
		}
		try{
			usuarioDAO.delete(usuario);
		}catch (DAOException e){
			throw new ServiceException(e);
		}
	}
	
	public List<Usuario> findAll() throws ServiceException{
		List<Usuario> listUsu = usuarioDAO.findAll();
		if(listUsu.size() == 0){
			throw new ServiceException("Não existe usuarios cadastrados.");
		}
		return listUsu;
	}
	
	public Usuario findById(Integer id) throws ServiceException{
		Usuario usuarioBuscado = usuarioDAO.findById(id);
		if(usuarioBuscado == null){
			throw new ServiceException("Não existe usuario cadastrado com este ID.");
		}
		return usuarioBuscado;
	}
	
	public Usuario findByEmail(String email) throws ServiceException{
		Usuario usuarioBuscado = usuarioDAO.findByEmail(email);
		if(usuarioBuscado == null){
			throw new ServiceException("Usuario não existe.");
		}
		return usuarioBuscado;
	}
}
