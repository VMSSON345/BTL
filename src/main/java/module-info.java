module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires javafx.web;
    requires freetts;
    requires voicerss.tts;
    requires java.desktop;
    requires javafx.media;

    opens com.example.demo to javafx.fxml;
    exports com.example.demo;

    opens com.example.demo.Controllers to javafx.fxml;
    exports com.example.demo.Controllers;
    opens com.example.demo.Game to javafx.fxml;
    exports com.example.demo.Game;

}
