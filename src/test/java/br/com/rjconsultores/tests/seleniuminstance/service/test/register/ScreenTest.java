package br.com.rjconsultores.tests.seleniuminstance.service.test.register;

import org.junit.Before;
import org.junit.Test;

import br.com.rjconsultores.tests.seleniuminstance.enums.SourceEvent;
import br.com.rjconsultores.tests.seleniuminstance.service.test.utilities.ConstanteUtil;
import br.com.rjconsultores.tests.seleniuminstance.service.test.utilities.GenerateUtil;
import br.com.rjconsultores.tests.seleniuminstance.service.test.utilities.VerifyUtil;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.Attribute;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.Component;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.Event;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.Screen;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.System;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.service.request.Request;

public class ScreenTest {
	private System system;
	
	private SourceEvent sourceEvent;
	
	private String msgErrorComponentRequired;
	private String msgErrorAttributeNullValue;
	private String msgErrorEventNullValue;

	@Before
	public void init() {		
		system = new System();
		system.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		system.setAddress(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		system.setPort(GenerateUtil.getRandomNumber(ConstanteUtil.SIZE_DEFAULT_PORT));
		
		sourceEvent = SourceEvent.SCREEN; 
		
		msgErrorComponentRequired = "Um ou mais componentes devem estar associados à tela.";
		msgErrorAttributeNullValue = "O atributo deve ser um valor válido e nulo não é um valor válido.";
		msgErrorEventNullValue = "O evento deve ser um valor válido e nulo não é um valor válido.";
	}

	@Test
	public void validateNameRequiredError() {
		Screen screen = new Screen();		
		VerifyUtil.verifyAndThrowsFieldRequireException(doRegisters(screen), sourceEvent, "name");
		
		screen.setName("");
		VerifyUtil.verifyAndThrowsFieldRequireException(doRegisters(screen), sourceEvent, "name");
	}
	
	@Test
	public void validateNameOverflowError() {
		Screen screen = new Screen();		
		screen.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_OVERFLOW_NAME));
		
		VerifyUtil.verifyAndThrowsFieldSizeOverflowException(doRegisters(screen), sourceEvent, "name");
	}
	
	@Test
	public void validateComponentRequiredError() {
		Screen screen = new Screen();
		screen.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		
		VerifyUtil.verifyAndThrowsRequireResourceException(doRegisters(screen), sourceEvent, msgErrorComponentRequired);
	}
	
	@Test
	public void validateComponentWithNullValue() {
		Screen screen = new Screen();
		screen.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		
		Component component = null;
		screen.registerComponent(component);
		
		VerifyUtil.verifyAndThrowsRequireResourceException(doRegisters(screen), sourceEvent, msgErrorComponentRequired);
	}
	
	@Test
	public void validateAttributeWithNullValue() {
		Screen screen = new Screen();
		screen.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		
		Component component = new Component();
		screen.registerComponent(component);
		
		Attribute attribute = null;
		screen.registerAttribute(attribute);
		
		VerifyUtil.verifyAndThrowsRequireResourceException(doRegisters(screen), sourceEvent, msgErrorAttributeNullValue);
	}
	
	@Test
	public void validateEventWithNullValue() {
		Screen screen = new Screen();
		screen.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		
		Component component = new Component();
		screen.registerComponent(component);
		
		Event event = null;
		screen.registerEvent(event);
		
		VerifyUtil.verifyAndThrowsRequireResourceException(doRegisters(screen), sourceEvent, msgErrorEventNullValue);
	}

	private Request doRegisters(Screen screen) {
		Request request = new Request();
		
		system.registerScreen(screen);
		request.setSystem(system);
		
		return request;
	}
}
