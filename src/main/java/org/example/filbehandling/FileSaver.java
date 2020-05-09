package org.example.filbehandling;

import org.example.Components.CarComponent;
import org.example.Components.ComponentRegister;

import java.io.IOException;
import java.nio.file.Path;

public interface FileSaver {
    void save(ComponentRegister componentRegister, Path filePath) throws IOException, InterruptedException;
}

