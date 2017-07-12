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

public class AttributeTest {
	private System system;
	private Screen screen;
	private Component component;
	private Event event;
	private Rule rule;
	private SourceEvent sourceEvent;
	
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
		
		rule = new Rule();
		rule.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		rule.setDescription(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_DESCRIPTION));
		
		sourceEvent = SourceEvent.ATTRIBUTE;
	}
	
	@Test
	public void validateNameRequiredError() {		
		Attribute attribute = new Attribute();
		VerifyUtil.verifyAndThrowsFieldRequireException(doRegister(attribute) , sourceEvent, "name");
	
		attribute.setName("");	
		VerifyUtil.verifyAndThrowsFieldRequireException(doRegister(attribute) , sourceEvent, "name");
	}
	
	@Test
	public void validateDesccriptionRequiredError() {		
		Attribute attribute = new Attribute();
		attribute.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		VerifyUtil.verifyAndThrowsFieldRequireException(doRegister(attribute), sourceEvent, "description");
			
		attribute.setDescription("");	
		VerifyUtil.verifyAndThrowsFieldRequireException(doRegister(attribute), sourceEvent, "description");
	}
	
	@Test
	public void validateNameOverflowError() {		
		Attribute attribute = new Attribute();
		attribute.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_OVERFLOW_NAME));	
		VerifyUtil.verifyAndThrowsFieldSizeOverflowException(doRegister(attribute), sourceEvent, "name");
	}
	
	@Test
	public void validateDescriptionOverflowError() {		
		Attribute attribute = new Attribute();
		attribute.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		attribute.setDescription(GenerateUtil.getRandomString(ConstanteUtil.SIZE_OVERFLOW_DESCRIPTION));
		VerifyUtil.verifyAndThrowsFieldSizeOverflowException(doRegister(attribute), sourceEvent, "description");
	}
	
	private Request doRegister(Attribute attribute) {
		Request request = new Request();
		
		rule.registerAttribute(attribute);
		event.registerRule(rule);
		component.registerEvent(event);
		screen.registerComponent(component);
		system.registerScreen(screen);
		request.setSystem(system);
		
		return request;
	}

}
