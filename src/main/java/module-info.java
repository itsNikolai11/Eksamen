module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    //requires kotlin.stdlib;

    opens org.example to javafx.fxml;
    exports org.example;
    opens org.example.Components to javafx.base;

}