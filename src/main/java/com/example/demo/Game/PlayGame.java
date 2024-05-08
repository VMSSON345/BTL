package com.example.demo.Game;

import com.example.demo.Alerts.Alerts;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;

public class PlayGame implements Initializable  {
    @FXML
    private TextField Answer;
    @FXML
    private Label Cauhoi;
    @FXML
    private Button buttonBack;
    @FXML
    private AnchorPane container;

    Alerts alert = new Alerts();

    private String traloi;
    private String answer;
    private Questions questions;
    private ArrayList<Questions> res = new ArrayList<>();;
    private boolean firstTime = true,ss;
    private int check,cau,diem;

    public void newQuestion(){
        Random rand = new Random();
        int randomIndex = rand.nextInt(res.size());
        questions = res.get(randomIndex);
        Cauhoi.setText(questions.getQuestion());

        traloi = questions.getTitle();

        answer = Answer.getText();
    }
    public void read(String linkPath) {
        try {
            FileReader fileReader = new FileReader(linkPath);

            BufferedReader bf = new BufferedReader(fileReader);
            String line;
            while ((line = bf.readLine()) != null) {
                String[] cauhoi = line.split(" ");
                if (cauhoi.length >= 2) {
                    Questions questions = new Questions(cauhoi[0], cauhoi[1]);
                    res.add(questions);
                }
            }

            bf.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Cauhoi.setText("WELCOME");
        Answer.setVisible(false);
        Answer.setText("");

        buttonBack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showComponent("/com/example/demo/Menu.fxml");
            }
        });
    }
    @FXML
    public void Submit(ActionEvent event){
        if (firstTime) {
            Answer.setVisible(true);
            firstTime = false; // Đặt lại biến firstTime
            read("D:\\demo\\demo\\data\\question.txt");

            if (res.isEmpty()) {
                alert.showAlertWarning("Thông báo","Không có câu hỏi này");
                return;
            }
            cau++;
            newQuestion();
            return;
        }
        answer = Answer.getText();
        if(cau < 10){
            if(traloi != null && (traloi.equals(answer) || answer.equals(traloi.toLowerCase()))){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("");
                alert.setHeaderText("Chính xác!");
                alert.setContentText("Chúc mừng bạn");
                alert.show();
                ss = true;
                cau++;
                diem++;
                newQuestion();
            }
            else{
                check++;
                if(check < 5) {
                    // Debugging
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("");
                    alert.setHeaderText("Không chính xác");
                    alert.show();
                }
                else if(check == 5){
                    Alert alerts = new Alert(Alert.AlertType.CONFIRMATION);
                    alerts.setTitle("");
                    alerts.setHeaderText("BAN HAY HOC LAI TU NAY");
                    alerts.setContentText("cau tra loi dung la: " + traloi );
                    alerts.show();
                    check = 0;
                    cau++;
                    newQuestion();
                }
            }
        }
        else{
            if(ss){
                diem++;
                showAlertConfirmation(diem,cau);
            }
            else{
                showAlertConfirmation(diem,cau);
            }
            diem = 0;
            cau = 0;
        }
        Answer.setText("");


    }
    public void showAlertConfirmation(int diem, int cau) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận");
        alert.setHeaderText("Điểm của bạn là :" + diem + '/' + cau);
        alert.setContentText("Bạn có muốn tiếp tục không");

        ButtonType buttonTypeYes = new ButtonType("Có");
        ButtonType buttonTypeNo = new ButtonType("Không");

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.setAlwaysOnTop(true);

        alert.showAndWait().ifPresent(response -> {
            if (response == buttonTypeYes) {
                newQuestion();
            } else if (response == buttonTypeNo) {
                showComponent("/com/example/dmeo/Menu.fxml");
            }
        });
    }
    @FXML
    private void showComponent( String path ) {
        try {
            AnchorPane Component = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(path)));
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

        Scene scene = buttonBack.getScene();
        if (scene != null) {
            ((AnchorPane) scene.getRoot()).getChildren().setAll(this.container);
        }
    }
}