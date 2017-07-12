package br.com.rjconsultores.tests.seleniuminstance.util;

import br.com.rjconsultores.tests.seleniuminstance.enums.SourceEvent;
import br.com.rjconsultores.tests.seleniuminstance.exception.FieldRequireException;
import br.com.rjconsultores.tests.seleniuminstance.exception.FieldSizeOverflowException;
import br.com.rjconsultores.tests.seleniuminstance.exception.SeleniumInstanceException;

public class ValidateUtil {

	/**
	 * 
	 * @param fieldName Nome do campo a ser validado
	 * @param fieldValue Valor do campo a ser validado
	 * @throws FieldRequireException Exceção caso os valores estejam nulos ou em branco
	 */
	private static void validateNullValue(SourceEvent sourceEvent, String fieldName, String fieldValue) throws FieldRequireException {
		if (fieldValue == null || fieldValue.isEmpty()) {
			throw new FieldRequireException(sourceEvent, fieldName);
		}
	}

	private static void validateSizeOverflow(SourceEvent sourceEvent, String fieldName, String fieldValue, int maxLength) throws FieldSizeOverflowException {
		if (fieldValue.length() > maxLength) {
			throw new FieldSizeOverflowException(sourceEvent, fieldName);
		}
	}
	
	public static void validateField(SourceEvent sourceEvent, String fieldName, String fieldValue, int maxLength) throws SeleniumInstanceException {
		ValidateUtil.validateNullValue(sourceEvent, fieldName, fieldValue);
		ValidateUtil.validateSizeOverflow(sourceEvent, fieldName, fieldValue, maxLength);
	}
}
