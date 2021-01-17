package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import lab.City;

public class CityTest {
    City city;
    @Before
    public void setup(){
        city = new City(819827, "Razvilka", 55.591667, 37.740833,	"RU");
    }
    @Test
    public void testCosntructor(){
        Assert.assertEquals(819827, city.getId());
        Assert.assertEquals("Razvilka", city.getDenumire());
        Assert.assertEquals(55.591667, city.getLatitudine(), 55.59);
        Assert.assertEquals(37.740833, city.getLongitudine(), 37.74);
        Assert.assertEquals("RU", city.getCod());
    }
}
