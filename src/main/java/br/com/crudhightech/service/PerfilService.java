package br.com.crudhightech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.crudhightech.entity.Perfil;
import br.com.crudhightech.exception.DAOException;
import br.com.crudhightech.exception.ServiceException;
import br.com.crudhightech.interfaces.PerfilDAO;

@Service
public class PerfilService {
	
	@Autowired
	@Qualifier("perfilDAOJPA")
	PerfilDAO perfilDAO;
	
	public Perfil save(Perfil perfil) throws ServiceException{
		Perfil perfilExistente = perfilDAO.findByDesc(perfil.getDescricao());
		if(perfilExistente != null){
			throw new ServiceException("Perfil já cadastrado.");
		}
		return perfilDAO.save(perfil);
	}
	
	public void delete (Perfil perfil) throws ServiceException{
		Perfil perfilExistente = perfilDAO.findByDesc(perfil.getDescricao());
		if(perfilExistente == null){
			throw new ServiceException("Perfil não existe.");
		}
		try{
			perfilDAO.delete(perfil);
		}catch (DAOException e){
			throw new ServiceException(e);
		}
	}
	
	public List<Perfil> findAll() throws ServiceException{
		List<Perfil> listPerf = perfilDAO.findAll();
		if(listPerf.size() == 0){
			throw new ServiceException("Não existe perfil cadastrados.");
		}
		return listPerf;
	}
	
	public Perfil findById(Integer id) throws ServiceException{
		Perfil perfilBuscado = perfilDAO.findById(id);
		if(perfilBuscado == null){
			throw new ServiceException("Não existe perfil cadastrado com este ID.");
		}
		return perfilBuscado;
	}
	
	public Perfil findByDesc(String desc) throws ServiceException{
		Perfil perfilBuscado = perfilDAO.findByDesc(desc);
		if(perfilBuscado == null){
			throw new ServiceException("Perfil não existe.");
		}
		return perfilBuscado;
	}
}
