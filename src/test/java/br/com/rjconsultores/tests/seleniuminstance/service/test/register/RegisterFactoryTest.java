package br.com.rjconsultores.tests.seleniuminstance.service.test.register;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.rjconsultores.tests.seleniuminstance.enums.SourceEvent;
import br.com.rjconsultores.tests.seleniuminstance.exception.RequiredResourceException;
import br.com.rjconsultores.tests.seleniuminstance.service.test.utilities.ConstanteUtil;
import br.com.rjconsultores.tests.seleniuminstance.service.test.utilities.GenerateUtil;
import br.com.rjconsultores.tests.seleniuminstance.service.test.utilities.VerifyUtil;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.Component;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.Event;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.Rule;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.Screen;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.System;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.factory.RegisterFactory;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.service.request.Request;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.service.response.ResponseError;

public class RegisterFactoryTest {
	private System system;
	private Screen screen;
	private Component component;
	private Event event;
	private Rule rule;
	private SourceEvent sourceEvent;
	
	private String msgErrorRequestNullValue;
	private String msgErrorSystemNullValue;
	
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
		
		sourceEvent = SourceEvent.REGISTER_SERVICE;
		
		msgErrorRequestNullValue = "O request deve ser um valor válido e nulo não é um valor válido.";
		msgErrorSystemNullValue = "O sistema deve ser um valor válido e nulo não é um valor válido.";
		
	}
	
	@Test
	public void validateInstance() {
		Assert.assertEquals(RegisterFactory.INSTANCE().getClass(), RegisterFactory.class);
	}
	
	@Test
	public void validateErrorRequestNull() {
		RegisterFactory registerFactory = RegisterFactory.INSTANCE();
		Request request = null;
		
		VerifyUtil.verifyError(new ResponseError(new RequiredResourceException(sourceEvent, msgErrorRequestNullValue)),
				registerFactory.registerScreen(request));
	}
	
	@Test
	public void validateErrorSystemNull() {
		RegisterFactory registerFactory = RegisterFactory.INSTANCE();
		
		VerifyUtil.verifyError(new ResponseError(new RequiredResourceException(sourceEvent, msgErrorSystemNullValue)),
				registerFactory.registerScreen(new Request()));
	}
}
