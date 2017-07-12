package br.com.rjconsultores.tests.seleniuminstance.service.test.register;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import br.com.rjconsultores.tests.seleniuminstance.enums.OperationType;
import br.com.rjconsultores.tests.seleniuminstance.enums.SourceEvent;
import br.com.rjconsultores.tests.seleniuminstance.exception.FieldSizeOverflow;
import br.com.rjconsultores.tests.seleniuminstance.exception.ResourceRequiredException;
import br.com.rjconsultores.tests.seleniuminstance.service.test.utilities.ConstanteUtil;
import br.com.rjconsultores.tests.seleniuminstance.service.test.utilities.GenerateUtil;
import br.com.rjconsultores.tests.seleniuminstance.service.test.utilities.PopulateUtil;
import br.com.rjconsultores.tests.seleniuminstance.service.test.utilities.VerifyUtil;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.Attribute;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.Component;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.Event;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.Rule;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.Screen;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.System;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.service.Register;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.service.request.Request;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.service.response.Response;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.service.response.ResponseError;

public class RegisterServiceRealTest {
	
	private Register register;
	private Request request;
	
	@Before
	public void init() {
		register = new Register();
	}
	
	@Test
	public void validateRequestSuccess() {
		request = new Request();
		System system = new System();

		system.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		system.setAddress(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_ADDRESS));
		system.setPort(GenerateUtil.getRandomNumber(ConstanteUtil.SIZE_DEFAULT_PORT));

		Screen screen = new Screen();
		screen.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));

		Component component = new Component();
		component.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		component.setDescription(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_DESCRIPTION));

		Attribute attribute = new Attribute();
		attribute.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		attribute.setDescription(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_DESCRIPTION));

		Rule rule = new Rule();
		rule.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		rule.setDescription(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_DESCRIPTION));
		
		Event event = new Event();
		event.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		event.setDescription(GenerateUtil.getRandomString(ConstanteUtil.SIZE_OVERFLOW_DESCRIPTION));
		event.registerRule(rule);
		
		component.registerEvent(event);
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
		system.setAddress(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_ADDRESS));
		system.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		system.setPort(GenerateUtil.getRandomNumber(ConstanteUtil.SIZE_DEFAULT_PORT));
		request.setSystem(system);
		
		VerifyUtil.verifyError(new ResponseError(new ResourceRequiredException(SourceEvent.SYSTEM, "No mínimo uma tela deve estar associada ao sistema.")),
				register.doRegisterSystem(request));
	}
	
	
	@Test
	public void validateRequestInsuccessReal() {
		Arrays.stream(new String[] { "name", "address", "port" })
				.forEach(field -> VerifyUtil.verifyError(PopulateUtil.populateResponseError(SourceEvent.SYSTEM, field),
						register.doRegisterSystem(PopulateUtil.populateRequest(PopulateUtil.populateSystemWithNull(field)))));
	}
	
	@Test
	public void validateRequestWithInputOverflowForSystem() {
		request = new Request();
		
		System system = new System();
		system.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_OVERFLOW_NAME));
		
		request.setSystem(system);
		request.setOperationType(Mockito.any(OperationType.class));
		
		VerifyUtil.verifyError(new ResponseError(new FieldSizeOverflow(SourceEvent.SYSTEM, "name")),
				register.doRegisterSystem(request));
		
		system.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		system.setAddress(GenerateUtil.getRandomString(ConstanteUtil.SIZE_OVERFLOW_ADDRESS));
		
		request.setSystem(system);
		
		VerifyUtil.verifyError(new ResponseError(new FieldSizeOverflow(SourceEvent.SYSTEM, "address")),
				register.doRegisterSystem(request));
		
		system.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		system.setAddress(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_ADDRESS));
		system.setPort(GenerateUtil.getRandomNumber(ConstanteUtil.SIZE_OVERFLOW_PORT));
		
		request.setSystem(system);
		
		VerifyUtil.verifyError(new ResponseError(new FieldSizeOverflow(SourceEvent.SYSTEM, "port")),
				register.doRegisterSystem(request));
	}
	
	@Test
	public void validateRequestWithInputOverflowForScreen() {
		request = new Request();
		
		System system = new System();
		system.setAddress(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_ADDRESS));
		system.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		system.setPort(GenerateUtil.getRandomNumber(ConstanteUtil.SIZE_DEFAULT_PORT));
		
		Screen screen = new Screen();
		screen.setName(GenerateUtil.getRandomString(100));
		
		system.registerScreen(screen);
		
		request.setSystem(system);
		
		VerifyUtil.verifyError(new ResponseError(new FieldSizeOverflow(SourceEvent.SCREEN, "name")),
				register.doRegisterSystem(request));
	}
	
	@Test
	public void validateRequestWithInputOverflowForAttribute() {
		request = new Request();
		
		System system = new System();
		system.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		system.setAddress(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_ADDRESS));
		system.setPort(GenerateUtil.getRandomNumber(ConstanteUtil.SIZE_DEFAULT_PORT));
		
		Screen screen = new Screen();
		screen.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		
		Component component = new Component();
		component.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		component.setDescription(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_DESCRIPTION));
				
		Rule rule = new Rule();
		rule.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		rule.setDescription(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_DESCRIPTION));
		
		Event event = new Event();
		event.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		event.setDescription(GenerateUtil.getRandomString(ConstanteUtil.SIZE_OVERFLOW_DESCRIPTION));
		event.registerRule(rule);
		
		component.registerEvent(event);
		
		Attribute attribute = new Attribute();
		attribute.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_OVERFLOW_NAME));
		
		component.registerAttribute(attribute);
		screen.registerComponent(component);
		system.registerScreen(screen);
		
		request.setSystem(system);
		
		VerifyUtil.verifyError(new ResponseError(new FieldSizeOverflow(SourceEvent.ATTRIBUTE, "name")),
				register.doRegisterSystem(request));
	}
	
	
	public void validateRequestWithInputOverflowAndNullPointerForEvent() {
		request = new Request();
		
		System system = new System();
		system.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		system.setAddress(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_ADDRESS));
		system.setPort(GenerateUtil.getRandomNumber(ConstanteUtil.SIZE_DEFAULT_PORT));
		
		Screen screen = new Screen();
		screen.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		
		
		
		
	}
}
