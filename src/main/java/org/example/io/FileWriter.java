package org.example.io;

import org.example.CarRegister;
import java.io.IOException;
import java.nio.file.Path;

public interface FileWriter {

    void save(CarRegister carRegister, Path filePath) throws IOException;
}
