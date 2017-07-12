package br.com.rjconsultores.tests.seleniuminstance.service.test.utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class GenerateUtil {
	public static String getRandomString(int quantity) {
		return RandomStringUtils.randomAlphabetic(quantity);
	}

	public static String getRandomNumber(int quantity) {
		return RandomStringUtils.randomNumeric(quantity);
	}
}
