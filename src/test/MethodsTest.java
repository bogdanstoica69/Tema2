package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import lab.City;
import utils.Methods;

public class MethodsTest {
    City city;

    @Before
    public void setup(){
        city = new City(819827, "Razvilka", 55.591667, 37.740833,	"RU");
    }

    @Test
    public void testMethods(){
        Assert.assertNotEquals(null, Methods.getWeather(city));
        Assert.assertNotEquals(null, Methods.readFile());
    }

}
