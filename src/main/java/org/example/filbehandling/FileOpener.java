package org.example.filbehandling;

import java.io.IOException;

public interface FileOpener {
    Object load() throws IOException, ClassNotFoundException;
}
