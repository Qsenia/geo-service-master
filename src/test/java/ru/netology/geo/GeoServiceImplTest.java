package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import static ru.netology.geo.GeoServiceImpl.MOSCOW_IP;

class GeoServiceImplTest {
    @Test
    void testByIpUSA() {
        GeoService geoService = new GeoServiceImpl();
        Country actual = geoService.byIp("96.").getCountry();
        Country expected = new Location(null, Country.USA, null, 0).getCountry();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testByIpRussia() {
        GeoService geoService = new GeoServiceImpl();
        Country actual = geoService.byIp("172.").getCountry();
        Country expected = new Location(null, Country.RUSSIA, null, 0).getCountry();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testByIpCityMoscow() {
        GeoService geoService = new GeoServiceImpl();
        Location location = geoService.byIp("172.0.32.11");
        String actual = location.getCity();
        String expected = new Location("Moscow", null, null, 0).getCity();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testByIpCityNewYork() {
        GeoService geoService = new GeoServiceImpl();
        Location location = geoService.byIp("96.44.183.149");
        String actual = location.getCity();
        String expected = new Location("New York", null, null, 0).getCity();
        Assertions.assertEquals(expected, actual);
    }
}