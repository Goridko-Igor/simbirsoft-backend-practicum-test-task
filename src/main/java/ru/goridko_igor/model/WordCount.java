package ru.goridko_igor.model;

public class WordCount {
    private final String word;
    private final Long count;

    public WordCount(String word, Long count) {
        this.word = word;
        this.count = count;
    }

    public String getWord() {
        return word;
    }

    public Long getCount() {
        return count;
    }
}
