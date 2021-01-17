package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import lab.City;
import lab.Weather;

public class WeatherTest {
    Weather weather;
    @Before
    public void setup(){
        City city = new City(819827, "Razvilka", 55.591667, 37.740833,	"RU");
        weather = new Weather(city, "rain", "104", 10.75, 50, 10);
    }

    @Test
    public void testWeather(){
        Assert.assertEquals(819827, weather.getCity().getId());
        Assert.assertEquals("Razvilka", weather.getCity().getDenumire());
        Assert.assertEquals("RU", weather.getCity().getCod());
        Assert.assertEquals(55.591667, weather.getCity().getLatitudine(), 55.59);
        Assert.assertEquals(37.740833, weather.getCity().getLongitudine(), 37.74);
        Assert.assertEquals("rain", weather.getDescription());
        Assert.assertEquals(10.75, weather.getTemp(), 10.75);
        Assert.assertEquals(50, weather.getHumidity(), 50);
        Assert.assertEquals(10, weather.getWind(), 10);
        Assert.assertEquals("104", weather.getIcon());
    }

}
