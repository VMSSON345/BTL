package com.example.demo.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    public AnchorPane mainContent = new AnchorPane();
    @FXML
    public AnchorPane searchPane;
    @FXML
    public AnchorPane translatePane;
    @FXML
    public AnchorPane savePane;
    @FXML
    public AnchorPane addPane;
    @FXML
    public AnchorPane settingPane;

    @FXML
    public AnchorPane lisPane;

    @FXML
    public AnchorPane gamePane;

    @FXML
    public SearchController searchController;
    @FXML
    public SaveController saveController;
    @FXML
    public AddController addController;

    @FXML
    public TranslateController translateController;

    @FXML
    public VideoController videoController;

    @FXML
    public Menu menu;

    @FXML
    public Button searchButton;
    @FXML
    public Button translateButton;
    @FXML
    public Button saveButton;

    @FXML
    public Button addButton;
    @FXML
    public Button settingButton;

    @FXML
    public Button gameButton;

    @FXML
    public Button lisButton;


    public void setMainContent(AnchorPane anchorPane) {
        mainContent.getChildren().setAll(anchorPane);
    }

    public void setNode(Node node) {
        mainContent.getChildren().clear();
        mainContent.getChildren().add((Node) node);


    }


    public void resetStyleNav() {
        searchButton.getStyleClass().removeAll("active");
        translateButton.getStyleClass().removeAll("active");
        saveButton.getStyleClass().removeAll("active");
        gameButton.getStyleClass().removeAll("active");
        lisButton.getStyleClass().removeAll("active");
        addButton.getStyleClass().removeAll("active");
    }

    @FXML
    public void showSearchPane() {
        resetStyleNav();
        searchButton.getStyleClass().add("active");
        setNode(searchPane);
        setMainContent(searchPane);
    }

    @FXML
    public void showTranslatePane() {
        resetStyleNav();
        translateButton.getStyleClass().add("active");
        setNode(translatePane);
        setMainContent(translatePane);
    }

    @FXML
    public void showBookmarkPane() {
        resetStyleNav();
        saveButton.getStyleClass().add("active");
        setNode(savePane);
        setMainContent(savePane);
    }

    @FXML
    public void showGamePane() {
        resetStyleNav();
        gameButton.getStyleClass().add("active");
        setNode(gamePane);
        setMainContent(gamePane);
    }

    @FXML
    public void showAddPane() {
        resetStyleNav();
        addButton.getStyleClass().add("active");
        setNode(addPane);
        setMainContent(addPane);
    }

    @FXML
    public void showSettingPane() {
        resetStyleNav();
        settingButton.getStyleClass().add("active");
        setMainContent(settingPane);
    }

    @FXML
    public void showLisPane() {
        resetStyleNav();
        lisButton.getStyleClass().add("active");
        setNode(lisPane);
        setMainContent(lisPane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FXMLLoader searchLoader = new FXMLLoader(getClass().getResource("/com/example/demo/SearchController.fxml"));
            searchPane = searchLoader.load();
            searchController = searchLoader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FXMLLoader addLoader = new FXMLLoader(getClass().getResource("/com/example/demo/AddController.fxml"));
            addPane = addLoader.load();
            addController = addLoader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FXMLLoader saveLoader = new FXMLLoader(getClass().getResource("/com/example/demo/SaveController.fxml"));
            savePane = saveLoader.load();
            saveController = saveLoader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FXMLLoader translateLoader = new FXMLLoader(getClass().getResource("/com/example/demo/TranslateController.fxml"));
            translatePane = translateLoader.load();
            translateController = translateLoader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            FXMLLoader translateLoader = new FXMLLoader(getClass().getResource("/com/example/demo/Menu.fxml"));
            gamePane = translateLoader.load();
            menu = translateLoader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            FXMLLoader searchLoader = new FXMLLoader(getClass().getResource("/com/example/demo/VideoController.fxml"));
            lisPane = searchLoader.load();
            videoController = searchLoader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }

        searchButton.getStyleClass().add("active");
        mainContent.getChildren().setAll(searchPane);
    }
}
