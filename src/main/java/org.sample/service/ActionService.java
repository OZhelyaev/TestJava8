package org.sample.service;

import org.sample.model.LanguageCard;

/**
 * Created by OZhelyaev on 10.08.2017.
 */
public class ActionService implements Action {
    @Override
    public LanguageCard next() {
        return null;
    }

    @Override
    public Boolean check(LanguageCard lc, String answer) {
        return null;
    }
}
