package br.com.crudhightech.exception;

public class ServiceException extends Exception{

	public ServiceException(String msg) {
		super(msg);
	}

	public ServiceException(DAOException e) {
		super(e);
	}

}
