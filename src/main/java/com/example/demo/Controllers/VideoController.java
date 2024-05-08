
package com.example.demo.Controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.util.Duration;

public class VideoController implements Initializable {
    @FXML
    private MediaView mediaView;

    private MediaPlayer mediaPlayer;

    @FXML
    private Slider slider;

    @FXML
    private Button run;
    @FXML
    private Button pause;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Tạo một đối tượng Media từ tệp video
        String videoFile = "D:\demo\demo\src\main\resources\media\Are you following your dreams_ ⏲️ 6 Minute English.mp4"; // Đường dẫn đến tệp video của bạn
        Media media = new Media(new File(videoFile).toURI().toString());

        // Tạo một đối tượng MediaPlayer từ đối tượng Media
        mediaPlayer = new MediaPlayer(media);

        // Gán MediaPlayer cho MediaView
        mediaView.setMediaPlayer(mediaPlayer);

        // Bắt đầu phát video
        mediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
            slider.setValue(newValue.toSeconds());
        });

        // Thiết lập giá trị tối đa của thanh trượt
        mediaPlayer.setOnReady(() -> {
            slider.setMax(mediaPlayer.getTotalDuration().toSeconds());
        });
        pause.setVisible(false);
        run.setVisible(true);
        run.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handlePlayButtonAction();
            }
        });
        pause.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handlePauseButtonAction();
            }
        });
    }

    @FXML
    public void handlePlayButtonAction() {
        mediaPlayer.play();
        run.setVisible(false);
        pause.setVisible(true);
    }

    @FXML
    private void handlePauseButtonAction() {
        mediaPlayer.pause();
        run.setVisible(true);
        pause.setVisible(false);
    }

    @FXML
    private void handleSliderValueChanged() {
        mediaPlayer.seek(Duration.seconds(slider.getValue()));
    }
}

