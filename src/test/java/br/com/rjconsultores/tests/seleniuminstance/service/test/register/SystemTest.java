package br.com.rjconsultores.tests.seleniuminstance.service.test.register;

import org.junit.Before;
import org.junit.Test;

import br.com.rjconsultores.tests.seleniuminstance.enums.SourceEvent;
import br.com.rjconsultores.tests.seleniuminstance.service.test.utilities.ConstanteUtil;
import br.com.rjconsultores.tests.seleniuminstance.service.test.utilities.GenerateUtil;
import br.com.rjconsultores.tests.seleniuminstance.service.test.utilities.VerifyUtil;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.Screen;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.System;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.service.request.Request;

public class SystemTest {
	
	private SourceEvent sourceEvent;
	
	private String msgErrorScreenRequired;
	private String msgErrorScreenNullValue;
	
	@Before
	public void init() {
		sourceEvent = SourceEvent.SYSTEM;
		
		msgErrorScreenRequired = "Uma ou mais telas devem estar associadas ao sistema.";
		msgErrorScreenNullValue = "A tela deve ser um valor válido e nulo não é um valor válido.";
	}
	
	@Test
	public void validateNameRequiredError() {
		System system = new System();
		VerifyUtil.verifyAndThrowsFieldRequireException(doRegisters(system), sourceEvent, "name"); 
		
		system.setName("");
		VerifyUtil.verifyAndThrowsFieldRequireException(doRegisters(system), sourceEvent, "name");
	}
	
	@Test
	public void validateAddressRequiredError() {
		System system = new System();
		
		system.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		VerifyUtil.verifyAndThrowsFieldRequireException(doRegisters(system), sourceEvent, "address");
		
		system.setAddress("");
		VerifyUtil.verifyAndThrowsFieldRequireException(doRegisters(system), sourceEvent, "address");
	}
	
	@Test
	public void validatePortRequiredError() {
		System system = new System();
		
		system.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		system.setAddress(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		VerifyUtil.verifyAndThrowsFieldRequireException(doRegisters(system), sourceEvent, "port");
		
		system.setPort("");
		VerifyUtil.verifyAndThrowsFieldRequireException(doRegisters(system), sourceEvent, "port");
	}
	
	
	@Test
	public void validateNameOverflowError() {
		System system = new System();
		
		system.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_OVERFLOW_NAME));
		VerifyUtil.verifyAndThrowsFieldSizeOverflowException(doRegisters(system), sourceEvent, "name");
	}
	
	@Test
	public void validateAddressOverflowError() {
		System system = new System();
		
		system.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		system.setAddress(GenerateUtil.getRandomString(ConstanteUtil.SIZE_OVERFLOW_ADDRESS));
		VerifyUtil.verifyAndThrowsFieldSizeOverflowException(doRegisters(system), sourceEvent, "address");
	}
	
	@Test
	public void validatePortOverflowError() {
		System system = new System();
		
		system.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		system.setAddress(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_ADDRESS));
		system.setPort(GenerateUtil.getRandomNumber(ConstanteUtil.SIZE_OVERFLOW_PORT));
		VerifyUtil.verifyAndThrowsFieldSizeOverflowException(doRegisters(system), sourceEvent, "port");
	}
	
	@Test
	public void validateErrorScreenRequire() {
		System system = new System();
		
		system.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		system.setAddress(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		system.setPort(GenerateUtil.getRandomNumber(ConstanteUtil.SIZE_DEFAULT_PORT));
		VerifyUtil.verifyAndThrowsRequireResourceException(doRegisters(system), sourceEvent, msgErrorScreenRequired);
	}
	
	@Test
	public void validateErrorScreenNullvalue() {
		System system = new System();
		
		system.setName(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_NAME));
		system.setAddress(GenerateUtil.getRandomString(ConstanteUtil.SIZE_DEFAULT_ADDRESS));
		system.setPort(GenerateUtil.getRandomNumber(ConstanteUtil.SIZE_DEFAULT_PORT));
		
		Screen screen = null;
		system.registerScreen(screen);
		
		VerifyUtil.verifyAndThrowsRequireResourceException(doRegisters(system), sourceEvent, msgErrorScreenNullValue);
	}
	
	private Request doRegisters(System system) {
		Request request = new Request();
		request.setSystem(system);
		
		return request;
	}
}
