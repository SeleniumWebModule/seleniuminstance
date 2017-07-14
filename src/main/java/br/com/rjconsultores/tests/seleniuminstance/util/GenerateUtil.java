package br.com.rjconsultores.tests.seleniuminstance.util;

import java.util.UUID;

public class GenerateUtil {
	public static String getIDForNewObject() {
		return Long.toHexString(UUID.randomUUID().getMostSignificantBits()) + Long.toHexString(UUID.randomUUID().getLeastSignificantBits() + System.currentTimeMillis()); 
	}
}
