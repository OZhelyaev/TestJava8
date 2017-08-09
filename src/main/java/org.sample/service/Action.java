package org.sample.service;

import org.sample.model.LanguageCard;

/**
 * Created by OZhelyaev on 10.08.2017.
 */
interface Action {

    /**
     * @return next random langusge card
     */
    public LanguageCard next();

    /**
     * @param lc current language card
     * @param answer user answer
     * @return check result
     */
    public Boolean check(LanguageCard lc, String answer);
}
