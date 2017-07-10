package br.com.rjconsultores.tests.seleniuminstance.service.test.utilities;

import org.mockito.Mockito;

import br.com.rjconsultores.tests.seleniuminstance.exception.FieldRequireException;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.System;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.service.request.OperationType;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.service.request.Request;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.service.response.ResponseError;

public class PopulateUtil {
	public static System populateSystemWithNull(String fieldName) {
		System system = new System();

		system.setName(fieldName.equals("name") ? null : GenerateUtil.getRandomString(10));
		system.setAddress(fieldName.equals("address") ? null : GenerateUtil.getRandomString(8));
		system.setPort(fieldName.equals("port") ? null : GenerateUtil.getRandomInt(4).toString());

		return system;
	}
	
	public static ResponseError populateResponseError(String fieldName) {
		return new ResponseError(new FieldRequireException(fieldName));
	}
	
	public static Request populateRequest(System system) {
		Request request = new Request();
		request.setSystem(system);
		request.setOperationType(Mockito.any(OperationType.class));

		return request;
	}
}
