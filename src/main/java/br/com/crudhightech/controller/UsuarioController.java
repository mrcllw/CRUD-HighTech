package br.com.crudhightech.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.crudhightech.entity.Usuario;
import br.com.crudhightech.exception.ServiceException;
import br.com.crudhightech.messages.MessageUtils;
import br.com.crudhightech.service.UsuarioService;

@Controller
public class UsuarioController {
	
	private Usuario usu = new Usuario();
	private List<Usuario> listaUsu;
	
	@Autowired
	private UsuarioService uSer;
	
	@PostConstruct
	public void start() throws ServiceException{
		setListaUsu(uSer.findAll());
	}
	
	public void salvar(){
		try {
			Usuario uSave = uSer.save(usu);
			if(usu.getId() == null){
				listaUsu.add(uSave);
			}
			usu = new Usuario();
			MessageUtils.messageInfo(MessageUtils.SALVO_SUCESSO);
		} catch (ServiceException e) {
			MessageUtils.messageErro(MessageUtils.FALHA_SALVAR);
			e.printStackTrace();
		}
	}
	
	public void cancelar(){
		this.usu = new Usuario();
	}
	
	public void excluir(Usuario usu) throws ServiceException{
		try {
			uSer.delete(usu);
			listaUsu.remove(usu);
			MessageUtils.messageInfo(MessageUtils.EXCLUIDO_SUCESSO);
		} catch (ServiceException e) {
			MessageUtils.messageErro(MessageUtils.FALHA_EXCLUSAO);
			e.printStackTrace();
		}
	}
	
	public void editar(Usuario usu){
		this.usu = usu;
	}
	
	public Usuario getUsu() {
		return usu;
	}
	public void setUsu(Usuario usu) {
		this.usu = usu;
	}
	public List<Usuario> getListaUsu() {
		return listaUsu;
	}
	public void setListaUsu(List<Usuario> listaUsu) {
		this.listaUsu = listaUsu;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((listaUsu == null) ? 0 : listaUsu.hashCode());
		result = prime * result + ((usu == null) ? 0 : usu.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioController other = (UsuarioController) obj;
		if (listaUsu == null) {
			if (other.listaUsu != null)
				return false;
		} else if (!listaUsu.equals(other.listaUsu))
			return false;
		if (usu == null) {
			if (other.usu != null)
				return false;
		} else if (!usu.equals(other.usu))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "UsuarioController [usu=" + usu + ", listaUsu=" + listaUsu + "]";
	}
}
