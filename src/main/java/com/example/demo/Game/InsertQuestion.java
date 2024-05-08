package com.example.demo.Game;

import com.example.demo.Alerts.Alerts;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class InsertQuestion implements Initializable {
    @FXML
    private TextField Question;
    @FXML
    private TextField Correct;
    @FXML
    private Button buttonBack;
    @FXML
    private AnchorPane container;
    //private ArrayList<Questions> questions ;

    Alerts alerts = new Alerts();

    @FXML
    public void Insert(ActionEvent event) {
        String question = Question.getText();
        String correct = Correct.getText();
        Questions cauhoi = new Questions(question, correct);
        //questions.add(cauhoi);
        write(cauhoi, "D:\\demo\\demo\\data\\question.txt");
        Question.setText("");
        Correct.setText("");
    }

    public void write(Questions questions, String linkPath) {
        try {
            FileWriter fileWriter = new FileWriter("D:\\demo\\demo\\data\\question.txt",true);
            BufferedWriter bf = new BufferedWriter(fileWriter);
            bf.write(questions.getQuestion() + " " + questions.getTitle());
            bf.newLine();
            bf.flush();
            bf.close();
            alerts.showAlertInformation("Thông báo", "Lưu thành công !");
        } catch (UnsupportedEncodingException e) {
            System.out.println("Lỗi: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Lỗi: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttonBack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showComponent("/com/example/demo/Menu.fxml");
            }
        });
    }

    @FXML
    private void showComponent(String path) {
        try {
            AnchorPane Component = FXMLLoader.load(getClass().getResource(path));
            setNode(Component);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setNode(Node node) {
        if (this.container == null) {
            this.container = new AnchorPane();
        }
        this.container.getChildren().setAll(node);

        // Cập nhật UI
        Scene scene = buttonBack.getScene();
        if (scene != null) {
            ((AnchorPane) scene.getRoot()).getChildren().setAll(this.container);
        }
    }


}
