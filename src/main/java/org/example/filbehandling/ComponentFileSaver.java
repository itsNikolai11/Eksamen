package org.example.filbehandling;

import org.example.Components.CarComponent;
import org.example.Components.ComponentRegister;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ComponentFileSaver implements FileSaver {
    @Override
    public void save() throws IOException {

        save();
        //TODO Komponent array til fil.

       // Files.write(ComponentRegister.getCarComponents())
        //TODO prefikse lokasjon på fil

    }
}
