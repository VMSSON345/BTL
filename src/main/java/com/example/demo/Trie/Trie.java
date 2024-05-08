package com.example.demo.Trie;

import java.util.*;

public class Trie {
    protected final Map<Character, Trie> map;
    protected String content;

    boolean check =false;


    public Trie() {
        this(null);
    }


    public Trie(String content) {
        this.content = content;
        map = new HashMap<Character, Trie>();
    }

    protected void addWord(Character character) {
        String s;
        if (this.content == null) {
            s = Character.toString(character);
        } else {
            s = this.content + character;
        }
        map.put(character, new Trie(s));
    }

    public void insertWord(String newWord) {
        if (newWord == null && newWord.isEmpty()) {
            throw new IllegalArgumentException("Error : Invalid word");
        }
        Trie res = this;//trả về vitri insertWord được gọi
        for (char c : newWord.toCharArray()) {
            if (!res.map.containsKey(c)) {
                res.addWord(c);
            }
            res = res.map.get(c);
        }
        res.check = true;//đánh dấu là hoàn thành

    }

    public List<String> showAllPrefix() {
        List<String> res = new ArrayList<String>();
        if (this.check) {
            res.add(this.content);
        }
        for (Map.Entry<Character, Trie> e : map.entrySet()) {
            Trie val = e.getValue();
            Collection<String> prefix = val.showAllPrefix();
            res.addAll(prefix);
        }
        return res;
    }

    public List<String> prefixReturn(String prefix) {
        Trie res = this;
        for (char c : prefix.toCharArray()) {
            if (!res.map.containsKey(c)) {
                return null;
            }
            res = res.map.get(c);
        }
        return res.showAllPrefix();
    }
}
