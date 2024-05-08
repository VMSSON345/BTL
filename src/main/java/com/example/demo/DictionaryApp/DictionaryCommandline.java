package com.example.demo.DictionaryApp;

import com.example.demo.Trie.Trie;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryCommandline extends Trie {
    private static final ArrayList<Word> vocabulary = new ArrayList<>();
    private static Trie trie = new Trie();
    private static DictionaryManagement dictionaryManagement = new DictionaryManagement();

    public DictionaryManagement dictionaryBasic() {
        return new DictionaryManagement();
    }

    // Phương thức thêm một từ mới vào từ điển
    public static void addWord(Dictionary dictionary, String newVocab, String newMeaning) throws IOException {
        Word newWord = new Word(newVocab, newMeaning);
        newWord.setWordTarget(newVocab);
        newWord.setWordExplain(newMeaning);
        dictionary.add(newWord);
        BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE_PATH, true));
        writer.write(newVocab);
        writer.newLine();
        writer.close();
    }

    private static final String DATA_FILE_PATH = "data/data_commandline.txt";
    private static final String SPLITTING_CHARACTERS = "<html>";

    public static void insertFromFile(Dictionary dictionary) {
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
            }
            bf.close();
        } catch (IOException e) {
            System.out.println("Can not open file!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Xuất dữ liệu từ điển ra tệp.
    public static void exportToFile(Dictionary dictionary, String linkPath) {
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

    // Phương thức tìm kiếm các từ chứa một phần của từ khóa.
    // Search for a word in the dictionary and display its English and Vietnamese meanings
    private static void searchWord(Dictionary dictionary, String wordSearch) {
        int index = dictionaryManagement.searchWord(dictionary, wordSearch);
        if (index != -1) {
            Word foundWord = dictionary.get(index);
            System.out.println("English: " + foundWord.getWordTarget());
            System.out.println("Vietnamese: " + foundWord.getWordExplain());
        } else {
            System.out.println("Word not found in the dictionary.");
        }
    }

    public static void newTrie(Dictionary dictionary) {
        try {
            for (Word word : dictionary) {
                trie.insertWord(word.getWordTarget());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());//in ra lỗi nếu set thất bại.
        }
    }

    public static void removeWord(Dictionary dictionary, int index, String linkPath) {
        try {
            dictionary.remove(index);
            trie = new Trie();
            newTrie(dictionary);
            exportToFile(dictionary, linkPath);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Phương thức cập nhật định nghĩa của một từ trong từ điển.
    public static void updateToWord(Dictionary dictionary, int index, String newExplain, String linkPath) {
        try {
            dictionary.get(index).setWordExplain(newExplain);
            exportToFile(dictionary, linkPath);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Phương thức hiển thị toàn bộ từ điển.
    // Display all words in the dictionary
    public static void showAllWords(Dictionary dictionary) {
        if (dictionary.isEmpty()) {
            System.out.println("Dictionary is empty.");
        } else {
            System.out.println("No\t| English\t| Vietnamese");
            for (int i = 0; i < dictionary.size(); i++) {
                Word word = dictionary.get(i);
                System.out.println((i + 1) + "\t| " + word.getWordTarget() + "\t| " + word.getWordExplain());
            }
        }
    }


    // Phương thức lookup để tìm kiếm một từ trong từ điển.
    public static void lookupWord(Dictionary dictionary, String wordToLookup) {
        boolean found = false;
        for (Word word : dictionary) {
            if (word.getWordTarget().startsWith(wordToLookup)) {
                System.out.println("English: " + word.getWordTarget());
                System.out.println("Vietnamese: " + word.getWordExplain());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No words found starting with wordToLookUp" + wordToLookup + "'.");
        }
    }

    public static void game() {
        String[] questions = {
                "An apple a day keeps the doctor away. Trong câu này, 'apple' có nghĩa là gì?",
                "Câu hỏi 2: What does accurate mean?",
                "Câu hỏi 3: What does Hello mean?",
                "Câu hỏi 4: What does 'apple' represent in the context: 'He was offered the apple of discord'?"
        };

        String[][] answers = {
                {"a) Dưa hấu", "b) Lê", "c) Quả táo", "d) Chuối"},
                {"a) Lỗi", "b) Quả dâu", "c) Giao hàng", "d) Chính xác"},
                {"a) Tạm biệt", "b) Goodbye", "c) Xin chào", "d) Hi"},
                {"a) Fruit", "b) Tomato", "c) Apple", "d) Wheat"}
        };

        char[] correctAnswers = {'c', 'd', 'c', 'c'};

        int score = 0;
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < questions.length; i++) {
            System.out.println(questions[i]);
            for (String answer : answers[i]) {
                System.out.println(answer);
            }
            System.out.print("Chọn đáp án của bạn: ");
            char userAnswer = scanner.next().toLowerCase().charAt(0);

            if (userAnswer == correctAnswers[i]) {
                System.out.println("Chính xác!\n");
                score++;
            } else {
                System.out.println("Sai rồi. Đáp án đúng là '" + correctAnswers[i] + "'.\n");
            }
        }
        if (score == 0) {
            System.out.println("Điểm số : 0/4\n Bạn cần luyện tập thêm! ");
        } else {
            System.out.println("Bạn đã hoàn thành trò chơi với điểm số là: " + score + "/" + questions.length);
        }

    }

    public static void dictionaryAdvanced() throws IOException {
        Dictionary dictionary = new Dictionary();
        insertFromFile(dictionary);
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        while (choice != 0) {
            System.out.println("Welcome to My Application!");
            System.out.println("[0] Exit");
            System.out.println("[1] Add");
            System.out.println("[2] Remove");
            System.out.println("[3] Update");
            System.out.println("[4] Display");
            System.out.println("[5] Lookup");
            System.out.println("[6] Search");
            System.out.println("[7] Game");
            System.out.println("[8] Import from file");
            System.out.println("[9] Export to file");
            System.out.print("Your action: ");

            // Đọc lựa chọn từ người dùng.
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 0:
                    System.out.println("Exiting...");
                    break;
                case 1:
                    // Add.
                    System.out.println("Enter the word to add: ");
                    String newWord = scanner.nextLine();
                    System.out.println("Enter the definition: ");
                    String newDefinition = scanner.nextLine();
                    addWord(dictionary, newWord, newDefinition);
                    break;
                case 2:
                    // Remove.
                    System.out.println("Enter the word to remove: ");
                    String wordToRemove = scanner.nextLine();
                    int index = dictionaryManagement.searchWord(dictionary, wordToRemove);
                    removeWord(dictionary, index, DATA_FILE_PATH);
                    break;
                case 3:
                    // Update.
                    System.out.println("Enter the word to update: ");
                    String wordToUpdate = scanner.nextLine();
                    System.out.println("Enter the new definition: ");
                    String newDefinitionForUpdate = scanner.nextLine();
                    int indexToUpdate = dictionaryManagement.searchWord(dictionary, wordToUpdate); // <-- Renamed to 'indexToUpdate'
                    updateToWord(dictionary, indexToUpdate, newDefinitionForUpdate, DATA_FILE_PATH);
                    break;

                case 4:
                    // Display.
                    showAllWords(dictionary);
                    break;
                case 5:
                    // Lookup.
                    System.out.println("Enter the word to lookup: ");
                    String wordToLookup = scanner.nextLine();
                    lookupWord(dictionary, wordToLookup);
                    break;
                case 6:
                    // Search.
                    System.out.println("Enter the keyword to search: ");
                    String keyword = scanner.nextLine();
                    searchWord(dictionary, keyword);
                    break;

                case 7:
                    // Game.
                    game();
                    break;
                case 8:
                    insertFromFile(dictionary);
                    break;
                case 9:
                    exportToFile(dictionary, DATA_FILE_PATH);
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }

        scanner.close();
    }

    public static void main(String[] args) throws IOException {
        dictionaryAdvanced();
    }

}