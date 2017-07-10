package br.com.rjconsultores.tests.seleniuminstance.service.test.register;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import br.com.rjconsultores.tests.seleniuminstance.exception.ResourceRequiredException;
import br.com.rjconsultores.tests.seleniuminstance.service.test.utilities.GenerateUtil;
import br.com.rjconsultores.tests.seleniuminstance.service.test.utilities.PopulateUtil;
import br.com.rjconsultores.tests.seleniuminstance.service.test.utilities.VerifyUtil;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.Attribute;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.Component;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.Screen;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.System;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.service.Register;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.service.request.OperationType;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.service.request.Request;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.service.response.Response;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.service.response.ResponseError;

public class RegisterServiceReal {
	
	private Register register;
	private Request request;
	
	@Before
	public void init() {
		register = new Register();
	}
	
	@Test
	public void validateRequestSuccessReal() {
		request = new Request();
		System system = new System();

		system.setAddress(GenerateUtil.getRandomString(8));
		system.setName(GenerateUtil.getRandomString(8));
		system.setPort(GenerateUtil.getRandomInt(4).toString());

		Screen screen = new Screen();
		screen.setName(GenerateUtil.getRandomString(10));

		Component component = new Component();
		component.setName(GenerateUtil.getRandomString(10));
		component.setDescricao(GenerateUtil.getRandomString(40));

		Attribute attribute = new Attribute();
		attribute.setName(GenerateUtil.getRandomString(10));
		attribute.setDescricao(GenerateUtil.getRandomString(40));

		component.registerAttribute(attribute);
		screen.registerComponent(component);
		system.registerScreen(screen);

		request.setSystem(system);
		request.setOperationType(Mockito.any(OperationType.class));

		Response responseExpected = new Response();
		Response responseActual = register.doRegisterSystem(request);

		VerifyUtil.verifySuccess(responseExpected, responseActual);																																																																																																																																																																																																																																																																																																																																																																																																					
	}

	@Test
	public void validateRequestWithoutScreen() {
		request = new Request();
		
		System system = new System();
		system.setAddress(GenerateUtil.getRandomString(8));
		system.setName(GenerateUtil.getRandomString(10));
		system.setPort(GenerateUtil.getRandomInt(4).toString());
		request.setSystem(system);
		
		VerifyUtil.verifyError(new ResponseError(new ResourceRequiredException("No mínimo uma tela deve estar associada ao sistema.")),
				register.doRegisterSystem(request));
	}
	
	@Test
	public void validateRequestInsuccessReal() {
		Arrays.stream(new String[] { "name", "address", "port" })
				.forEach(field -> VerifyUtil.verifyError(PopulateUtil.populateResponseError(field),
						register.doRegisterSystem(PopulateUtil.populateRequest(PopulateUtil.populateSystemWithNull(field)))));
	}
	
	@Test
	public void validateRequestWithoutComponentInScreen() {
		request = new Request();
		
		System system = new System();
		system.setAddress(GenerateUtil.getRandomString(8));
		system.setName(GenerateUtil.getRandomString(10));
		system.setPort(GenerateUtil.getRandomInt(4).toString());
		
		Screen screen = new Screen();
		screen.setName(GenerateUtil.getRandomString(10));
		
		system.registerScreen(screen);
		
		request.setSystem(system);
		
		VerifyUtil.verifyError(new ResponseError(new ResourceRequiredException("No mínimo um componente deve estar associado a tela.")),
				register.doRegisterSystem(request));
	}
}
