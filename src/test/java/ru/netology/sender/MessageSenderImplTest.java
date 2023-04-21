package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

import java.util.HashMap;
import java.util.Map;

class MessageSenderImplTest {
    //заглушки (mock) в виде GeoServiceImpl и LocalizationServiceImpl)
    // минимум 4 unit теста
    private LocalizationService localizationService;
    private GeoService geoService;
    private MessageSender messageSender;
    private Map<String, String> headers;

    @BeforeEach
    void setUp() {
        localizationService = Mockito.mock(LocalizationService.class);
        geoService = Mockito.mock(GeoService.class);
        headers = new HashMap<>();
        messageSender = new MessageSenderImpl(geoService, localizationService);
    }

    @Test
    public void testSendRussia() {
        Mockito.when(geoService.byIp("172."))
                .thenReturn(new Location(null, Country.RUSSIA, null, 0));
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.");
        String actual = messageSender.send(headers);
        String expected = localizationService.locale(Country.RUSSIA);
        Mockito.verify(geoService, Mockito.times(1)).byIp("172.");
        Mockito.verify(geoService, Mockito.never()).byIp("96.");
        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void testSendUSA() {
        Mockito.when(geoService.byIp("69."))
                .thenReturn(new Location(null, Country.USA, null, 0));
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "69.");
        String actual = messageSender.send(headers);
        String expected = localizationService.locale(Country.USA);
        Assertions.assertEquals(expected, actual);
    }
}