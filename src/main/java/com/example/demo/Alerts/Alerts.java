package com.example.demo.Alerts;

import javafx.scene.control.Alert;

public class Alerts {
    public void showAlertInformation(String title, String content) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(content);
        a.showAndWait();
    }

    public void showAlertWarning(String t, String content) {
        Alert a = new Alert(Alert.AlertType.WARNING);
        a.setTitle(t);
        a.setTitle(null);
        a.setContentText(content);
        a.showAndWait();
    }

    public Alert alertWarning(String t, String content) {
        Alert a = new Alert(Alert.AlertType.WARNING);
        a.setTitle(t);
        a.setHeaderText(null);
        a.setContentText(content);
        return a;
    }

    public Alert alertConfirmation(String t, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(t);
        alert.setHeaderText(content);
        alert.setContentText(content);
        return alert;
    }

}