package org.example.filbehandling;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SuperUserFileOpener implements FileOpener {
    @Override
    public Object load() throws IOException, ClassNotFoundException {
        InputStream is = Files.newInputStream(Paths.get("users.jobj"));
        ObjectInputStream in = new ObjectInputStream(is);
        return in.readObject();
    }
}
