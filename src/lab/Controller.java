package lab;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.Methods;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class Controller implements Initializable {

    @FXML
    private ComboBox<String> countryComboBox;

    @FXML
    private ComboBox<String> cityComboBox;

    @FXML
    private Label humidityLabel;

    @FXML
    private Label windLabel;

    @FXML
    private ImageView image;

    @FXML
    private Label degreeLabel;

    @FXML
    private Label cityLabel;

    @FXML
    private Label descriptionLabel;

    private String selectedCountry;
    private String selectedCity;
    private Map<String, List<City>> citiesMap;
    private Weather currentWeather;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> countryList = FXCollections.observableArrayList(citiesMap.keySet());
        countryComboBox.setItems(countryList);
    }

    public Controller() {
        citiesMap = Methods.readFile();
    }

    @FXML
    /*
    * la alegerea unui nou oras se vor atribui valorile returnate de metoda getWeather() catre campurile UI corespunzatoare
    * */
    void cityChanged(ActionEvent event) {
        selectedCity = cityComboBox.getValue();
        if (selectedCity != null) {
            City city = citiesMap.get(selectedCountry).stream().filter(c -> selectedCity.equals(c.getDenumire())).findAny().orElse(null);
            currentWeather = Methods.getWeather(city);
            humidityLabel.setText("Humidity: " + currentWeather.getHumidity() + "%");
            windLabel.setText("Wind: " + currentWeather.getWind() + "km/h");
            degreeLabel.setText(currentWeather.getTemp() + " C");
            cityLabel.setText(selectedCity);
            descriptionLabel.setText(currentWeather.getDescription());
            image.setImage(new Image("http://openweathermap.org/img/wn/" + currentWeather.getIcon() +".png"));
        }
    }

    @FXML
    /*
    *la alegerea unei noi tari se vor sterge valorile elementelor ui corespunzatoare vremii, se va curata combo box-ul pentru orase si i se vor atrebui noile orase specifice tarii alese
    * orasele se vor atribui dintr-un map care contine o lista cu orase specifice fiecarui cod de tara
    * valorile din acest map sunt preluate in costrunctor cu ajutorul functiei readFile
     */
    void countryChanged(ActionEvent event) {
        selectedCity = "";
        currentWeather = null;
        humidityLabel.setText("");
        windLabel.setText("");
        degreeLabel.setText("");
        cityLabel.setText("");
        descriptionLabel.setText("");
        image.setImage(null);
        selectedCountry = countryComboBox.getValue();
        cityComboBox.getSelectionModel().clearSelection();
        cityComboBox.getItems().clear();
        List<String> cities = citiesMap.get(selectedCountry).stream().map(City::getDenumire).collect(Collectors.toList());
        ObservableList<String> cityList = FXCollections.observableArrayList(cities);
        cityComboBox.setItems(cityList);
    }
}
