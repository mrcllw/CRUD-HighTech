package br.com.crudhightech.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crudhightech.entity.Perfil;
import br.com.crudhightech.service.PerfilService;

@Component
public class PerfilConverter implements Converter{

	@Autowired
	PerfilService pSer;
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String idTela) {
		try {
			return pSer.findById(Integer.parseInt(idTela));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
  

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object objPerfil) {
		try{
			Perfil perfil = (Perfil) objPerfil;
			return perfil.getId().toString();
		}catch (Exception e){
			e.printStackTrace();
			return "";
		}
	}
}
