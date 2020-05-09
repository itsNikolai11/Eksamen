package org.example.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class UserFileStringWriter {

    public static void writeUserString(Path path, String userString) throws IOException{
        Files.write(path, userString.getBytes());
    }
}
