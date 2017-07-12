package br.com.rjconsultores.tests.seleniuminstance.service.test.register;

import org.junit.Test;
import org.mockito.Mockito;

import br.com.rjconsultores.tests.seleniuminstance.enums.OperationType;
import br.com.rjconsultores.tests.seleniuminstance.exception.SeleniumInstanceException;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.System;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.factory.RegisterFactory;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.service.Register;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.service.request.Request;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.service.response.Response;

public class RegisterServiceMockTest {

	@SuppressWarnings("unchecked")
	@Test
	public void validateReturns() throws SeleniumInstanceException {
		RegisterFactory factory = Mockito.mock(RegisterFactory.class);

		Response responseRegisterFactory = factory.registerScreen(Mockito.any(Request.class));

		Mockito.when(factory.registerScreen(Mockito.any(Request.class))).thenReturn(responseRegisterFactory)
				.thenThrow(SeleniumInstanceException.class);

		Register register = Mockito.mock(Register.class);
		Response responseService = register.doRegisterSystem(Mockito.any(Request.class));

		Mockito.when(register.doRegisterSystem(Mockito.any(Request.class))).thenReturn(responseService);
	}

	@Test
	public void validateRequestSuccessMock() {
		Register registerMock = Mockito.mock(Register.class);
		Request requestMock = Mockito.mock(Request.class);
		System systemMock = Mockito.mock(System.class);

		systemMock.setAddress(Mockito.anyString());
		systemMock.setName(Mockito.anyString());
		systemMock.setPort(Mockito.anyString());
		requestMock.setSystem(systemMock);

		requestMock.setSystem(systemMock);
		requestMock.setOperationType(OperationType.INSERT);

		Response responseSucess = registerMock.doRegisterSystem(requestMock);
		Mockito.when(registerMock.doRegisterSystem(requestMock)).thenReturn(responseSucess);
	}
}
