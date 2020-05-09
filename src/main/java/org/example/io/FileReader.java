package org.example.io;

import org.example.CarRegister;
import java.io.IOException;
import java.nio.file.Path;

public interface FileReader {

    void open(CarRegister carRegister, Path filepath) throws IOException;
}
