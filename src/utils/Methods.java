package utils;

import org.json.JSONException;
import org.json.JSONObject;
import lab.City;
import lab.Weather;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.*;

public class Methods {
    private static final double KelvinCelsiusDifference = -272.15;

    /*
    * va citi datele din fisierul Source.txt
    * datele vor fi atribuite unui obiect al clasei City
    * va returna un map care va contine ca si keys codurile tarilor regasite in fisier si ca values Liste cu orasele corespunzatoare tarilor
    * */
    public static Map<String, List<City>> readFile() {
        Map<String, List<City>> cities = new HashMap<>();
        try {
            File myObj = new File("src\\resources\\Source.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                long id = Long.parseLong(myReader.next());
                String denumire = myReader.next();
                double lat = Double.parseDouble(myReader.next());
                double lon = Double.parseDouble(myReader.next());
                String cod = myReader.next();
                City city = new City(id, denumire, lat, lon, cod);
                if(cities.keySet().contains(cod)) {
                    List<City> temp = cities.get(cod);
                    temp.add(city);
                    cities.put(cod, temp);
                }else{
                    List<City> temp = new ArrayList<>();
                    temp.add(city);
                    cities.put(cod, temp);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return cities;
    }

    /*
    * primeste un obiect al clasei City pentru care va cauta datele meteo cu ajutorul apelului catre un api openweather
    * atribuie valorile returnate de api intr-un obiect al clasei Weather
    * returneaza obiectul weather
    * in cazul in care apelul catre api este gresit sau nu s-au gasit informatiile cautate, va returna NULL
    * */
    public static Weather getWeather(City city){
        Weather weather = null;
        try {
            String tempURL = Config.apiUrl + city.getId() + "&appid=" + Config.apiKey;
            URL url = new URL(tempURL);
            System.out.println(tempURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            int responseCode = con.getResponseCode();
            if(responseCode == 200){
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                JSONObject myResponse = new JSONObject(response.toString());
                JSONObject obj1 = (JSONObject) myResponse.getJSONArray("weather").get(0);
                JSONObject obj2 = myResponse.getJSONObject("main");
                JSONObject obj3 = myResponse.getJSONObject("wind");
                String description = obj1.getString("description");
                String icon = obj1.getString("icon");
                double temp = obj2.getDouble("temp");
                temp = temp + KelvinCelsiusDifference;
                DecimalFormat df = new DecimalFormat("#.##");
                temp = Double.parseDouble(df.format(temp));
                double humidity = obj2.getDouble("humidity");
                double wind = obj3.getDouble("speed");
                weather = new Weather(city, description, icon, temp, humidity, wind);
            }
            else {
                System.out.println("Eroare la preluarea datelor, status code:" + responseCode);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return weather;
    }

}
