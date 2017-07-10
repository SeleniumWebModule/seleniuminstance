package br.com.rjconsultores.tests.webmodule.seleniuminstance.factory;

import java.util.Collection;

import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.System;

public class RequestFactory {
	private static RequestFactory INSTANCE;
	
	public RequestFactory() {};
	
	public static RequestFactory INSTANCE() {
		if (INSTANCE == null) {
			INSTANCE = new RequestFactory();
		}
		
		return INSTANCE;
	}
	
	public Collection<System> requestSystems() {
		return InstanceFactory.INSTANCE().getSystems();
	}
}
