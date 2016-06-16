package br.com.crudhightech.interfaces;

import java.util.List;

import br.com.crudhightech.entity.Usuario;
import br.com.crudhightech.exception.DAOException;

public interface UsuarioDAO {
	public Usuario save(Usuario usu);
	public void delete(Usuario usu) throws DAOException;
	public List<Usuario> findAll();
	public Usuario findById(int id);
	public Usuario findByEmail(String email);
}
