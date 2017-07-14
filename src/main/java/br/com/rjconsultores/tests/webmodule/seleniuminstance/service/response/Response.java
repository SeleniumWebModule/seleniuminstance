package br.com.rjconsultores.tests.webmodule.seleniuminstance.service.response;

import br.com.rjconsultores.tests.seleniuminstance.exception.SeleniumInstanceException;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.System;

public class Response {
	private StatusResponse statusResponse;
	private SeleniumInstanceException instanceException;
	private System system;
	
	public Response(System system) {
		this.statusResponse = StatusResponse.SUCCESS;
		this.system = system;
	}
	
	public Response(SeleniumInstanceException instanceException) {
		this.instanceException = instanceException;
	}

	public StatusResponse getStatusResponse() {
		return statusResponse;
	}
	
	public SeleniumInstanceException getInstanceException() {
		return instanceException;
	}
	
	public System getSystem() {
		return system;
	}
}
