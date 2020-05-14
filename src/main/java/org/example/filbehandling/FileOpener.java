package org.example.filbehandling;

import org.example.Components.ComponentRegister;

import java.io.IOException;
import java.nio.file.Path;

public interface FileOpener {
    Object open() throws IOException, ClassNotFoundException;
}
