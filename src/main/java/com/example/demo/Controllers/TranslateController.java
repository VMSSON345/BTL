package com.example.demo.Controllers;

import com.example.demo.Alerts.Alerts;
import com.example.demo.DictionaryApp.TranslateAPI;
import com.example.demo.DictionaryApp.VoiceRSS;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class TranslateController implements Initializable {
    Alerts alerts = new Alerts();
    private static final String AUDIO_PATH = "D:\\demo\\demo\\src\\main\\resources\\media\\audio.war";
    String translateFrom = " ";
    String translateTo = " ";


    String nameFrom;
    String speakFrom;

    String nameTo;
    String speakTo;

    @FXML
    private TextArea area1;
    @FXML
    private TextArea area2;
    @FXML
    private Button langFromFirst;
    @FXML
    private Button langToFirst;
    @FXML
    private Button langFromSecond;

    @FXML
    private Button langToSecond;
    @FXML
    private Button langToThird;
    @FXML
    private Button langToFourth;
    @FXML
    private Button langToFifth;

    @FXML
    private Button speaker2;

    public void resetStyleLangFrom() {
        langFromFirst.getStyleClass().removeAll("active");
        langFromSecond.getStyleClass().removeAll("active");
    }

    public void resetStyleLangTo() {
        langToFirst.getStyleClass().removeAll("active");
        langToSecond.getStyleClass().removeAll("active");
        langToThird.getStyleClass().removeAll("active");
        langToFourth.getStyleClass().removeAll("active");
        langToFifth.getStyleClass().removeAll("active");
    }

    public void detect() {
        resetStyleLangFrom();
        langFromFirst.getStyleClass().add("active");
        translateFrom = "";
        nameFrom = "Linda";
        speakFrom = "en-gb";
    }

    @FXML
    void eng1() {
        resetStyleLangFrom();
        langFromSecond.getStyleClass().add("active");
        translateFrom = "en";
        nameFrom = "Linda";
        speakFrom = "en-gb";
    }

    @FXML
    void vie1() {
        resetStyleLangFrom();
        langFromFirst.getStyleClass().add("active");
        translateFrom = "vi";
        nameFrom = "Chi";
        speakFrom = "vi-vn";
    }

    @FXML
    void vie2() throws IOException {
        resetStyleLangTo();
        langToSecond.getStyleClass().add("active");
        translateTo = "vi";
        nameTo = "Chi";
        speakTo = "vi-vn";
        if (!Objects.equals(area1.getText(), "")) {
            area2.setText(TranslateAPI.googleTranslate(translateFrom, translateTo, area1.getText()));
        }
    }

    @FXML
    void eng2() throws IOException {
        resetStyleLangTo();
        langToFirst.getStyleClass().add("active");
        translateTo = "en";
        nameTo = "Linda";
        speakTo = "en-gb";
        if (!Objects.equals(area1.getText(), "")) {
            area2.setText(TranslateAPI.googleTranslate(translateFrom, translateTo, area1.getText()));
        }
    }

    @FXML
    void korea2() throws IOException {
        resetStyleLangTo();
        langToThird.getStyleClass().add("active");
        translateTo = "ko";
        nameTo = "Nari";
        speakTo = "ko-kr";
        if (!Objects.equals(area1.getText(), "")) {
            area2.setText(TranslateAPI.googleTranslate(translateFrom, translateTo, area1.getText()));
        }
    }

    @FXML
    void rus2() throws IOException {
        resetStyleLangTo();
        langToFourth.getStyleClass().add("active");
        translateTo = "ru";
        nameTo = "Marina";
        speakTo = "ru-ru";
        if (!Objects.equals(area1.getText(), "")) {
            area2.setText(TranslateAPI.googleTranslate(translateFrom, translateTo, area1.getText()));
        }
    }

    @FXML
    void chinese2() throws IOException {
        resetStyleLangTo();
        langToFifth.getStyleClass().add("active");
        translateTo = "zh";
        nameTo = "Luli";
        speakTo = "zh-cn";
        if (!Objects.equals(area1.getText(), "")) {
            area2.setText(TranslateAPI.googleTranslate(translateFrom, translateTo, area1.getText()));
        }
    }

    @FXML
    void translate() throws IOException {
        if (translateTo.equals(" ") || translateFrom.equals(" ")) {
            alerts.showAlertWarning("Thông tin", "Vui lòng chọn ngôn ngữ bạn muốn dịch");
        } else {
            if (!Objects.equals(area1.getText(), "")) {
                area2.setText(TranslateAPI.googleTranslate(translateFrom, translateTo, area1.getText()));
            }
        }
    }

    @FXML
    void speak1() throws Exception {
        VoiceRSS.Name = nameFrom;
        VoiceRSS.language = speakFrom;
        if (!Objects.equals(area1.getText(), "")) {
            VoiceRSS.speakWord(area1.getText());
            VoiceRSS.playAudio(AUDIO_PATH);
        }
    }


    @FXML
    void speak2() throws Exception {
        VoiceRSS.Name = nameTo;
        VoiceRSS.language = speakTo;
        if (!Objects.equals(area2.getText(), "")) {
            VoiceRSS.speakWord(area2.getText());
            VoiceRSS.playAudio(AUDIO_PATH);
        }
    }

    @FXML
    void clear1() {
        area1.clear();
    }

    @FXML
    void clear2() {
        area2.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        langFromFirst.getStyleClass().add("active");
        langToFirst.getStyleClass().add("active");

        area1.setText("");
        nameFrom = "Chi";
        translateFrom = "vi";
        speakFrom = "vi-vn";
        nameTo = "Linda";

        translateTo = "en";
        speakTo = "en-gb";
    }
}
