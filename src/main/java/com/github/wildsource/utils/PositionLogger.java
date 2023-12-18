package com.github.wildsource.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.github.wildsource.Coordinate;

public class PositionLogger {
	public static boolean createCoordinate(String coordinate) {
		boolean status = true;
		try {
			List<String> tokens = parseCommandTokenList(coordinate);
			Coordinate temp = convertStringToCoordinate(tokens);
			File log = createFile(temp.name());
			writeObjectCoordinate(log, temp);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			status = false;
		} catch (IOException e) {
			e.printStackTrace();
			status = false;
		} catch (Exception e) {
			e.printStackTrace();
			status = false;
		}
		return status;
	}

	private static File createFile(String name) throws IOException {
		File file = new File("src/main/resources/coordinates/" + name);
		file.createNewFile();
		return file;
	}

	private static void writeObjectCoordinate(File log, Coordinate coordinate)
			throws FileNotFoundException, IOException {
		FileOutputStream fileStream = new FileOutputStream(log);
		ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
		objectStream.writeObject(coordinate);
		fileStream.close();
		objectStream.close();
	}

	private static void writeStringCoordinate(File log, String coordinates) throws IOException {
		FileWriter writer = new FileWriter(log);
		String trimmedString = parseCommandString(coordinates);
		writer.write(trimmedString);
		writer.close();
	}

	private static List<String> parseCommandTokenList(String rawString) {
		String temp = parseCommandString(rawString);
		return getTokens(temp);
	}

	private static String parseCommandString(String rawString) {
		return rawString.replace("!newPos ", "");
	}

	private static Coordinate convertStringToCoordinate(List<String> coordinateData) {
		String name = coordinateData.get(0);
		int x = Integer.parseInt(coordinateData.get(1));
		int y = Integer.parseInt(coordinateData.get(1));
		int z = Integer.parseInt(coordinateData.get(1));
		return new Coordinate(name, x, y, z);
	}

	private static List<String> getTokens(String str) {
		List<String> tokens = new ArrayList<>();
		StringTokenizer tokenizer = new StringTokenizer(str, " ");
		while (tokenizer.hasMoreElements()) {
			tokens.add(tokenizer.nextToken());
		}
		return tokens;
	}

}
