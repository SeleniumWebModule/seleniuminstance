package br.com.rjconsultores.tests.webmodule.seleniuminstance.factory;

import br.com.rjconsultores.tests.seleniuminstance.enums.OperationType;
import br.com.rjconsultores.tests.seleniuminstance.enums.SourceEvent;
import br.com.rjconsultores.tests.seleniuminstance.exception.RequiredResourceException;
import br.com.rjconsultores.tests.seleniuminstance.exception.SeleniumInstanceException;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.System;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.service.request.Request;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.service.response.Response;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.service.response.ResponseError;

public class RegisterFactory {
	private static RegisterFactory INSTANCE;
	private SourceEvent sourceEvent;
	
	private String msgErrorRequestNullValue;
	private String msgErrorSystemNullValue;
	private String msgErrorOperationTypeNullValue;
	
	RegisterFactory() {
		sourceEvent = SourceEvent.REGISTER_SERVICE;
		
		msgErrorRequestNullValue = "O request deve ser um valor v�lido e nulo n�o � um valor v�lido.";
		msgErrorSystemNullValue = "O sistema deve ser um valor v�lido e nulo n�o � um valor v�lido.";
		msgErrorOperationTypeNullValue = "O tipo de opera��o deve ser um valor v�lido e nulo n�o � um valor v�lido.";
	}
	

	public static RegisterFactory INSTANCE() {
		if (INSTANCE == null) {
			INSTANCE = new RegisterFactory();
		}

		return INSTANCE;
	}

	public Response registerScreen(Request request) {
		try {
			if (request == null) {
				return new ResponseError(new RequiredResourceException(sourceEvent, msgErrorRequestNullValue));
			}
			
			System systemRequest = request.getSystem();
			if (systemRequest == null) {
				return new ResponseError(new RequiredResourceException(sourceEvent, msgErrorSystemNullValue));
			}
			
			systemRequest.validate();
			
			if (request.getOperationType() == null) {
				return new ResponseError(new RequiredResourceException(sourceEvent, msgErrorOperationTypeNullValue));
			}
			
			if (request.getOperationType().equals(OperationType.INSERT)) {
				return doRegister(systemRequest);
			}
		} catch (SeleniumInstanceException instanceException) {
			return new ResponseError(instanceException);
		}
		
		return new ResponseError(new SeleniumInstanceException(sourceEvent, "Ocorreu um erro n�o esperado"));
	}
	
	private Response doRegister(System system) {
		if (system == null) {
			return new ResponseError(new RequiredResourceException(sourceEvent, msgErrorSystemNullValue));
		}
		
		return DBFactory.INSTANCE().registerSystem(system);
	}
}
