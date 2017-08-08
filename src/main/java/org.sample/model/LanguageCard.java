package org.sample.model;

import java.util.List;

/**
 * English card for memory
 * Created by OZhelyaev on 26.03.2017.
 */
public class LanguageCard {
    private String word;
    private String transcrption;
    private List<String> translate;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTranscrption() {
        return transcrption;
    }

    public void setTranscrption(String transcrption) {
        this.transcrption = transcrption;
    }

    public List<String> getTranslate() {
        return translate;
    }

    public void setTranslate(List<String> translate) {
        this.translate = translate;
    }

}
