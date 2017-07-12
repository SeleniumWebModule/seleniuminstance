package br.com.rjconsultores.tests.seleniuminstance.service.test.register;

import org.junit.Before;
import org.junit.Test;

import br.com.rjconsultores.tests.seleniuminstance.enums.SourceEvent;
import br.com.rjconsultores.tests.seleniuminstance.exception.FieldRequireException;
import br.com.rjconsultores.tests.seleniuminstance.exception.FieldSizeOverflowException;
import br.com.rjconsultores.tests.seleniuminstance.exception.ResourceRequiredException;
import br.com.rjconsultores.tests.seleniuminstance.service.test.utilities.ConstanteUtil;
import br.com.rjconsultores.tests.seleniuminstance.service.test.utilities.GenerateUtil;
import br.com.rjconsultores.tests.seleniuminstance.service.test.utilities.VerifyUtil;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.System;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.service.Register;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.service.request.Request;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.service.response.ResponseError;

public class SystemTest {
	
	private Register register;
	private Request request;
	
	@Before
	public void init() {
		register = new Register();
	}
	
	@Test
	public void validateNameRequiredError() {
		request = new Request();
		
		System system = new System();
		
		system.setName(null);
		request.setSystem(system);
		VerifyUtil.verifyError(new ResponseError(new FieldRequireException(SourceEvent.SYSTEM, "name")), 
		register.doRegisterSystem(request));
		
		system.setName("");
		request.setSystem(system);
		VerifyUtil.verifyError(new ResponseError(new FieldRequireException(SourceEvent.SYSTEM, "name")), 
				register.doRegisterSystem(request));
		
	}
	
	@Test
	public void validateAddressRequiredError() {
		request = new Request();
		
		System system = new System();
		
		system.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		
		system.setAddress(null);
		request.setSystem(system);
		VerifyUtil.verifyError(new ResponseError(new FieldRequireException(SourceEvent.SYSTEM, "address")), 
		register.doRegisterSystem(request));
		
		system.setAddress("");
		request.setSystem(system);
		VerifyUtil.verifyError(new ResponseError(new FieldRequireException(SourceEvent.SYSTEM, "address")), 
				register.doRegisterSystem(request));
	}
	
	@Test
	public void validatePortRequiredError() {
		request = new Request();
		
		System system = new System();
		
		system.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		system.setAddress(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		
		system.setPort(null);
		request.setSystem(system);
		VerifyUtil.verifyError(new ResponseError(new FieldRequireException(SourceEvent.SYSTEM, "port")), 
		register.doRegisterSystem(request));
		
		system.setPort("");
		request.setSystem(system);
		VerifyUtil.verifyError(new ResponseError(new FieldRequireException(SourceEvent.SYSTEM, "port")), 
				register.doRegisterSystem(request));
	}
	
	@Test
	public void validateScreenRequireError() {
		request = new Request();
		
		System system = new System();
		
		system.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		system.setAddress(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		system.setPort(GenerateUtil.getRandomNumber(ConstanteUtil.SIZE_DEFAULT_PORT));
		
		system.registerScreen(null);
		request.setSystem(system);
		
		VerifyUtil.verifyError(new ResponseError(new ResourceRequiredException(SourceEvent.SYSTEM, 
				"Uma tela é esperada como parametro para o sistema, foi encontrado null.")), 
		register.doRegisterSystem(request));
	}
	
	@Test
	public void validateNameOverflowError() {
		request = new Request();
		System system = new System();
		
		system.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_OVERFLOW_NAME));
		request.setSystem(system);
		
		VerifyUtil.verifyError(new ResponseError(new FieldSizeOverflowException(SourceEvent.SYSTEM, "name")),
			register.doRegisterSystem(request));
	}
	
	@Test
	public void validateAddressOverflowError() {
		request = new Request();
		System system = new System();
		
		system.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		system.setAddress(GenerateUtil.getRandomString(ConstanteUtil.SIZE_OVERFLOW_ADDRESS));
		request.setSystem(system);
		
		VerifyUtil.verifyError(new ResponseError(new FieldSizeOverflowException(SourceEvent.SYSTEM, "address")),
			register.doRegisterSystem(request));
	}
	
	@Test
	public void validatePortOverflowError() {
		request = new Request();
		System system = new System();
		
		system.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		system.setAddress(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_ADDRESS));
		system.setPort(GenerateUtil.getRandomNumber(ConstanteUtil.SIZE_OVERFLOW_PORT));
		request.setSystem(system);
		
		VerifyUtil.verifyError(new ResponseError(new FieldSizeOverflowException(SourceEvent.SYSTEM, "port")),
			register.doRegisterSystem(request));
	}
	
	@Test
	public void validateScreenRequiredError() {
		request = new Request();
		System system = new System();
		
		system.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		system.setAddress(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_ADDRESS));
		system.setPort(GenerateUtil.getRandomNumber(ConstanteUtil.SIZE_DEFAULT_PORT));
		request.setSystem(system);
		
		VerifyUtil.verifyError(new ResponseError(new ResourceRequiredException(SourceEvent.SYSTEM,
				"No mínimo uma tela deve estar associada ao sistema.")),
			register.doRegisterSystem(request));
	}
}
