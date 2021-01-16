package sample;

public class City {
    private long id;
    private String denumire;
    private double latitudine;
    private double longitudine;
    private String cod;

    public City(long id, String denumire, double latitudine, double longitudine, String cod) {
        this.id = id;
        this.denumire = denumire;
        this.latitudine = latitudine;
        this.longitudine = longitudine;
        this.cod = cod;
    }

    public long getId() {
        return id;
    }

    public String getDenumire() {
        return denumire;
    }

    public double getLatitudine() {
        return latitudine;
    }

    public double getLongitudine() {
        return longitudine;
    }

    public String getCod() {
        return cod;
    }
}
