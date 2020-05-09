package org.example.Gui;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class FileHandler {

    private enum DialogMode {Open, Save}

    static void saveFile(Stage stage){
        File selectedFile = fileFromChooser(DialogMode.Save, stage);
        //Trenger feilhåndtering med info ved feil med lagring + ved suksesfull lagring.
    }

    static void openFile(Stage stage){
        File selectedFile = fileFromChooser(DialogMode.Open, stage);
        //Trenger feilhåndtering for forsøk på lesing av feil type fil + Feilmelding for evt feil ved åpning av fil.
    }


    private static File fileFromChooser(DialogMode mode, Stage stage){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Velg en fil");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text files", "*.txt"));

        if(mode == DialogMode.Open){
            return fileChooser.showOpenDialog(stage);
        }
        else{
            return fileChooser.showSaveDialog(stage);
        }
    }


    //Måte å returnere filen valgt av fileFromChooser.
}
