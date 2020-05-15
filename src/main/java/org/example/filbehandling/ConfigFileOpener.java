package org.example.filbehandling;

import org.example.Components.CarComponent;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ConfigFileOpener implements FileOpener {
    @Override
    public ArrayList<String> load() throws IOException, ClassNotFoundException {
        InputStream is = Files.newInputStream(Paths.get("configuration.jobj"));
        ObjectInputStream in = new ObjectInputStream(is);
        return (ArrayList<String>) in.readObject();
    }
}
