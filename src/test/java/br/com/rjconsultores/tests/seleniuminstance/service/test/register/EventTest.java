package br.com.rjconsultores.tests.seleniuminstance.service.test.register;

import org.junit.Before;
import org.junit.Test;

import br.com.rjconsultores.tests.seleniuminstance.enums.SourceEvent;
import br.com.rjconsultores.tests.seleniuminstance.service.test.utilities.ConstanteUtil;
import br.com.rjconsultores.tests.seleniuminstance.service.test.utilities.GenerateUtil;
import br.com.rjconsultores.tests.seleniuminstance.service.test.utilities.VerifyUtil;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.Component;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.Event;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.Rule;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.Screen;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.System;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.service.request.Request;

public class EventTest {
	private System system;
	private Screen screen;
	private Component component;
	private SourceEvent sourceEvent;
	
	private String msgErrorRuleRequired;
	private String msgErrorRuleNullValue;

	@Before
	public void init() {
		system = new System();
		system.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		system.setAddress(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		system.setPort(GenerateUtil.getRandomNumber(ConstanteUtil.SIZE_DEFAULT_PORT));
		
		screen = new Screen();
		screen.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		
		component = new Component();
		component.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		component.setDescription(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_DESCRIPTION));
		
		sourceEvent = SourceEvent.EVENT;
		
		msgErrorRuleRequired = "Uma ou mais regras devem estar associadas ao evento.";
		msgErrorRuleNullValue = "A regra deve ser um valor válido e nulo não é um valor válido.";
	}
	
	@Test
	public void validateNameRequiredError() {
		Event event = new Event();
		VerifyUtil.verifyAndThrowsFieldRequireException(doRegisters(event), sourceEvent, "name");
		
		event.setName("");
		VerifyUtil.verifyAndThrowsFieldRequireException(doRegisters(event), sourceEvent, "name");
	}
	
	@Test
	public void validateDesccriptionRequiredError() {
		Event event = new Event();
		event.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		VerifyUtil.verifyAndThrowsFieldRequireException(doRegisters(event), sourceEvent, "description");
		
		event.setDescription("");
		VerifyUtil.verifyAndThrowsFieldRequireException(doRegisters(event), sourceEvent, "description");
	}
	
	@Test
	public void validateNameOverflowError() {
		Event event = new Event();
		event.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_OVERFLOW_NAME));
		VerifyUtil.verifyAndThrowsFieldSizeOverflowException(doRegisters(event), sourceEvent, "name");
	}
	
	@Test
	public void validateDescriptionOverflowError() {
		Event event = new Event();
		event.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		event.setDescription(GenerateUtil.getRandomString(ConstanteUtil.SIZE_OVERFLOW_DESCRIPTION));
		VerifyUtil.verifyAndThrowsFieldSizeOverflowException(doRegisters(event), sourceEvent, "description");
	}
	
	@Test
	public void validateRuleRequiredError() {		
		Event event = new Event();
		event.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		event.setDescription(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_DESCRIPTION));
		VerifyUtil.verifyAndThrowsRequireResourceException(doRegisters(event), sourceEvent, msgErrorRuleRequired);
	}
	
	@Test
	public void validateRuleErrorNullValue() {		
		Event event = new Event();
		event.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		event.setDescription(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_DESCRIPTION));
		
		Rule rule = null;
		event.registerRule(rule);
		
		VerifyUtil.verifyAndThrowsRequireResourceException(doRegisters(event), sourceEvent, msgErrorRuleNullValue);
	}
	
	private Request doRegisters(Event event) {
		Request request = new Request();
		component.registerEvent(event);
		
		screen.registerComponent(component);
		system.registerScreen(screen);
		request.setSystem(system);
		
		return request;
	}
}
