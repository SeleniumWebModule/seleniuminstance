package br.com.rjconsultores.tests.webmodule.seleniuminstance.factory;

public class RequestFactory {
	private static RequestFactory INSTANCE;
	
	public RequestFactory() {};
	
	public static RequestFactory INSTANCE() {
		if (INSTANCE == null) {
			INSTANCE = new RequestFactory();
		}
		
		return INSTANCE;
	}
}
