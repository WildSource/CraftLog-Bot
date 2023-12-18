package com.github.wildsource.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import org.javacord.api.entity.message.MessageBuilder;

import com.github.wildsource.Coordinate;

public class PositionReader {
	public static Object readObjectFromFile(File file) throws IOException, ClassNotFoundException {
		Object result = null;
		try (FileInputStream fis = new FileInputStream(file); ObjectInputStream ois = new ObjectInputStream(fis)) {
			result = ois.readObject();
		}
		return result;
	}

	public static List<Coordinate> getCoordinates() {
		List<Coordinate> coordinates = new ArrayList<Coordinate>();
		File directory = new File("src/main/resources/coordinates/");
		File[] coordinatesFiles = directory.listFiles();
		for (File file : coordinatesFiles) {
			Coordinate temp;
			try {
				temp = (Coordinate) readObjectFromFile(file);
				coordinates.add(temp);
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}
		return coordinates;
	}

	public static StringBuilder listPositions() {
		return new MessageBuilder()	.append(getCoordinates())
									.getStringBuilder();
	}
}
