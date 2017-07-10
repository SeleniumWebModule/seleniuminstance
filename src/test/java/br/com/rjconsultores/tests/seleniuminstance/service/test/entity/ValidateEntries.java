package br.com.rjconsultores.tests.seleniuminstance.service.test.entity;

import java.util.Collection;
import java.util.Set;

import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.Attribute;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.Component;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.Event;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.Rule;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.Screen;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.System;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.service.request.OperationType;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.service.request.Request;

public class ValidateEntries {
	
	@Test
	public void validateSystem() {
		System systemMock = Mockito.mock(System.class);
		
		systemMock.setName(Mockito.anyString());
		systemMock.getName();

		systemMock.setAddress(Mockito.anyString());
		systemMock.getAddress();

		systemMock.setPort(Mockito.anyString());
		systemMock.getPort();

		systemMock.registerScreen(Mockito.any(Screen.class));
		
		Collection<Screen> screens = systemMock.listScreens();
		
		Mockito.verify(systemMock).setName(Mockito.anyString());
		Mockito.verify(systemMock).getName();
		Mockito.verify(systemMock).setAddress(Mockito.anyString());
		Mockito.verify(systemMock).getAddress();
		Mockito.verify(systemMock).setPort(Mockito.anyString());
		Mockito.verify(systemMock).getPort();
		Mockito.verify(systemMock).registerScreen(Mockito.any(Screen.class));
		
		Mockito.when(systemMock.listScreens()).thenReturn(screens);
	}
	
	@Test
	public void validadeScreen() {
		Screen screenMock = Mockito.mock(Screen.class);
		
		screenMock.setName(Mockito.anyString());
		screenMock.getName();
		
		screenMock.registerAttribute(Mockito.any(Attribute.class));
		screenMock.registerComponent(Mockito.any(Component.class));
		
		Collection<Attribute> attributes = screenMock.listAttributes();
		Collection<Component> components = screenMock.listComponents();
		
		Mockito.verify(screenMock).setName(Mockito.anyString());
		Mockito.verify(screenMock).getName();
		Mockito.verify(screenMock).registerAttribute(Mockito.any(Attribute.class));
		
		Mockito.when(screenMock.listAttributes()).thenReturn(attributes);
		Mockito.when(screenMock.listComponents()).thenReturn(components);
	}
	
	@Test
	public void validateComponent() {
		Component componentMock = Mockito.mock(Component.class);
		
		componentMock.setName(Mockito.anyString());
		componentMock.getName();
		
		componentMock.setDescricao(Mockito.anyString());
		componentMock.getDescricao();
		
		componentMock.registerAttribute(Mockito.any(Attribute.class));
		componentMock.registerEvent(Matchers.any(Event.class));
		
		Set<Attribute> attributes = componentMock.listAttributes();
		Set<Event> events = componentMock.listEvents();
		
		Mockito.verify(componentMock).setName(Mockito.anyString());
		Mockito.verify(componentMock).getName();
		Mockito.verify(componentMock).setDescricao(Mockito.anyString());
		Mockito.verify(componentMock).getDescricao();
		Mockito.verify(componentMock).registerAttribute(Matchers.any(Attribute.class));
		Mockito.verify(componentMock).registerEvent(Matchers.any(Event.class));
		
		Mockito.when(componentMock.listAttributes()).thenReturn(attributes);
		Mockito.when(componentMock.listEvents()).thenReturn(events);
	}
	
	@Test
	public void validateEvent() {
		Event eventMock = Mockito.mock(Event.class);
		
		eventMock.setName(Mockito.anyString());
		eventMock.getName();
		
		eventMock.setDescricao(Mockito.anyString());
		eventMock.getDescricao();
		
		eventMock.registerRule(Mockito.any(Rule.class));
		
		Collection<Rule> rules = eventMock.listRules();
		
		Mockito.verify(eventMock).setName(Mockito.anyString());
		Mockito.verify(eventMock).getName();
		Mockito.verify(eventMock).setDescricao(Mockito.anyString());
		Mockito.verify(eventMock).getDescricao();
		
		Mockito.when(eventMock.listRules()).thenReturn(rules);
	}
	
	@Test
	public void validateRule() {
		Rule ruleMock = Mockito.mock(Rule.class);
		
		ruleMock.setName(Mockito.anyString());
		ruleMock.getName();
		
		ruleMock.setDescricao(Mockito.anyString());
		ruleMock.getDescricao();
		
		Mockito.verify(ruleMock).setName(Mockito.anyString());
		Mockito.verify(ruleMock).getName();
		
		Mockito.verify(ruleMock).setDescricao(Mockito.anyString());
		Mockito.verify(ruleMock).getDescricao();
	}
	
	@Test
	public void validateAttribute() {
		Attribute attributeMock = Mockito.mock(Attribute.class);
		
		attributeMock.setName(Mockito.anyString());
		attributeMock.getName();
		
		attributeMock.setDescricao(Mockito.anyString());
		attributeMock.getDescricao();
		
		Mockito.verify(attributeMock).setName(Mockito.anyString());
		Mockito.verify(attributeMock).getName();
		
		Mockito.verify(attributeMock).setDescricao(Mockito.anyString());
		Mockito.verify(attributeMock).getDescricao();
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
