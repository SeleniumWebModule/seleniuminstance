package br.com.rjconsultores.tests.seleniuminstance.service.test.register;

import org.junit.Test;
import org.mockito.Mockito;

import br.com.rjconsultores.tests.seleniuminstance.enums.OperationType;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.System;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.service.request.Request;

public class RequestTest {
	
	@Test
	public void validateRequestSystem() {
		Request request = Mockito.mock(Request.class);
		System system = Mockito.mock(System.class);
		
		request.setSystem(system);
		request.getSystem();
		
		Mockito.verify(request).setSystem(system);
		Mockito.verify(request).getSystem();
	}
	
	@Test
	public void validateRequestOperationInsert() {
		Request request = Mockito.mock(Request.class);
		
		OperationType operationType = OperationType.INSERT;
		
		request.setOperationType(operationType);
		request.getOperationType();
		
		Mockito.verify(request).setOperationType(operationType);
		Mockito.verify(request).getOperationType();
	}
	
	@Test
	public void validateRequestOperationUpdate() {
		Request request = Mockito.mock(Request.class);
		
		OperationType operationType = OperationType.UPDATE;
		
		request.setOperationType(operationType);
		request.getOperationType();
		
		Mockito.verify(request).setOperationType(operationType);
		Mockito.verify(request).getOperationType();
	}
	
	@Test
	public void validateRequestOperationDelete() {
		Request request = Mockito.mock(Request.class);
		
		OperationType operationType = OperationType.DELETE;
		
		request.setOperationType(operationType);
		request.getOperationType();
		
		Mockito.verify(request).setOperationType(operationType);
		Mockito.verify(request).getOperationType();
	}
}
