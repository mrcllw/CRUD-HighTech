package br.com.crudhightech.messages;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MessageUtils {
	
	public static final String EXCLUIDO_SUCESSO = "Excluido com sucesso!";
	public static final String FALHA_EXCLUSAO = "Não foi possivel excluir.";
	public static final String SALVO_SUCESSO = "Salvo com sucesso!";
	public static final String FALHA_SALVAR = "Não foi possivel salvar.";
	
	
	public static void messageInfo(String msg){
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public static void messageErro(String msg){
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public static void messageAlert(String msg){
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, msg, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}
