package br.com.rjconsultores.tests.seleniuminstance.service.test.register;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.rjconsultores.tests.seleniuminstance.enums.OperationType;
import br.com.rjconsultores.tests.seleniuminstance.enums.SourceEvent;
import br.com.rjconsultores.tests.seleniuminstance.exception.RequiredResourceException;
import br.com.rjconsultores.tests.seleniuminstance.service.test.utilities.ConstanteUtil;
import br.com.rjconsultores.tests.seleniuminstance.service.test.utilities.GenerateUtil;
import br.com.rjconsultores.tests.seleniuminstance.service.test.utilities.VerifyUtil;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.Attribute;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.Component;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.Event;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.Rule;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.Screen;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.System;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.factory.RegisterFactory;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.service.request.Request;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.service.response.Response;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.service.response.ResponseError;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.service.response.StatusResponse;

public class RegisterFactoryTest {
	private System system;
	private Screen screen;
	private Component component;
	private Event event;
	private Rule rule;
	private SourceEvent sourceEvent;
	private Attribute attribute;
	
	private String msgErrorRequestNullValue;
	private String msgErrorSystemNullValue;
	private String msgErrorOperationTypeNullValue;
	
	
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

		attribute = new Attribute();
		attribute.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		attribute.setDescription(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_DESCRIPTION));
		
		sourceEvent = SourceEvent.REGISTER_SERVICE;
		
		msgErrorRequestNullValue = "O request deve ser um valor válido e nulo não é um valor válido.";
		msgErrorSystemNullValue = "O sistema deve ser um valor válido e nulo não é um valor válido.";
		msgErrorOperationTypeNullValue = "O tipo de operação deve ser um valor válido e nulo não é um valor válido.";
		
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
	
	@Test
	public void validateErrorOperationTypeNull() {
		VerifyUtil.verifyError(new ResponseError(new RequiredResourceException(sourceEvent, msgErrorOperationTypeNullValue)),
				RegisterFactory.INSTANCE().registerScreen(doRegisters()));
	}
	
	@Test
	public void validateSystemIdForInsert() {
		Request request = doRegisters();
		request.setOperationType(OperationType.INSERT);

		RegisterFactory.INSTANCE().registerScreen(request);
		VerifyUtil.verifyId(system.getId());
	}
	
	@Test
	public void validateChildrensSystemIdsForInsert() {		
		Request request = doRegisters();
		request.setOperationType(OperationType.INSERT);

		RegisterFactory.INSTANCE().registerScreen(request).getSystem().listScreens().forEach(screen -> {
			VerifyUtil.verifyId(screen.getId());
			Assert.assertEquals(system.getId(), screen.getParentId());			
		});
	}
	
	@Test
	public void validateChildrensIdsScreenForInsert() {
		Request request = doRegisters();
		request.setOperationType(OperationType.INSERT);
		
		RegisterFactory.INSTANCE().registerScreen(request).getSystem().listScreens()
			.forEach(screen -> screen.listComponents().forEach(component -> {
				VerifyUtil.verifyId(component.getId());
				Assert.assertEquals(screen.getId(), component.getParentId());
			}));
		
		RegisterFactory.INSTANCE().registerScreen(request).getSystem().listScreens()
			.forEach(screen -> screen.listEvents().forEach(event -> {
				VerifyUtil.verifyId(event.getId());
				Assert.assertEquals(screen.getId(), event.getParentId());
			}));
		
		RegisterFactory.INSTANCE().registerScreen(request).getSystem().listScreens()
			.forEach(screen -> screen.listAttributes().forEach(attribute -> {
				VerifyUtil.verifyId(attribute.getId());
				Assert.assertEquals(screen.getId(), attribute.getParentId());
			}));
	}
	
	@Test
	public void validateChildrensIdsComponentForInsert() {
		Request request = doRegisters();
		request.setOperationType(OperationType.INSERT);
		
		RegisterFactory.INSTANCE().registerScreen(request).getSystem().listScreens()
			.forEach(screen -> screen.listComponents().forEach(component ->  
				component.listAttributes().forEach(atribute -> { 
					VerifyUtil.verifyId(attribute.getId());
					Assert.assertEquals(component.getId(), attribute.getParentId());
				})
			));
		
		RegisterFactory.INSTANCE().registerScreen(request).getSystem().listScreens()
			.forEach(screen -> screen.listComponents().forEach(component ->  
				component.listEvents().forEach(event -> {
					VerifyUtil.verifyId(event.getId());
					Assert.assertEquals(component.getId(), event.getParentId());
				})
			));
	}
	
	@Test
	public void validateChildrensIdsEventForInsert() {
		Request request = doRegisters();
		request.setOperationType(OperationType.INSERT);
		
		RegisterFactory.INSTANCE().registerScreen(request).getSystem().listScreens()
			.forEach(screen -> screen.listComponents().forEach(component -> 
				component.listEvents().forEach(event -> event.listRules().forEach(rule -> {
					VerifyUtil.verifyId(rule.getId());
					Assert.assertEquals(event.getId(), rule.getParentId());
				})
			)
		));
	}
	
	@Test
	public void validateChildrensIdsRuleForInsert() {
		Request request = doRegisters();
		request.setOperationType(OperationType.INSERT);
		
		RegisterFactory.INSTANCE().registerScreen(request).getSystem().listScreens()
			.forEach(screen -> screen.listComponents().forEach(component -> 
				component.listEvents().forEach(event -> event.listRules().forEach(rule -> 
					rule.listAttributes().forEach(attribute -> {
						VerifyUtil.verifyId(attribute.getId());
						Assert.assertEquals(rule.getId(), attribute.getParentId());						
					})
				))
			));
	}
	
	@Test
	public void validateSuccessforInsertValidatingResponseSystem() {
		Request request = doRegisters();
		request.setOperationType(OperationType.INSERT);
		
		Response response = RegisterFactory.INSTANCE().registerScreen(request);
		
		Assert.assertEquals(StatusResponse.SUCCESS, response.getStatusResponse());
		Assert.assertEquals(null, response.getInstanceException());
		Assert.assertNotEquals(null, response.getSystem());
		Assert.assertNotEquals(null, response.getSystem().getId());
		Assert.assertNotEquals("", response.getSystem().getId());
		Assert.assertNotEquals(null, response.getSystem().listScreens());
		Assert.assertNotEquals(0, response.getSystem().listScreens().size());
		Assert.assertNotEquals(null, response.getSystem().listScreens());
		Assert.assertNotEquals(0, response.getSystem().listScreens().size());
	}
	
	@Test
	public void validateSuccessforInsertValidatingResponseScreen() {
		Request request = doRegisters();
		request.setOperationType(OperationType.INSERT);
		
		RegisterFactory.INSTANCE().registerScreen(request).getSystem().listScreens().forEach(screen -> {
			Assert.assertNotEquals(null, screen.getId());
			Assert.assertNotEquals("", screen.getId());
			Assert.assertNotEquals(null, screen.listComponents());
			Assert.assertNotEquals(0, screen.listComponents().size());
		});
	}
	
	@Test
	public void validateSuccessforInsertValidatingResponseComponents() {
		Request request = doRegisters();
		request.setOperationType(OperationType.INSERT);
		
		RegisterFactory.INSTANCE().registerScreen(request).getSystem().listScreens().forEach (
				screen -> screen.listComponents().forEach(component -> {
					Assert.assertNotEquals(null, component.getId());
					Assert.assertNotEquals("", component.getId());
					Assert.assertNotEquals("", component.getParentId());
					Assert.assertNotEquals(null, component.getParentId());
					Assert.assertNotEquals(null, component.listEvents());
					Assert.assertNotEquals(0, component.listEvents().size());
				})
			);	
	}
	
	@Test
	public void validateSuccessforInsertValidatingResponseRules() {
		Request request = doRegisters();
		request.setOperationType(OperationType.INSERT);
		
		RegisterFactory.INSTANCE().registerScreen(request).getSystem().listScreens()
			.forEach(screen -> screen.listComponents().forEach(component -> 
				component.listEvents().forEach(event -> event.listRules().forEach(rule -> {
					Assert.assertNotEquals(null, rule.getId());
					Assert.assertNotEquals("", rule.getId());
					Assert.assertNotEquals("", rule.getParentId());
					Assert.assertNotEquals(null, rule.getParentId());
					Assert.assertNotEquals(null, rule.listAttributes());
					Assert.assertNotEquals(0, rule.listAttributes().size());
				}))
			));
	}
	
	@Test
	public void validateSuccessforInsertValidatingResponseAttributes() {
		Request request = doRegisters();
		request.setOperationType(OperationType.INSERT);
		
		RegisterFactory.INSTANCE().registerScreen(request).getSystem().listScreens()
			.forEach(screen -> screen.listComponents().forEach(component -> component.listEvents().forEach(event -> event.listRules()
					.forEach(rule -> rule.listAttributes().forEach(attribute -> {
						Assert.assertNotEquals(null, attribute.getId());
						Assert.assertNotEquals("", attribute.getId());
						Assert.assertNotEquals("", attribute.getParentId());
						Assert.assertNotEquals(null, attribute.getParentId());
					})
				))
			));
	}
	
	private Request doRegisters() {
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
