package org.sample.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sample.model.LanguageCard;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Class for loading Language cards
 * Created by OZhelyaev on 26.03.2017.
 */
public class CardsLoader {
    private static final Logger log = LogManager.getLogger(CardsLoader.class);

    private static final int FIRS_WORD = 0;
    //private static final int TRANSCRPTION = 1;
    private static final int TRANS_WORDS = 1;

    private static CardsLoader cardsLoader = null;
    private String filelName;

    private CardsLoader() {
    }

    public synchronized static CardsLoader getInstance() {
        if (cardsLoader == null)
            cardsLoader = new CardsLoader();
        return cardsLoader;
    }

    public List<LanguageCard> load() {
        if (filelName.isEmpty())
            throw new IllegalArgumentException("File Name not set");

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream input = classloader.getResourceAsStream(filelName);
        Reader reader = new InputStreamReader(input);
        List<LanguageCard> languageCards = new ArrayList<>();
        String val;
        log.info("Reading");
        try {
            //Stream<String> streamFromFiles = Files.lines(Paths.get("file.txt"))
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(reader);
            for (CSVRecord record: records) {
                LanguageCard lCard = new LanguageCard();
                val = record.get(FIRS_WORD);
                lCard.setWord(val);
                log.info("FIRS_WORD: " + val);

//                val = record.get(TRANSCRPTION);
//                lCard.setTranscrption(val);
//                log.info("TRANSCRPTION: " + val);

                val = record.get(TRANS_WORDS);
                List<String> tWords = new ArrayList<>();
                tWords.add(val);
                lCard.setTranslate(tWords);
                log.info("TRANS_WORDS: " + val);
                languageCards.add(lCard);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return languageCards;
    }

    public void setFilelName(String filelName) {
        this.filelName = filelName;
    }
}
