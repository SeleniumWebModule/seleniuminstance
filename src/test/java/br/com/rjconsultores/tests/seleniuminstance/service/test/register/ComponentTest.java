package br.com.rjconsultores.tests.seleniuminstance.service.test.register;

import org.junit.Before;
import org.junit.Test;

import br.com.rjconsultores.tests.seleniuminstance.enums.SourceEvent;
import br.com.rjconsultores.tests.seleniuminstance.exception.FieldRequireException;
import br.com.rjconsultores.tests.seleniuminstance.exception.FieldSizeOverflowException;
import br.com.rjconsultores.tests.seleniuminstance.exception.ResourceRequiredException;
import br.com.rjconsultores.tests.seleniuminstance.service.test.utilities.ConstanteUtil;
import br.com.rjconsultores.tests.seleniuminstance.service.test.utilities.GenerateUtil;
import br.com.rjconsultores.tests.seleniuminstance.service.test.utilities.VerifyUtil;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.Component;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.Screen;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.System;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.service.Register;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.service.request.Request;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.service.response.ResponseError;

public class ComponentTest {
	private Register register;
	private Request request;
	private System system;
	private Screen screen;
	

	@Before
	public void init() {
		register = new Register();
		
		system = new System();
		system.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		system.setAddress(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		system.setPort(GenerateUtil.getRandomNumber(ConstanteUtil.SIZE_DEFAULT_PORT));
		
		screen = new Screen();
		screen.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
	}

	@Test
	public void validateNameRequiredError() {
		request = new Request();
		
		Component component = new Component();
		screen.registerComponent(component);
		
		system.registerScreen(screen);
		request.setSystem(system);
		VerifyUtil.verifyError(new ResponseError(new FieldRequireException(SourceEvent.COMPONENT, "name")),
				register.doRegisterSystem(request));
		
		component.setName("");
		screen.registerComponent(component);
		system.registerScreen(screen);
		request.setSystem(system);
		VerifyUtil.verifyError(new ResponseError(new FieldRequireException(SourceEvent.COMPONENT, "name")),
				register.doRegisterSystem(request));
	}
	
	@Test
	public void validateDescriptionRequiredError() {
		request = new Request();
		
		Component component = new Component();
		component.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		screen.registerComponent(component);
		
		system.registerScreen(screen);
		request.setSystem(system);
		VerifyUtil.verifyError(new ResponseError(new FieldRequireException(SourceEvent.COMPONENT, "description")),
				register.doRegisterSystem(request));
		
		component.setDescription("");
		screen.registerComponent(component);
		system.registerScreen(screen);
		request.setSystem(system);
		VerifyUtil.verifyError(new ResponseError(new FieldRequireException(SourceEvent.COMPONENT, "description")),
				register.doRegisterSystem(request));
	}
	
	@Test
	public void validateNameOverflowError() {
		request = new Request();
		
		Component component = new Component();
		component.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_OVERFLOW_NAME));
		screen.registerComponent(component);
		
		system.registerScreen(screen);
		request.setSystem(system);
		
		VerifyUtil.verifyError(new ResponseError(new FieldSizeOverflowException(SourceEvent.COMPONENT, "name")),
			register.doRegisterSystem(request));
	}
	
	@Test
	public void validateDescriptionOverflowError() {
		request = new Request();
		
		Component component = new Component();
		component.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		component.setDescription(GenerateUtil.getRandomString(ConstanteUtil.SIZE_OVERFLOW_DESCRIPTION));
		screen.registerComponent(component);
		
		system.registerScreen(screen);
		request.setSystem(system);
		
		VerifyUtil.verifyError(new ResponseError(new FieldSizeOverflowException(SourceEvent.COMPONENT, "description")),
			register.doRegisterSystem(request));
	}
	
	@Test
	public void validateEventRequiredError() {
		request = new Request();
		
		Component component = new Component();
		component.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		component.setDescription(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_DESCRIPTION));
		screen.registerComponent(component);
		
		system.registerScreen(screen);
		request.setSystem(system);
		
		VerifyUtil.verifyError(new ResponseError(new ResourceRequiredException(SourceEvent.COMPONENT,
				"No mínimo um evento deve estar associado ao componente.")),
			register.doRegisterSystem(request));
	}
}
