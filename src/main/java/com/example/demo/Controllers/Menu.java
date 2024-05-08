package com.example.demo.Controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class Menu implements Initializable {
    @FXML
    private Button buttonInsert;
    @FXML
    private Button buttonPlayGame;

    @FXML
    private Button goBack;
    @FXML
    private AnchorPane container;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttonInsert.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showComponent("/com/example/demo/InsertQuestion.fxml");
            }
        });
        buttonPlayGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showComponent("/com/example/demo/PlayGame.fxml");
            }
        });
        goBack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showComponent("/com/example/demo/MainController.fxml");
            }
        });
    }

    @FXML
    private void showComponent(String path) {
        try {
            AnchorPane Component = FXMLLoader.load(getClass().getResource(path));
            setNode(Component);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void setNode(Node node) {
        if (this.container == null) {
            this.container = new AnchorPane();
        }
        this.container.getChildren().setAll(node);

        // Cập nhật UI
        Scene scene = buttonInsert.getScene();
        if (scene != null) {
            ((AnchorPane) scene.getRoot()).getChildren().setAll(this.container);
        }
    }


}