package br.com.rjconsultores.tests.seleniuminstance.util;

import br.com.rjconsultores.tests.seleniuminstance.exception.FieldRequireException;

public class ValidateUtil {

	/**
	 * 
	 * @param fieldName Nome do campo a ser validado
	 * @param fieldValue Valor do campo a ser validado
	 * @throws FieldRequireException Exceção caso os valores estejam nulos ou em branco
	 */
	public static void validateNullValue(String fieldName, String fieldValue) throws FieldRequireException {
		if (fieldValue == null || fieldValue.isEmpty()) {
			throw new FieldRequireException(fieldName);
		}
	}
}
