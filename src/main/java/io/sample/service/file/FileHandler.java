package io.sample.service.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileHandler {
	private static final Logger log = LoggerFactory.getLogger(FileHandler.class);
	public String handleFile(File input) throws IOException {
		log.info("Copying file: " + input.getAbsolutePath());
		
		return new String(Files.readAllBytes(input.toPath()));
	}
}
