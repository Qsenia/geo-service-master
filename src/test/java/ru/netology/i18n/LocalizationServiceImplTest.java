package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationServiceImplTest {

    @Test
    void testLocaleUSA() {
        LocalizationService localizationService = new LocalizationServiceImpl();
        String actual = localizationService.locale(Country.USA);
        String expected = "Welcome";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testLocaleRussia() {
        LocalizationService localizationService = new LocalizationServiceImpl();
        String actual = localizationService.locale(Country.RUSSIA);
        String expected = "Добро пожаловать";
        Assertions.assertEquals(expected, actual);
    }
}