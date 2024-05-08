package com.example.demo.DictionaryApp;

import com.example.demo.Alerts.Alerts;
import com.example.demo.Trie.Trie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;


public class DictionaryManagement {
    private Trie trie = new Trie();

    Alerts alerts = new Alerts();

    ObservableList<String> list = FXCollections.observableArrayList();


    private static final String DATA_FILE_PATH = "data/E_V.txt";
    //private static final String FXML_FILE_PATH = "./src/main/resources/com/example/dictionary/dictionary-view.fxml";
    private static final String SPLITTING_CHARACTERS = "<html>";

    public DictionaryManagement() {
    }

    public void insertFromFile(Dictionary dictionary) {
        try {
            FileReader fileReader = new FileReader(DATA_FILE_PATH);

            BufferedReader bf = new BufferedReader(fileReader);
            String line;

            while ((line = bf.readLine()) != null) {
                String[] parts = line.split(SPLITTING_CHARACTERS);
                String word = parts[0];
                String definition = SPLITTING_CHARACTERS + parts[1];
                Word word1 = new Word(word, definition);
                dictionary.add(word1);
                //list.add(word1.getWordTarget());
            }
            bf.close();
        } catch (IOException e) {
            System.out.println("Can not open file! '");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    //xuất dữ liệu từ điền ra tệp
    public void exportToFile(Dictionary dictionary, String linkPath) {
        try {
            FileWriter fileWriter = new FileWriter(linkPath);
            BufferedWriter bf = new BufferedWriter(fileWriter);
            for (Word word : dictionary) {
                bf.write(word.getWordTarget() + word.getWordExplain());
                bf.newLine();
            }
            bf.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //tìm kiếm Word bằng binaryResearch.
    public int searchWord(Dictionary dictionary, String wordSearch) {
        try {
            dictionary.sort(new SortWord());
            int l = 0;
            int r = dictionary.size() - 1;
            while (l <= r) {
                int mid = (l + r) / 2;
                int res = dictionary.get(mid).getWordTarget().compareTo(wordSearch);
                if (res == 0) {
                    return mid;
                } else if (res < 0) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    public int searchSaveList(ArrayList<Word> list, String wordTarget) {
        list.sort(new SortWord());
        int l = 0;
        int r = list.size() - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            int res = list.get(mid).getWordTarget().compareTo(wordTarget);
            if (res == 0) {
                return mid;
            } else if (res < 0) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return -1;
    }

    //set lại một cái newTrie mới sau khi tác động.
    public void newTrie(Dictionary dictionary) {
        try {
            for (Word word : dictionary) {
                trie.insertWord(word.getWordTarget());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());//in ra lỗi nếu set thất bại.
        }
    }

    //thiếu.
    public void deleteWord(Dictionary dictionary, int index, String linkPath) {
        try {
            dictionary.remove(index);
            trie = new Trie();
            newTrie(dictionary);
            exportToFile(dictionary, linkPath);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateToWord(Dictionary dictionary, int index, String newExplain, String linkPath) {
        try {
            dictionary.get(index).setWordExplain(newExplain);
            exportToFile(dictionary, linkPath);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * public ObservableList<String> findWord(Dictionary dictionary, String wordFind) {
     * ObservableList<String> resList = FXCollections.observableArrayList();
     * try {
     * List<String> results = trie.prefixReturn(wordFind);
     * if (results != null) {
     * int listWord = Math.min(results.size(), 35);
     * for (int i = 0; i < listWord; i++) {
     * resList.add(results.get(i));
     * }
     * }
     * } catch (Exception e) {
     * System.out.println(e.getMessage());
     * }
     * return resList;
     * }
     **/

    public void addWordtoDic(Dictionary dictionary, String newVocab, String newMeaning) throws IOException {
        try {
            FileReader fileReader = new FileReader(DATA_FILE_PATH);

            BufferedReader bf = new BufferedReader(fileReader);
            String line;

            while ((line = bf.readLine()) != null) {
                String[] parts = line.split(SPLITTING_CHARACTERS);
                String word = parts[0];
                String definition = SPLITTING_CHARACTERS + parts[1];
                Word word1 = new Word(word, definition);
                list.add(word1.getWordTarget());
            }
            bf.close();
        } catch (IOException e) {
            System.out.println("Can not open file! '");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        boolean isExisted = false;
        for (String w : list) {
            if (newVocab.equalsIgnoreCase(w)) {
                isExisted = true;
            }
        }
        if (!isExisted) {

            Word newWord = new Word(newVocab, newMeaning);
            newWord.setWordTarget(newVocab);
            newWord.setWordExplain(newMeaning);
            BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE_PATH, true));
            writer.write(newVocab + newMeaning);
            writer.newLine();
            writer.close();
            alerts.showAlertInformation("Thông Báo", "Thêm từ thành công");
        } else {
            alerts.showAlertWarning("Thông báo", "Từ đã được lưu từ trước");
        }

    }
}
