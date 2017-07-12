package br.com.rjconsultores.tests.seleniuminstance.service.test.register;

import org.junit.Before;
import org.junit.Test;

import br.com.rjconsultores.tests.seleniuminstance.enums.SourceEvent;
import br.com.rjconsultores.tests.seleniuminstance.exception.FieldRequireException;
import br.com.rjconsultores.tests.seleniuminstance.exception.FieldSizeOverflow;
import br.com.rjconsultores.tests.seleniuminstance.exception.ResourceRequiredException;
import br.com.rjconsultores.tests.seleniuminstance.service.test.utilities.ConstanteUtil;
import br.com.rjconsultores.tests.seleniuminstance.service.test.utilities.GenerateUtil;
import br.com.rjconsultores.tests.seleniuminstance.service.test.utilities.VerifyUtil;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.Screen;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.System;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.service.Register;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.service.request.Request;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.service.response.ResponseError;

public class ScreenTest {
	private Register register;
	private Request request;
	private System system;

	@Before
	public void init() {
		register = new Register();
		
		system = new System();
		system.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		system.setAddress(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		system.setPort(GenerateUtil.getRandomNumber(ConstanteUtil.SIZE_DEFAULT_PORT));
	}

	@Test
	public void validateNameRequiredError() {
		request = new Request();
		Screen screen = new Screen();
		
		system.registerScreen(screen);
		request.setSystem(system);
		VerifyUtil.verifyError(new ResponseError(new FieldRequireException(SourceEvent.SCREEN, "name")),
				register.doRegisterSystem(request));
		
		screen.setName("");
		system.registerScreen(screen);
		request.setSystem(system);
		VerifyUtil.verifyError(new ResponseError(new FieldRequireException(SourceEvent.SCREEN, "name")),
				register.doRegisterSystem(request));

	}
	
	@Test
	public void validateNameOverflowError() {
		request = new Request();
		
		Screen screen = new Screen();		
		screen.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_OVERFLOW_NAME));

		system.registerScreen(screen);
		request.setSystem(system);
		
		VerifyUtil.verifyError(new ResponseError(new FieldSizeOverflow(SourceEvent.SCREEN, "name")),
			register.doRegisterSystem(request));
	}
	
	@Test
	public void validateComponentRequiredError() {
		request = new Request();
		System system = new System();
		
		system.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		system.setAddress(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_ADDRESS));
		system.setPort(GenerateUtil.getRandomNumber(ConstanteUtil.SIZE_DEFAULT_PORT));
		
		Screen screen = new Screen();
		screen.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		
		system.registerScreen(screen);
		request.setSystem(system);
		
		VerifyUtil.verifyError(new ResponseError(new ResourceRequiredException(SourceEvent.SCREEN,
				"No mínimo um componente deve estar associado à tela.")),
			register.doRegisterSystem(request));
	}
}
