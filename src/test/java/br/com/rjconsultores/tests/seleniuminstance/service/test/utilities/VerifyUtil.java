package br.com.rjconsultores.tests.seleniuminstance.service.test.utilities;

import org.junit.Assert;

import br.com.rjconsultores.tests.seleniuminstance.enums.SourceEvent;
import br.com.rjconsultores.tests.seleniuminstance.exception.FieldRequireException;
import br.com.rjconsultores.tests.seleniuminstance.exception.FieldSizeOverflowException;
import br.com.rjconsultores.tests.seleniuminstance.exception.RequiredResourceException;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.service.Register;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.service.request.Request;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.service.response.Response;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.service.response.ResponseError;

public class VerifyUtil {
	public static void verifySuccess(Response responseExpected, Response responseActual) {
		Assert.assertEquals(responseExpected.getClass(), responseActual.getClass());
		Assert.assertEquals(responseExpected.getStatusResponse(), responseActual.getStatusResponse());
	}
	
	private static void verifyError(ResponseError responseExpected, Response responseActual) {
		Assert.assertEquals(responseExpected.getClass(), 
				responseActual.getClass());
		
		Assert.assertEquals(responseExpected.getStatusResponse(), 
				responseActual.getStatusResponse());
		
		Assert.assertEquals(responseExpected.getInstanceException().getMessage(), 
				responseActual.getInstanceException().getMessage());
		
		Assert.assertEquals(responseExpected.getInstanceException().getSourceEvent().getDescription(), 
				responseActual.getInstanceException().getSourceEvent().getDescription());
	}
	
	public static void verifyAndThrowsRequireResourceException(Request request, SourceEvent sourceEvent, String message) {
		VerifyUtil.verifyError(new ResponseError(new RequiredResourceException(sourceEvent, message)),
				new Register().doRegisterSystem(request));		
	}
	
	public static void verifyAndThrowsFieldSizeOverflowException(Request request, SourceEvent sourceEvent, String fieldName) {
		VerifyUtil.verifyError(new ResponseError(new FieldSizeOverflowException(sourceEvent, fieldName)),
				new Register().doRegisterSystem(request));		
	}
	
	public static void verifyAndThrowsFieldRequireException(Request request, SourceEvent sourceEvent, String fieldName) {
		VerifyUtil.verifyError(new ResponseError(new FieldRequireException(sourceEvent, fieldName)),
				new Register().doRegisterSystem(request));		
	}
}
