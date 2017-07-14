package br.com.rjconsultores.tests.seleniuminstance.service.test.register;

import org.junit.Test;
import org.mockito.Mockito;

import br.com.rjconsultores.tests.seleniuminstance.service.test.utilities.ConstanteUtil;
import br.com.rjconsultores.tests.seleniuminstance.service.test.utilities.GenerateUtil;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.Attribute;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.Component;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.Event;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.Rule;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.Screen;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.System;

public class ValidateEntriesTest {
	
	@Test
	public void validateSystem() {
		System system = Mockito.mock(System.class);
		
		String name = GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME);
		String address = GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_ADDRESS);
		String port = GenerateUtil.getRandomNumber(ConstanteUtil.SIZE_DEFAULT_PORT);
		String id = br.com.rjconsultores.tests.seleniuminstance.util.GenerateUtil.getIDForNewObject();
		String parentId = br.com.rjconsultores.tests.seleniuminstance.util.GenerateUtil.getIDForNewObject();
		
		system.setName(name);
		system.getName();
		
		system.setAddress(address);
		system.getAddress();
		
		system.setPort(port);
		system.getPort();
		
		system.setId(id);
		system.getId();
		
		system.setParentId(parentId);
		system.getParentId();
		
		Mockito.verify(system).setId(id);
		Mockito.verify(system).getId();
		
		Mockito.verify(system).setParentId(parentId);
		Mockito.verify(system).getParentId();
		
		Mockito.verify(system).setName(name);
		Mockito.verify(system).getName();
		
		Mockito.verify(system).setAddress(address);
		Mockito.verify(system).getAddress();
		
		Mockito.verify(system).setPort(port);
		Mockito.verify(system).getPort();
	}
	
	@Test
	public void validateScreen() {
		Screen screen = Mockito.mock(Screen.class);
		
		String name = GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME);
		String id = br.com.rjconsultores.tests.seleniuminstance.util.GenerateUtil.getIDForNewObject();
		String parentId = br.com.rjconsultores.tests.seleniuminstance.util.GenerateUtil.getIDForNewObject();
		
		screen.setName(name);
		screen.getName();
		
		screen.setId(id);
		screen.getId();
		
		screen.setParentId(parentId);
		screen.getParentId();
		
		Mockito.verify(screen).setId(id);
		Mockito.verify(screen).getId();
		
		Mockito.verify(screen).setParentId(parentId);
		Mockito.verify(screen).getParentId();
		
		Mockito.verify(screen).setName(name);
		Mockito.verify(screen).getName();
	}
	
	@Test
	public void validateComponent() {
		Component component = Mockito.mock(Component.class);
		
		String name = GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME);
		String id = br.com.rjconsultores.tests.seleniuminstance.util.GenerateUtil.getIDForNewObject();
		String parentId = br.com.rjconsultores.tests.seleniuminstance.util.GenerateUtil.getIDForNewObject();
		
		component.setName(name);
		component.getName();
		
		component.setId(id);
		component.getId();
		
		component.setParentId(parentId);
		component.getParentId();
		
		Mockito.verify(component).setId(id);
		Mockito.verify(component).getId();
		
		Mockito.verify(component).setParentId(parentId);
		Mockito.verify(component).getParentId();
		
		Mockito.verify(component).setName(name);
		Mockito.verify(component).getName();
	}
	
	@Test
	public void validateRule() {
		Rule rule = Mockito.mock(Rule.class);
		
		String name = GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME);
		String description = GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_DESCRIPTION);
		String id = br.com.rjconsultores.tests.seleniuminstance.util.GenerateUtil.getIDForNewObject();
		String parentId = br.com.rjconsultores.tests.seleniuminstance.util.GenerateUtil.getIDForNewObject();
		
		rule.setName(name);
		rule.getName();
		
		rule.setDescription(description);
		rule.getDescription();
		
		rule.setId(id);
		rule.getId();
		
		rule.setParentId(parentId);
		rule.getParentId();
		
		Mockito.verify(rule).setId(id);
		Mockito.verify(rule).getId();
		
		Mockito.verify(rule).setParentId(parentId);
		Mockito.verify(rule).getParentId();
		
		Mockito.verify(rule).setName(name);
		Mockito.verify(rule).getName();
		
		Mockito.verify(rule).setDescription(description);
		Mockito.verify(rule).getDescription();
	}
	
	@Test
	public void validateEvent() {
		Event event = Mockito.mock(Event.class);
		
		String name = GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME);
		String description = GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_DESCRIPTION);
		String id = br.com.rjconsultores.tests.seleniuminstance.util.GenerateUtil.getIDForNewObject();
		String parentId = br.com.rjconsultores.tests.seleniuminstance.util.GenerateUtil.getIDForNewObject();
		
		event.setName(name);
		event.getName();
		
		event.setDescription(description);
		event.getDescription();
		
		event.setId(id);
		event.getId();
		
		event.setParentId(parentId);
		event.getParentId();
		
		Mockito.verify(event).setId(id);
		Mockito.verify(event).getId();
		
		Mockito.verify(event).setParentId(parentId);
		Mockito.verify(event).getParentId();
		
		Mockito.verify(event).setName(name);
		Mockito.verify(event).getName();
		
		Mockito.verify(event).setDescription(description);
		Mockito.verify(event).getDescription();
	}
	
	@Test
	public void validateAttribute() {
		Attribute attribute = Mockito.mock(Attribute.class);
		
		String name = GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME);
		String description = GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_DESCRIPTION);
		String id = br.com.rjconsultores.tests.seleniuminstance.util.GenerateUtil.getIDForNewObject();
		String parentId = br.com.rjconsultores.tests.seleniuminstance.util.GenerateUtil.getIDForNewObject();
		
		attribute.setName(name);
		attribute.getName();
		
		attribute.setDescription(description);
		attribute.getDescription();
		
		attribute.setId(id);
		attribute.getId();
		
		attribute.setParentId(parentId);
		attribute.getParentId();
		
		Mockito.verify(attribute).setId(id);
		Mockito.verify(attribute).getId();
		
		Mockito.verify(attribute).setParentId(parentId);
		Mockito.verify(attribute).getParentId();
		
		Mockito.verify(attribute).setName(name);
		Mockito.verify(attribute).getName();
		
		Mockito.verify(attribute).setDescription(description);
		Mockito.verify(attribute).getDescription();
	}
}
