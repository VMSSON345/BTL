package com.example.demo.Controllers;

import com.example.demo.Alerts.Alerts;
import com.example.demo.DictionaryApp.DictionaryManagement;
import com.example.demo.DictionaryApp.Word;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.demo.DictionaryApp.Dictionary;
import javafx.scene.web.HTMLEditor;

public class AddController implements Initializable {

    DictionaryManagement dictionaryManagement = new DictionaryManagement();

    Dictionary dictionary = new Dictionary();


    @FXML
    TextField textWord;


    @FXML
    Button add;


    @FXML
    HTMLEditor htmlEditor;

    Alerts alerts = new Alerts();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    handleClickAdd();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        add.setVisible(false);
    }

    @FXML
    private void handleClickAdd() throws IOException {
        if (htmlEditor.getHtmlText().isEmpty()) {
            alerts.showAlertWarning("Thông báo", "Vui lòng thêm từ");
        }
        String text1 = textWord.getText().trim().toLowerCase();
        String text2 = htmlEditor.getHtmlText().replace(" dir=\"ltr\"", "");
        dictionaryManagement.addWordtoDic(dictionary, text1, text2);
        SearchController.getInstance().updateDictionary();
        textWord.setText(" ");
        htmlEditor.setHtmlText(" ");
    }

    @FXML
    public void addReset() {
        add.setVisible(true);
        htmlEditor.setHtmlText("<html>" + textWord.getText() + " /" + textWord.getText() + "/"
                + "<ul><li><font color='#cc0000'><b> Nghĩa thứ nhất: </b></font><ul><li><b><i> bổ sung ý nghĩa: </i></b> " +
                "<ul></li></ul></ul></li></ul><ul><li><font color='#cc0000'><b> Nghĩa thứ hai: </b></font><ul><li><b><i> bổ sung ý nghĩa: </i></b> " +
                "<ul></li></ul></ul></li></ul>");
    }

    @FXML
    public void handleClickArrow() {
        add.setVisible(true);
        if (textWord.getText().isEmpty()) {
            alerts.showAlertWarning("Thông báo", "Vui lòng thêm từ");
            return;
        }
        htmlEditor.setHtmlText("<html>" + textWord.getText() + " /" + textWord.getText() + "/"
                + "<ul><li><font color='#cc0000'><b> Nghĩa thứ nhất: </b></font><ul><li><b><i> bổ sung ý nghĩa: </i></b> " +
                "<ul></li></ul></ul></li></ul><ul><li><font color='#cc0000'><b> Nghĩa thứ hai: </b></font><ul><li><b><i> bổ sung ý nghĩa: </i></b> " +
                "<ul></li></ul></ul></li></ul>");
    }

}
