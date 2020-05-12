package org.example;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.Components.ComponentRegister;
import java.io.File;

public class FileHandler {


    private enum DialogMode {Open, Save}

    public static void saveFile(Stage stage, ComponentRegister componentRegister){
        File selectedFile = fileFromChooser(DialogMode.Save, stage);
        //TODO Trenger feilhåndtering med info ved feil med lagring + ved suksesfull lagring.
    }

    public static void openFile(Stage stage, ComponentRegister componentRegister){
        File selectedFile = fileFromChooser(DialogMode.Open, stage);
        //TODO Trenger feilhåndtering for forsøk på lesing av feil type fil + Feilmelding for evt feil ved åpning av fil.
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


    private File getFile(){
      return null;
    }

    //TODO Måte å returnere filen valgt av fileFromChooser.
}
