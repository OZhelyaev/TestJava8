package org.sample;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sample.model.LanguageCard;
import org.sample.service.CardsLoader;

import java.util.List;

/**
 * Created by OZhelyaev on 25.03.2017.
 */
public class Main {
    private static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        log.info("Start project");

        Main m = new Main();
        m.playCards();
    }

    private void playCards() {
        CardsLoader cardsLoader = CardsLoader.getInstance();
        cardsLoader.setFilelName("card.csv");
        List<LanguageCard> cards = cardsLoader.load();
        log.info(cards.stream().count());
    }
}
