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
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.Rule;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.Screen;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.System;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.service.request.Request;

public class RuleTest {
	private System system;
	private Screen screen;
	private Component component;
	private Event event;
	private SourceEvent sourceEvent;
	
	private String msgErrorAttributeRequired;
	private String msgErrorAttributeNullValue;

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
		
		event = new Event();
		event.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		event.setDescription(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_DESCRIPTION));
		component.registerEvent(event);
		
		sourceEvent = SourceEvent.RULE;
		
		msgErrorAttributeRequired = "Um ou mais atributos deve estar associados à regra.";
		msgErrorAttributeNullValue = "O atributo deve ser um valor válido e nulo não é um valor válido.";
	}
	
	@Test
	public void validateNameRequiredError() {		
		Rule rule = new Rule();
		VerifyUtil.verifyAndThrowsFieldRequireException(doRegister(rule) , sourceEvent, "name");
	
		rule.setName("");	
		VerifyUtil.verifyAndThrowsFieldRequireException(doRegister(rule) , sourceEvent, "name");
	}
	
	@Test
	public void validateDesccriptionRequiredError() {		
		Rule rule = new Rule();
		rule.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		VerifyUtil.verifyAndThrowsFieldRequireException(doRegister(rule), sourceEvent, "description");
			
		rule.setDescription("");	
		VerifyUtil.verifyAndThrowsFieldRequireException(doRegister(rule), sourceEvent, "description");
	}
	
	@Test
	public void validateNameOverflowError() {		
		Rule rule = new Rule();
		rule.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_OVERFLOW_NAME));	
		VerifyUtil.verifyAndThrowsFieldSizeOverflowException(doRegister(rule), sourceEvent, "name");
	}
	
	@Test
	public void validateDescriptionOverflowError() {		
		Rule rule = new Rule();
		rule.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		rule.setDescription(GenerateUtil.getRandomString(ConstanteUtil.SIZE_OVERFLOW_DESCRIPTION));
		VerifyUtil.verifyAndThrowsFieldSizeOverflowException(doRegister(rule), sourceEvent, "description");
	}
	
	@Test
	public void validateAttributeRequiredError() {		
		Rule rule = new Rule();
		rule.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		rule.setDescription(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_DESCRIPTION));
		VerifyUtil.verifyAndThrowsRequireResourceException(doRegister(rule), sourceEvent, msgErrorAttributeRequired);		
	}
	
	@Test
	public void validateAttributeWithNullValue() {		
		Rule rule = new Rule();
		rule.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		rule.setDescription(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_DESCRIPTION));
		
		Attribute attribute = null;
		rule.registerAttribute(attribute);
		VerifyUtil.verifyAndThrowsRequireResourceException(doRegister(rule), sourceEvent, msgErrorAttributeNullValue);		
	}
	
	private Request doRegister(Rule rule) {
		Request request = new Request();
		
		event.registerRule(rule);
		component.registerEvent(event);
		screen.registerComponent(component);
		system.registerScreen(screen);
		request.setSystem(system);
		
		return request;
	}
}
