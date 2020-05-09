package org.example.filbehandling;

import org.example.Components.CarComponent;
import org.example.Components.ComponentRegister;

import java.io.IOException;

public interface FileOpener {
    Object load(ComponentRegister componentRegister) throws IOException, ClassNotFoundException;
}
