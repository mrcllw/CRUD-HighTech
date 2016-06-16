package br.com.crudhightech.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.crudhightech.entity.Perfil;
import br.com.crudhightech.exception.ServiceException;
import br.com.crudhightech.messages.MessageUtils;
import br.com.crudhightech.service.PerfilService;

@Controller
public class PerfilController {
	
	private Perfil perfil = new Perfil();
	private List<Perfil> listaPerfil;
	
	@Autowired
	private PerfilService pSer;
	
	@PostConstruct
	public void start() throws ServiceException{
		setListaPerfil(pSer.findAll());
	}
	
	public void salvar(){
		try{
			Perfil pSave = pSer.save(perfil);
			if(perfil.getId() == null){
				listaPerfil.add(pSave);
			}
			perfil = new Perfil();
			MessageUtils.messageInfo(MessageUtils.SALVO_SUCESSO);
		}catch(ServiceException e){
			MessageUtils.messageErro(MessageUtils.FALHA_SALVAR);
		}
	}
	
	public void excluir(Perfil perfil) throws ServiceException{
		try{
			pSer.delete(perfil);
			listaPerfil.remove(perfil);
			MessageUtils.messageInfo(MessageUtils.EXCLUIDO_SUCESSO);
		}catch(ServiceException e){
			MessageUtils.messageErro(MessageUtils.FALHA_EXCLUSAO);
		}
	}
	
	public void editar(Perfil perfil){
		this.perfil = perfil;
	}
	
	public Perfil getPerfil() {
		return perfil;
	}
	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	public List<Perfil> getListaPerfil() {
		return listaPerfil;
	}
	public void setListaPerfil(List<Perfil> listaPerfil) {
		this.listaPerfil = listaPerfil;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((listaPerfil == null) ? 0 : listaPerfil.hashCode());
		result = prime * result + ((perfil == null) ? 0 : perfil.hashCode());
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
		PerfilController other = (PerfilController) obj;
		if (listaPerfil == null) {
			if (other.listaPerfil != null)
				return false;
		} else if (!listaPerfil.equals(other.listaPerfil))
			return false;
		if (perfil == null) {
			if (other.perfil != null)
				return false;
		} else if (!perfil.equals(other.perfil))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "PerfilController [perfil=" + perfil + ", listaPerfil=" + listaPerfil + "]";
	}
}
