package org.example.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class AdminFileStringWriter {

    public static void writeAdminString(Path path, String adminString) throws IOException{
        Files.write(path, adminString.getBytes());
    }
}
