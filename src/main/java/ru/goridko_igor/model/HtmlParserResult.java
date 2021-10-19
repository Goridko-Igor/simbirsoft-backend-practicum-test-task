package ru.goridko_igor.model;

import java.util.ArrayList;
import java.util.List;

public class HtmlParserResult {
    private int id;
    private String urlAddress;
    private List<WordCount> wordsCount;

    public HtmlParserResult() {
        this.wordsCount = new ArrayList<>();
    }

    public HtmlParserResult(int id, String urlAddress) {
        this.id = id;
        this.urlAddress = urlAddress;
        this.wordsCount = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrlAddress() {
        return urlAddress;
    }

    public void setUrlAddress(String urlAddress) {
        this.urlAddress = urlAddress;
    }

    public List<WordCount> getWordsCount() {
        return wordsCount;
    }
}
