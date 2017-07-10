package br.com.rjconsultores.tests.seleniuminstance.service.test.utilities;

import org.junit.Assert;

import br.com.rjconsultores.tests.webmodule.seleniuminstance.service.response.Response;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.service.response.ResponseError;

public class VerifyUtil {
	public static void verifySuccess(Response responseExpected, Response responseActual) {
		Assert.assertEquals(responseExpected.getClass(), responseActual.getClass());
		Assert.assertEquals(responseExpected.getStatusResponse(), responseActual.getStatusResponse());
	}
	
	public static void verifyError(ResponseError responseExpected, Response responseActual) {
		Assert.assertEquals(responseExpected.getClass(), responseActual.getClass());
		Assert.assertEquals(responseExpected.getStatusResponse(), responseActual.getStatusResponse());
		Assert.assertEquals(responseExpected.getInstanceException().getMessage(), responseActual.getInstanceException().getMessage());
	}
}
