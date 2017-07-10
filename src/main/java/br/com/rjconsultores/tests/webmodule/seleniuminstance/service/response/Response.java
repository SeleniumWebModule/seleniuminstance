package br.com.rjconsultores.tests.webmodule.seleniuminstance.service.response;

import br.com.rjconsultores.tests.seleniuminstance.exception.SeleniumInstanceException;

public class Response {
	private StatusResponse statusResponse;
	private SeleniumInstanceException instanceException;
	
	public Response() {
		this.statusResponse = StatusResponse.SUCCESS;
	}
	
	public Response(SeleniumInstanceException instanceException){
		this.instanceException = instanceException;
	}

	public StatusResponse getStatusResponse() {
		return statusResponse;
	}
	
	public SeleniumInstanceException getInstanceException() {
		return instanceException;
	}
}
