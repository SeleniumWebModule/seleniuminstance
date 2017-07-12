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

public class ComponentTest {
	private System system;
	private Screen screen;
	
	private SourceEvent sourceEvent;
	
	private String msgErrorRequiredEvent;
	private String msgErrorEventNullValue;
	private String msgErrorAttributeNullValue;
	
	@Before
	public void init() {		
		system = new System();
		system.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		system.setAddress(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		system.setPort(GenerateUtil.getRandomNumber(ConstanteUtil.SIZE_DEFAULT_PORT));
		
		screen = new Screen();
		screen.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		
		sourceEvent = SourceEvent.COMPONENT;
		
		msgErrorRequiredEvent = "Um ou mais eventos devem estar associados ao componente";
		msgErrorEventNullValue = "O evento deve ser um valor válido e nulo não é um valor válido.";
		msgErrorAttributeNullValue = "O atributo deve ser um valor válido e nulo não é um valor válido.";
	}

	@Test
	public void validateNameRequiredError() {
		Component component = new Component();
		VerifyUtil.verifyAndThrowsFieldRequireException(doRegisters(component), sourceEvent, "name");
		
		component.setName("");
		VerifyUtil.verifyAndThrowsFieldRequireException(doRegisters(component), sourceEvent, "name");
	}
	
	@Test
	public void validateDescriptionRequiredError() {		
		Component component = new Component();
		component.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		VerifyUtil.verifyAndThrowsFieldRequireException(doRegisters(component), sourceEvent, "description");
		
		component.setDescription("");
		VerifyUtil.verifyAndThrowsFieldRequireException(doRegisters(component), sourceEvent, "description");
	}
	
	@Test
	public void validateNameOverflowError() {
		Component component = new Component();
		component.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_OVERFLOW_NAME));
		
		VerifyUtil.verifyAndThrowsFieldSizeOverflowException(doRegisters(component), sourceEvent, "name");
	}
	
	@Test
	public void validateDescriptionOverflowError() {		
		Component component = new Component();
		component.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		component.setDescription(GenerateUtil.getRandomString(ConstanteUtil.SIZE_OVERFLOW_DESCRIPTION));
		
		VerifyUtil.verifyAndThrowsFieldSizeOverflowException(doRegisters(component), sourceEvent, "description");
	}
	
	@Test
	public void validateEventRequiredError() {
		Component component = new Component();
		component.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		component.setDescription(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_DESCRIPTION));
		
		VerifyUtil.verifyAndThrowsRequireResourceException(doRegisters(component), sourceEvent, msgErrorRequiredEvent);
	}
	
	@Test
	public void validateErrorEventNullValue() {
		Component component = new Component();
		component.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		component.setDescription(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_DESCRIPTION));
		
		Event event = null;
		component.registerEvent(event);
		
		VerifyUtil.verifyAndThrowsRequireResourceException(doRegisters(component), sourceEvent, msgErrorEventNullValue);
	}
	
	@Test
	public void validateErrorAttributeNullValue() {
		Component component = new Component();
		component.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		component.setDescription(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_DESCRIPTION));
		
		component.registerEvent(new Event());
		
		Attribute attribute = null;
		component.registerAttribute(attribute);
		
		VerifyUtil.verifyAndThrowsRequireResourceException(doRegisters(component), sourceEvent, msgErrorAttributeNullValue);
	}
	
	private Request doRegisters(Component component) {
		Request request = new Request();
		
		screen.registerComponent(component);
		system.registerScreen(screen);
		
		request.setSystem(system);
		return request;
	}
}
