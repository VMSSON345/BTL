package com.example.demo.Controllers;

import com.example.demo.Alerts.Alerts;
import com.example.demo.DictionaryApp.Dictionary;
import com.example.demo.DictionaryApp.DictionaryManagement;
import com.example.demo.DictionaryApp.VoiceRSS;
import com.example.demo.DictionaryApp.Word;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;


public class SaveController implements Initializable {
    private static final String SPLITTING_CHARACTERS = "<html>";

    Alerts alerts = new Alerts();

    Dictionary dictionary = new Dictionary();

    DictionaryManagement dictionaryManagement = new DictionaryManagement();
    ArrayList<Word> dic = new ArrayList<>();
    ObservableList<String> anotherlist = FXCollections.observableArrayList();

    @FXML
    TextArea textArea;
    private static final String AUDIO_PATH = "D:\\demo\\demo\\src\\main\\resources\\media\\audio.war";

    private void insertfromfile() {
        try {
            FileReader fd = new FileReader("data/BookmarkList.txt");
            BufferedReader rd = new BufferedReader(fd);
            String line;
            while ((line = rd.readLine()) != null) {
                String[] parts = line.split(SPLITTING_CHARACTERS);
                String word = parts[0];
                String definition = SPLITTING_CHARACTERS + parts[1];
                Word word2 = new Word(word, definition);
                dic.add(word2);
                anotherlist.add(word2.getWordTarget());
            }
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    private void loadWordList() {
        listView.setItems(anotherlist);
    }

    @FXML
    ListView<String> listView;

    @FXML
    WebView webView;


    int indexOfselectedWord;

    public void handleWrite(String word, String meaning, String path) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));
        writer.write(word + meaning);
        writer.newLine();
        writer.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;
        insertfromfile();
        loadWordList();
    }

    @FXML
    private void handleMouseClickWord() {
        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String selectedWord = listView.getSelectionModel().getSelectedItem();
                webView.setVisible(true);
                textArea.setText(selectedWord);
                indexOfselectedWord = dictionaryManagement.searchSaveList(dic, selectedWord);
                Word word = dic.get(indexOfselectedWord);
                webView.getEngine().loadContent(word.getWordExplain(), "text/html");
            }
        });
    }

    public void updateListView(Word newWord) {
        dic.add(newWord);
        anotherlist.add(newWord.getWordTarget());
        listView.setItems(anotherlist);
    }

    public void updateListViewAfterRemove(Word word) throws IOException {
        // Xóa từ khỏi danh sách từ điển
        dic.remove(word);
        // Cập nhật file lưu danh sách từ
        BufferedWriter bw = new BufferedWriter(new FileWriter("data/BookmarkList.txt"));
        for (Word word1 : dic) {
            bw.write(word1.getWordTarget() + word1.getWordExplain());
            bw.newLine();
        }
        bw.close();
        // Xóa từ khỏi danh sách lưu từ
        String wordTarget = word.getWordTarget();
        anotherlist.remove(wordTarget);
        // Cập nhật ListView để hiển thị danh sách từ mới
        listView.setItems(FXCollections.observableArrayList(anotherlist));
    }


    private static SaveController instance;

    // Khởi tạo thể hiện duy nhất của SaveController
    public static SaveController getInstance() {
        if (instance == null) {
            instance = new SaveController();
        }
        return instance;
    }

    @FXML
    public void handleClickSoundBtn() throws Exception {
        if (textArea.getText().isEmpty()) {
            alerts.showAlertWarning("Thông báo", "Vui lòng chọn từ");
            return;
        } else {
            VoiceRSS.Name = VoiceRSS.voiceNameUK;
            speak("en-gb");
        }
    }

    public void speak(String language) throws Exception {
        VoiceRSS.language = language;
        VoiceRSS.speakWord(textArea.getText());
        VoiceRSS.playAudio(AUDIO_PATH);
    }

    @FXML
    public void handleClickSoundBtn1() throws Exception {
        if (textArea.getText().isEmpty()) {
            alerts.showAlertWarning("Thông báo", "Vui lòng chọn từ");
            return;
        } else {
            VoiceRSS.Name = VoiceRSS.voiceNameUS;
            speak("en-gb");
        }
    }

    @FXML
    public void handleCLickRemoveBtn() {
        if (textArea.getText().isEmpty()) {
            alerts.showAlertWarning("Thông báo", "Vui lòng chọn từ");
        } else {
            Alert alertWarning = alerts.alertWarning("Thông báo", "Bạn có muốn xóa từ này");
            alertWarning.getButtonTypes().add(ButtonType.CANCEL);
            Optional<ButtonType> option = alertWarning.showAndWait();
            if (option.get() == ButtonType.OK) {
                Word word = dic.get(indexOfselectedWord);
                dic.remove(word);
                try {
                    FileWriter fileWriter = new FileWriter("data/BookmarkList.txt");
                    BufferedWriter bf = new BufferedWriter(fileWriter);
                    for (Word word1 : dic) {
                        bf.write(word1.getWordTarget() + word1.getWordExplain());
                        bf.newLine();
                    }
                    bf.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                anotherlist.remove(word.getWordTarget());
                listView.setItems(anotherlist);
                webView.getEngine().loadContent("");
                textArea.setText("");
                alerts.showAlertInformation("Thông báo", "Xóa từ thành công");
            }
        }
    }

}

