package br.com.rjconsultores.tests.webmodule.seleniuminstance.service.response;

import br.com.rjconsultores.tests.seleniuminstance.exception.SeleniumInstanceException;

public class ResponseError extends Response {

	public ResponseError(SeleniumInstanceException instanceException) {
		super(instanceException);
	}
	
	@Override
	public StatusResponse getStatusResponse() {
		return StatusResponse.ERROR;
	}
	
	public SeleniumInstanceException getInstanceException() {
		return super.getInstanceException();
	}
}
