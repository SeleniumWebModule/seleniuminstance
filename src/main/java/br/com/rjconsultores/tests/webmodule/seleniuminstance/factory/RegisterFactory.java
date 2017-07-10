package br.com.rjconsultores.tests.webmodule.seleniuminstance.factory;

import java.util.Collection;

import br.com.rjconsultores.tests.seleniuminstance.exception.ResourceRequiredException;
import br.com.rjconsultores.tests.seleniuminstance.exception.SeleniumInstanceException;
import br.com.rjconsultores.tests.seleniuminstance.util.ValidateUtil;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.Attribute;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.Component;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.Screen;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.System;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.service.request.Request;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.service.response.Response;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.service.response.ResponseError;

public class RegisterFactory {
	private static RegisterFactory INSTANCE;

	RegisterFactory() {
	}

	public static RegisterFactory INSTANCE() {
		if (INSTANCE == null) {
			INSTANCE = new RegisterFactory();
		}

		return INSTANCE;
	}

	public Response registerScreen(Request request) {
		System systemRequest = request.getSystem();
	
		try {	
			ValidateUtil.validateNullValue("name", systemRequest.getName());
			ValidateUtil.validateNullValue("address", systemRequest.getAddress());
			ValidateUtil.validateNullValue("port", systemRequest.getPort());

			if (systemRequest.listScreens() == null || systemRequest.listScreens().isEmpty()) {
				return new ResponseError(
						new ResourceRequiredException("No mínimo uma tela deve estar associada ao sistema."));
			}

			for (Screen screen : systemRequest.listScreens()) {
				ValidateUtil.validateNullValue("name", screen.getName());
				validateComponents(screen.listComponents());
			}
		} catch (SeleniumInstanceException instanceException) {
			return new ResponseError(instanceException);
		}

		return new Response();
	}
	
	private void validateComponents(Collection<Component> components) throws SeleniumInstanceException {
		if (components == null || components.isEmpty()) {
			throw new ResourceRequiredException("No mínimo um componente deve estar associado a tela.");
		}
		
		for (Component component: components) {
			ValidateUtil.validateNullValue("name", component.getName());
			ValidateUtil.validateNullValue("descricao", component.getDescricao());
			validateAttributesComponent(component.listAttributes());
		}
	}
	
	private void validateAttributesComponent(Collection<Attribute> attributes) throws SeleniumInstanceException {
		if (attributes == null || attributes.isEmpty()) {
			throw new ResourceRequiredException("No mínimo um atributo deve estar associado ao componente.");
		}
		
		for (Attribute attribute: attributes) {
			ValidateUtil.validateNullValue("name", attribute.getName());
			ValidateUtil.validateNullValue("descricao", attribute.getDescricao());
		}
	}
}
