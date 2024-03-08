package edu.finale_project.booking_tickets.dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;


public class DaoHelper {
	
	public static void writeFile(String payload, String fileName) throws IOException {
		writeFile(payload, fileName, false);
	}

	public static void writeFile(String payload, String fileName, boolean isAppend) throws IOException {
		if (isAppend) {
			Files.writeString(Paths.get("resources", fileName).toAbsolutePath(), payload, CREATE, APPEND);
		} else {

			Files.writeString(Paths.get("resources", fileName).toAbsolutePath(), payload, CREATE, TRUNCATE_EXISTING);
		}
	}

	public static List<String> readFileByLine(String fileName) throws IOException {
		return Files.readAllLines(Paths.get("resources", fileName).toAbsolutePath());
	}

}
