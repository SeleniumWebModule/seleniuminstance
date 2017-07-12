package br.com.rjconsultores.tests.seleniuminstance.service.test.register;

import org.junit.Before;
import org.junit.Test;

import br.com.rjconsultores.tests.seleniuminstance.enums.SourceEvent;
import br.com.rjconsultores.tests.seleniuminstance.service.test.utilities.VerifyUtil;

public class RegisterTest {
	
	private SourceEvent sourceEvent;
	
	
	@Before
	public void init() {
		sourceEvent = SourceEvent.REGISTER_SERVICE;
	}
	
	@Test
	public void validateRequestNull( ) {
		VerifyUtil.verifyAndThrowsResourceRequireException(null, sourceEvent, "A requisi��o n�o pode ter um valor null. "
				+ "Voc� deve passar um sistema com suas telas e componentes.");
	}
}
