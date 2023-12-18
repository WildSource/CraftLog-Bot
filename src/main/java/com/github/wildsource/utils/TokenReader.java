package com.github.wildsource.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TokenReader {

	public static String getToken(String tokenPath) {
		String token = null;
		try {
			File tokenFile = new File(tokenPath);
			Scanner tokenScanner = new Scanner(tokenFile);
			token = tokenScanner.nextLine();
			tokenScanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		return token;
	}
}
