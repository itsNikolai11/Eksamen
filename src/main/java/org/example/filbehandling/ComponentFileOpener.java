package org.example.filbehandling;

import org.example.Components.CarComponent;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ComponentFileOpener implements FileOpener {

    @Override
    public Object load() throws IOException, ClassNotFoundException {
        InputStream is = Files.newInputStream(Paths.get("component.jobj"));
        ObjectInputStream in = new ObjectInputStream(is);
        return in.readObject();
    }
}
