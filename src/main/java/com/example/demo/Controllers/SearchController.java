package com.example.demo.Controllers;

import com.example.demo.Alerts.Alerts;
import com.example.demo.DictionaryApp.Dictionary;
import com.example.demo.DictionaryApp.DictionaryManagement;
import com.example.demo.DictionaryApp.VoiceRSS;
import com.example.demo.DictionaryApp.Word;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;
import javafx.scene.control.ButtonType;


import java.io.*;
import java.net.URL;
import java.util.*;

public class SearchController implements Initializable {

    private Dictionary dictionary = new Dictionary();

    private DictionaryManagement dictionaryManagement = new DictionaryManagement();

    private Alerts alerts = new Alerts();


    @FXML
    TextField textField;


    @FXML
    ListView<String> listView;


    @FXML
    WebView definitionView;

    @FXML
    Button volumeButton;

    @FXML
    Button volumeButton1;

    @FXML
    Button luuButton;

    @FXML
    Button editButton;

    @FXML
    Button updateButton;

    @FXML
    Button deleteButton;

    @FXML
    protected HTMLEditor editDefinition;

    @FXML
    private Button cancelButton;

    int getIndexOfselectedWord;

    // dem so tu xuat hien trong savecontroller
    int countNumberOfWord = 0;

    private static final String SPLITTING_CHARACTERS = "<html>";

    private final String path = "data/E_V.txt";

    private final String savePath = "data/BookmarkList.txt";

    private static final String AUDIO_PATH = "D:\\demo\\demo\\src\\main\\resources\\media\\audio.war";

    ObservableList<String> list = FXCollections.observableArrayList();
    ObservableList<String> searchResult = FXCollections.observableArrayList();

    private static SearchController instance;

    // Khởi tạo thể hiện duy nhất của SaveController
    public static SearchController getInstance() {
        if (instance == null) {
            instance = new SearchController();
        }
        return instance;
    }
    //hàm kiểm tra xem đã lưu từ này ch

    public void loadWordlist() {
        this.listView.getItems().addAll(list);

    }

    public void showlist() {
        for (Word word : dictionary) {
            list.add(word.getWordTarget());
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dictionaryManagement.insertFromFile(dictionary);
        dictionaryManagement.newTrie(dictionary);
        showlist();
        loadWordlist();
        updateButton.setVisible(false);
        instance = this;

        textField.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (textField.getText().isEmpty()) {
                    cancelButton.setVisible(true);
                    editDefinition.setVisible(false);
                    luuButton.setVisible(true);
                    editButton.setVisible(true);
                    volumeButton.setVisible(true);
                    volumeButton1.setVisible(true);
                    deleteButton.setVisible(true);
                } else {
                    cancelButton.setVisible(true);
                    handleOnTyped();
                }
            }
        });
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textField.clear();
                cancelButton.setVisible(true);
                definitionView.getEngine().loadContent("");
                editDefinition.setVisible(false);
            }
        });
        luuButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    luuButton.setVisible(true);
                    editButton.setVisible(true);
                    volumeButton.setVisible(true);
                    volumeButton1.setVisible(true);
                    deleteButton.setVisible(true);
                    handleMouseClickSave();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    luuButton.setVisible(true);
                    editButton.setVisible(true);
                    volumeButton.setVisible(true);
                    volumeButton1.setVisible(true);
                    deleteButton.setVisible(true);
                    handleClickDeleteBtn();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    ;
                }
            }
        });
        volumeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    luuButton.setVisible(true);
                    editButton.setVisible(true);
                    volumeButton.setVisible(true);
                    volumeButton1.setVisible(true);
                    deleteButton.setVisible(true);
                    handleClickSoundBtn();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });
        volumeButton1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    luuButton.setVisible(true);
                    editButton.setVisible(true);
                    volumeButton.setVisible(true);
                    volumeButton1.setVisible(true);
                    deleteButton.setVisible(true);
                    handleClickSound1Btn();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });
        editButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    handleClickEditButton();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });
        updateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    luuButton.setVisible(true);
                    editButton.setVisible(true);
                    volumeButton.setVisible(true);
                    volumeButton1.setVisible(true);
                    deleteButton.setVisible(true);
                    handleClickUpdateBtn();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });

    }

    @FXML
    private void handleClickSound1Btn() {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        Voice voice = VoiceManager.getInstance().getVoice("kevin16");
        if (textField.getText().isEmpty()) {
            alerts.showAlertWarning("Thông báo", "Vui lòng chọn từ");
        } else {
            if (voice != null) {
                voice.allocate();
                voice.speak(textField.getText());
            } else {
                throw new IllegalStateException("Cannot find voice: kevin16");
            }
        }
    }

    private void handleClickUpdateBtn() {
        alerts.showAlertInformation("", "Sửa từ thành công");
        editButton.setVisible(true);
        updateButton.setVisible(false);
        luuButton.setVisible(true);
        deleteButton.setVisible(true);
        String newMeaning = editDefinition.getHtmlText().replace(" dir=\"ltr\"", "");
        dictionaryManagement.updateToWord(dictionary, getIndexOfselectedWord, newMeaning, path);
        editDefinition.setVisible(false);
        definitionView.setVisible(true);
        definitionView.getEngine().loadContent(newMeaning, "text/html");

    }

    @FXML
    private void handleClickEditButton() {
        if (textField.getText().isEmpty()) {
            updateButton.setVisible(false);
            alerts.showAlertWarning("Thông báo", "Vui lòng chọn từ");
            return;
        }
        editButton.setVisible(true);
        editDefinition.setVisible(true);
        definitionView.setVisible(false);
        luuButton.setVisible(true);
        deleteButton.setVisible(true);
        updateButton.setVisible(true);
        Word def = dictionary.get(getIndexOfselectedWord);
        String explain = def.getWordExplain();
        editDefinition.setHtmlText(explain);
    }

    @FXML
    private void handleMouseClickSave() throws IOException {
        if (textField.getText().isEmpty()) {
            alerts.showAlertWarning("Thông báo", "Vui lòng chọn từ");
            return;
        } else {
            SaveController saveController = SaveController.getInstance();
            editDefinition.setVisible(false);
            Word def = dictionary.get(getIndexOfselectedWord);
            BufferedReader rd = new BufferedReader(new FileReader(savePath));
            String line;
            while ((line = rd.readLine()) != null) {
                String[] parts = line.split(SPLITTING_CHARACTERS);
                String word = parts[0];
                if (word.equals(def.getWordTarget())) {
                    countNumberOfWord++;
                }
            }
            if (countNumberOfWord == 0) {
                alerts.showAlertInformation("Thông báo", "Đã lưu");
                saveController.handleWrite(def.getWordTarget(), def.getWordExplain(), savePath);
                SaveController.getInstance().updateListView(def);
            } else {
                alerts.showAlertWarning("Thông báo", "Từ này đã được lưu từ trước");
                countNumberOfWord = 0;
            }
        }


    }

    @FXML
    public void handleOnTyped() {
        String stringKey = textField.getText().trim().toLowerCase();
        if (textField.getText().isEmpty()) {
            listView.setItems(list);
        } else {
            editDefinition.setVisible(false);
            searchResult.clear();
            for (String word : list) {
                if (word.toLowerCase().startsWith(stringKey)) {
                    searchResult.add(word);
                }
            }
            if (searchResult.isEmpty()) {
                alerts.showAlertInformation("Thông báo", "Không thể tìm thấy từ");
            } else {
                listView.setItems(searchResult);
            }
        }
    }

    @FXML
    private void handleMouseClickAWord() {
//        String selectedWord = listView.getSelectionModel().getSelectedItem();
        /*this.listView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    indexOfselectedWord = dictionaryManagement.searchWord(dictionary, selectedWord);
                    Word word = dictionary.get(indexOfselectedWord);
                    definitionView.getEngine().loadContent(word.getWordExplain(), "text/html");
                }
        );*/
//        suggestListWords.setItems(list);
        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String selectedWord = listView.getSelectionModel().getSelectedItem();
                textField.setText(selectedWord);
                cancelButton.setVisible(true);
                definitionView.setVisible(true);
                luuButton.setVisible(true);
                volumeButton.setVisible(true);
                deleteButton.setVisible(true);
                editDefinition.setVisible(false);
                try {
                    getIndexOfselectedWord = dictionaryManagement.searchWord(dictionary, selectedWord);
                    Word word = dictionary.get(getIndexOfselectedWord);
                    definitionView.getEngine().loadContent(word.getWordExplain(), "text/html");
                } catch (Exception e) {
                    alerts.showAlertWarning("Thông báo", "Vui lòng chọn đúng từ!");
                }
            }
        });
    }


    @FXML
    private void handleClickDeleteBtn() throws IOException {
        if (textField.getText().isEmpty()) {
            alerts.showAlertWarning("Thông báo", "Vui lòng chọn từ");
        } else {
            Alert alertWarning = alerts.alertWarning("Thông báo", "Bạn có chắc muốn xóa từ này");
            editDefinition.setVisible(false);
            alertWarning.getButtonTypes().add(ButtonType.CANCEL);
            Optional<ButtonType> option = alertWarning.showAndWait();
            if (option.get() == ButtonType.OK) {
                dictionaryManagement.deleteWord(dictionary, getIndexOfselectedWord, path);
                String deletedWord = listView.getSelectionModel().getSelectedItem();
                if (searchResult != null && searchResult.contains(deletedWord)) {
                    searchResult.remove(deletedWord);
                    list.remove(deletedWord);
                    listView.setItems(searchResult);
                } else {
                    list.remove(deletedWord);
                    listView.setItems(list);
                }
                SaveController.getInstance().updateListViewAfterRemove(dictionary.get(getIndexOfselectedWord));
                // successfully
                alerts.showAlertInformation("Thông báo", "Xoá từ thành công");
                textField.clear();
                definitionView.getEngine().loadContent("");
            } else {
                alerts.showAlertWarning("Thông báo", "Hoàn thành");
            }
        }
    }

    @FXML
    public void handleClickSoundBtn() throws Exception {
        if (textField.getText().isEmpty()) {
            alerts.showAlertWarning("Thông báo", "Vui lòng chọn từ");
            return;
        } else {
            editDefinition.setVisible(false);
            VoiceRSS.Name = VoiceRSS.voiceNameUK;
            speak("en-gb");
        }
    }

    public void speak(String language) throws Exception {
        VoiceRSS.language = language;
        VoiceRSS.speakWord(textField.getText());
        VoiceRSS.playAudio(AUDIO_PATH);
    }

    public void updateDictionary() {
        dictionary.clear();
        dictionaryManagement.insertFromFile(dictionary);
        list.clear();
        showlist();
        listView.setItems(list);
    }
}
