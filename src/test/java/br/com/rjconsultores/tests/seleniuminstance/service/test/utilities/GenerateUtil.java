package br.com.rjconsultores.tests.seleniuminstance.service.test.utilities;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

public class GenerateUtil {
	private static Random random = new Random();
	
	
	public static String getRandomString(int quantity) {
		return RandomStringUtils.randomAlphabetic(quantity);
	}
	
	public static Integer getRandomInt(int quantity) {
		return random.nextInt(quantity);
	}
}
