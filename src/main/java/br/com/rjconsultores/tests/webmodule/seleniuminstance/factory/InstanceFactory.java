package br.com.rjconsultores.tests.webmodule.seleniuminstance.factory;

import java.util.Collection;

import javax.inject.Inject;

import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.System;

class InstanceFactory {
	private static InstanceFactory INSTANCE;

	@Inject
	private Collection<System> systems;

	static InstanceFactory INSTANCE() {
		if (INSTANCE == null) {
			INSTANCE = new InstanceFactory();
		}

		return INSTANCE;
	}

	public Collection<System> getSystems() {
		return systems;
	}
}