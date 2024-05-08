package com.example.demo.DictionaryApp;

public class Word {
    private String wordTarget;
    private String wordExplain;

    public Word(String english, String vietnamese) {
        this.wordTarget = english;
        this.wordExplain = vietnamese;
    }

    public Word(String newText) {
        wordTarget = newText;
    }

    public String getWordTarget() {
        return this.wordTarget;
    }

    public void setWordTarget(String wordTarget) {
        this.wordTarget = wordTarget;
    }

    public String getWordExplain() {
        return this.wordExplain;
    }

    public void setWordExplain(String wordExplain) {
        this.wordExplain = wordExplain;
    }

    public Word() {
        this.wordExplain = "";
        this.wordTarget = "";
    }

    @Override
    public String toString() {
        return "Word["
                + "wordTarget='" + wordTarget + '\''
                + ", wordExplain='" + wordExplain + '\''
                + ']';
    }
}
