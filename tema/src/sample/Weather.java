package sample;

public class Weather {

    private City city;
    private String description;
    private String icon;
    private double temp;
    private double humidity;
    private double wind;

    public Weather(City city, String description, String icon, double temp, double humidity, double wind) {
        this.city = city;
        this.description = description;
        this.icon = icon;
        this.temp = temp;
        this.humidity = humidity;
        this.wind = wind;
    }

    public City getCity() {
        return city;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }

    public double getTemp() {
        return temp;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getWind() {
        return wind;
    }
}
