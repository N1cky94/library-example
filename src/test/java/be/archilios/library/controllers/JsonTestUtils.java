package be.archilios.library.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class JsonTestUtils {
    private static final String BASE_PATH = "src/test/resources/json/";
    private static final String BASE_EXTENSION = ".json";
    
    public static String read(String fileName) throws IOException {
        return new String(
                Files.readAllBytes(
                        Paths.get(
                                BASE_PATH + fileName + BASE_EXTENSION
                        )
                )
        );
    }
}
