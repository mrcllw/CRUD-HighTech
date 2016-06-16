package br.com.crudhightech.repository;

import java.util.List;

import br.com.crudhightech.entity.Usuario;
import br.com.crudhightech.interfaces.UsuarioDAO;

public class UsuarioDAOJDBC implements UsuarioDAO {

	@Override
	public Usuario save(Usuario usu) {
		return null;
	}

	@Override
	public void delete(Usuario usu) {
		
	}

	@Override
	public List<Usuario> findAll() {
		return null;
	}

	@Override
	public Usuario findById(int id) {
		return null;
	}

	@Override
	public Usuario findByEmail(String email) {
		return null;
	}
}
