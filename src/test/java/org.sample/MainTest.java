package org.sample;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.sample.model.LanguageCard;
import org.sample.service.CardsLoader;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;


/**
 * Created by OZhelyaev on 25.03.2017.
 */
public class MainTest {
    private static final Logger log = LogManager.getLogger(MainTest.class);
    @Test
    public void cardsLoaderTest() throws Exception {
        CardsLoader cardsLoader = CardsLoader.getInstance();
        cardsLoader.setFilelName("card.csv");
        List<LanguageCard> cards = cardsLoader.load();

        Assert.assertNotNull(cards);
        Assert.assertEquals(cards.get(0).getWord(), "Hello");

        LongStream randomNumbers = LongStream.generate(
                () -> ThreadLocalRandom.current().nextLong(cards.stream().count())
            ).limit(10);
        log.info("randomNumbers:" + Arrays.toString(randomNumbers.toArray()));
        LanguageCard randomCard =  cards.stream().findAny().get();
        log.info("randomCard:" + randomCard.getWord() + " - " + randomCard.getTranslate());
        LanguageCard answerCard = cards.stream().filter(
                card -> card.getTranslate().equals(randomCard.getTranslate())
            ).findFirst().get();
        log.info("answerCard:" + answerCard.getWord() + " - " + answerCard.getTranslate());
    }

}