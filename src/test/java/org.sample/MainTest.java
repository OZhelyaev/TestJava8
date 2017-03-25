package org.sample;

import org.junit.Assert;
import org.junit.Test;
import org.sample.model.LanguageCard;
import org.sample.service.CardsLoader;

import java.util.List;


/**
 * Created by OZhelyaev on 25.03.2017.
 */
public class MainTest {
    @Test
    public void cardsLoaderTest() throws Exception {
        CardsLoader cardsLoader = CardsLoader.getInstance();
        cardsLoader.setFilelName("card.csv");
        List<LanguageCard> cards = cardsLoader.load();

        Assert.assertNotNull(cards);
        Assert.assertEquals(cards.get(0).getWord(), "Hello");
    }

}