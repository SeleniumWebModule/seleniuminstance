package br.com.rjconsultores.tests.seleniuminstance.service.test.entity;

import java.util.Collection;

import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import br.com.rjconsultores.tests.seleniuminstance.enums.OperationType;
import br.com.rjconsultores.tests.seleniuminstance.service.test.utilities.ConstanteUtil;
import br.com.rjconsultores.tests.seleniuminstance.service.test.utilities.GenerateUtil;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.Attribute;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.Component;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.Event;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.Rule;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.Screen;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.System;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.service.request.Request;

public class ValidateEntriesTest {
	
	@Test
	public void validateSystem() {
		System systemMock = Mockito.mock(System.class);
		
		String name = GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME);
		String address = GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_ADDRESS);
		String port = GenerateUtil.getRandomNumber(ConstanteUtil.SIZE_DEFAULT_PORT);
		
		systemMock.setName(name);
		systemMock.getName();

		systemMock.setAddress(address);
		systemMock.getAddress();

		systemMock.setPort(port);
		systemMock.getPort();

		systemMock.registerScreen(Mockito.any(Screen.class));
		
		Collection<Screen> screens = systemMock.listScreens();
		
		Mockito.verify(systemMock).setName(name);
		Mockito.verify(systemMock).getName();
		Mockito.verify(systemMock).setAddress(address);
		Mockito.verify(systemMock).getAddress();
		Mockito.verify(systemMock).setPort(port);
		Mockito.verify(systemMock).getPort();
		Mockito.verify(systemMock).registerScreen(Mockito.any(Screen.class));
		
		Mockito.when(systemMock.listScreens()).thenReturn(screens);
	}
	
	@Test
	public void validadeScreen() {
		Screen screenMock = Mockito.mock(Screen.class);
		
		String name = GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME);
		
		screenMock.setName(name);
		screenMock.getName();
		
		screenMock.registerAttribute(Mockito.any(Attribute.class));
		screenMock.registerComponent(Mockito.any(Component.class));
		
		Collection<Attribute> attributes = screenMock.listAttributes();
		Collection<Component> components = screenMock.listComponents();
		
		Mockito.verify(screenMock).setName(name);
		Mockito.verify(screenMock).getName();
		Mockito.verify(screenMock).registerAttribute(Mockito.any(Attribute.class));
		
		Mockito.when(screenMock.listAttributes()).thenReturn(attributes);
		Mockito.when(screenMock.listComponents()).thenReturn(components);
	}
	
	@Test
	public void validateComponent() {
		Component componentMock = Mockito.mock(Component.class);
		
		String name = GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME);
		String description = GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_DESCRIPTION);
		
		componentMock.setName(name);
		componentMock.getName();
		
		componentMock.setDescription(description);
		componentMock.getDescription();
		
		componentMock.registerAttribute(Mockito.any(Attribute.class));
		componentMock.registerEvent(Matchers.any(Event.class));
		
		Collection<Attribute> attributes = componentMock.listAttributes();
		Collection<Event> events = componentMock.listEvents();
		
		Mockito.verify(componentMock).setName(name);
		Mockito.verify(componentMock).getName();
		Mockito.verify(componentMock).setDescription(description);
		Mockito.verify(componentMock).getDescription();
		Mockito.verify(componentMock).registerAttribute(Matchers.any(Attribute.class));
		Mockito.verify(componentMock).registerEvent(Matchers.any(Event.class));
		
		Mockito.when(componentMock.listAttributes()).thenReturn(attributes);
		Mockito.when(componentMock.listEvents()).thenReturn(events);
	}
	
	@Test
	public void validateEvent() {
		Event eventMock = Mockito.mock(Event.class);
		
		String name = GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME);
		String description = GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_DESCRIPTION);
		
		eventMock.setName(name);
		eventMock.getName();
		
		eventMock.setDescription(description);
		eventMock.getDescription();
		
		eventMock.registerRule(Mockito.any(Rule.class));
		
		Collection<Rule> rules = eventMock.listRules();
		
		Mockito.verify(eventMock).setName(name);
		Mockito.verify(eventMock).getName();
		Mockito.verify(eventMock).setDescription(description);
		Mockito.verify(eventMock).getDescription();
		
		Mockito.when(eventMock.listRules()).thenReturn(rules);
	}
	
	@Test
	public void validateRule() {
		Rule ruleMock = Mockito.mock(Rule.class);
		
		String name = GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME);
		String description = GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_DESCRIPTION);
		
		ruleMock.setName(name);
		ruleMock.getName();
		
		ruleMock.setDescription(description);
		ruleMock.getDescription();
		
		Mockito.verify(ruleMock).setName(name);
		Mockito.verify(ruleMock).getName();
		
		Mockito.verify(ruleMock).setDescription(description);
		Mockito.verify(ruleMock).getDescription();
	}
	
	@Test
	public void validateAttribute() {
		Attribute attributeMock = Mockito.mock(Attribute.class);
		
		String name = GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME);
		String description = GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_DESCRIPTION);
		
		attributeMock.setName(name);
		attributeMock.getName();
		
		attributeMock.setDescription(description);
		attributeMock.getDescription();
		
		Mockito.verify(attributeMock).setName(name);
		Mockito.verify(attributeMock).getName();
		
		Mockito.verify(attributeMock).setDescription(description);
		Mockito.verify(attributeMock).getDescription();
	}
	
	@Test
	public void validateRequest() {
		Request requestMock = Mockito.mock(Request.class);
		
		requestMock.setSystem(Mockito.any(System.class));
		requestMock.getSystem();
		
		requestMock.setOperationType(Mockito.any(OperationType.class));
		requestMock.getOperationType();
		
		Mockito.verify(requestMock).setSystem(Mockito.any(System.class));
		Mockito.verify(requestMock).getSystem();
		Mockito.verify(requestMock).setOperationType(Mockito.any(OperationType.class));
		Mockito.verify(requestMock).getOperationType();
	}
}
