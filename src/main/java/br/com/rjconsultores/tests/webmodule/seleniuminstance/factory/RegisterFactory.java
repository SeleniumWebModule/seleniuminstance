package br.com.rjconsultores.tests.webmodule.seleniuminstance.factory;

import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.Screen;

public class RegisterFactory {
	private static RegisterFactory INSTANCE;
	
	RegisterFactory() {}
	
	public static RegisterFactory INSTANCE() {
		if (INSTANCE == null) {
			INSTANCE = new RegisterFactory();
		}
		
		return INSTANCE;
	}
	
	public void registerSystem(System system) {
		InstanceFactory.getSystems().add(system);
	}
	
	public void registerScreen(Screen screen) {
		InstanceFactory.getScreens().add(screen);
	}
}
