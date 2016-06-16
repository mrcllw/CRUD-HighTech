package br.com.crudhightech.interfaces;

import java.util.List;

import br.com.crudhightech.entity.Perfil;
import br.com.crudhightech.exception.DAOException;

public interface PerfilDAO {
	public Perfil save(Perfil perfil);
	public void delete(Perfil perfil) throws DAOException;
	public List<Perfil> findAll();
	public Perfil findById(int id);
	public Perfil findByDesc(String desc);
}
