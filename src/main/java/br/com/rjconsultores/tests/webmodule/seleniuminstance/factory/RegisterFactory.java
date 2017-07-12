package br.com.rjconsultores.tests.webmodule.seleniuminstance.factory;

import br.com.rjconsultores.tests.seleniuminstance.enums.SourceEvent;
import br.com.rjconsultores.tests.seleniuminstance.exception.ResourceRequiredException;
import br.com.rjconsultores.tests.seleniuminstance.exception.SeleniumInstanceException;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.System;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.service.request.Request;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.service.response.Response;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.service.response.ResponseError;

public class RegisterFactory {
	private static RegisterFactory INSTANCE;
	private SourceEvent sourceEvent;
	
	RegisterFactory() {
		sourceEvent = SourceEvent.REGISTER_SERVICE;
	}
	

	public static RegisterFactory INSTANCE() {
		if (INSTANCE == null) {
			INSTANCE = new RegisterFactory();
		}

		return INSTANCE;
	}

	public Response registerScreen(Request request) {
		try {
			System systemRequest = request.getSystem();
			if (systemRequest == null) {
				return new ResponseError(new ResourceRequiredException(sourceEvent,
						"É necessário informar um sistema."));
			}
			
			systemRequest.validate();
		} catch (SeleniumInstanceException instanceException) {
			return new ResponseError(instanceException);
		}

		return new Response();
	}
}
