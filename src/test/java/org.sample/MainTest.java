package org.sample;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.sample.model.LanguageCard;
import org.sample.service.CardsLoader;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.*;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;


/**
 * Created by OZhelyaev on 25.03.2017.
 */
public class MainTest {
    private static final Logger log = LogManager.getLogger(MainTest.class);

    private List<LanguageCard> cards;
    // lambda
    private IntFunction<Integer> rnd = (value) -> ThreadLocalRandom.current().nextInt(value);

    private BiFunction<LanguageCard, LanguageCard, Boolean> compateTo = (first, second) -> {
        log.info("compare cards : " + first.getWord() + " and " + second.getWord());
        return first.getWord().equals(second.getWord());
    };

    @Before
    public void load() throws Exception {
        CardsLoader cardsLoader = CardsLoader.getInstance();
        cardsLoader.setFilelName("card.csv");
        cards = cardsLoader.load();
    }

    @Test
    public void cardsLoaderTest() {
        Assert.assertNotNull(cards);
        Assert.assertEquals(cards.get(0).getWord(), "Hello");
    }

    /**
     *  Stram API
     *  generate int random stream
     *  create lambda
     *  and print random cards
     */
    @Test
    public void printRandomCards() {
        int count = cards.size();
        IntStream randomNumbers = IntStream.generate(
                () -> rnd.apply(count)).limit(count*3);
        //  full test list
        Consumer<LanguageCard> cadsInfo = (key) -> log.info("randomCard : " + key.getWord() + " - " + key.getTranslate());
        randomNumbers.forEach((p) -> cadsInfo.accept(cards.get(p)));
    }

    /**
     * generate index using function interface IntFunction
     * do test while not equale i and k index
     * compare cards, using BiFunction interface
     */
    @Test
    public void compareRandomCards() {
        int count = cards.size();
        int i = rnd.apply(count);
        int k = rnd.apply(count);
        while (i != k) {
            i = rnd.apply(count);
            k = rnd.apply(count);
            LanguageCard lc1 = cards.get(i);
            LanguageCard lc2 = cards.get(k);

            if (i == k)
                Assert.assertTrue("compareRandomCards TRUE test", compateTo.apply(lc1, lc2));
            else
                Assert.assertFalse("compareRandomCards FALSE test", compateTo.apply(lc1, lc2));
        }
    }


}