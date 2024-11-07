package lv.rvt;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

class ApplicationVariables
{
    public int ResolutionX = 800;
    public int ResolutionY = 600;
}

public class App extends Application 
{
    ApplicationVariables settings;
    WeatherService service;

    //I use start method for initialization
    @Override
    public void start(Stage primaryStage) 
    {
        settings = new ApplicationVariables();
        service = new WeatherService("8045d2ac8814d11d0b326f9b72a04c72");

        primaryStage.setTitle("Weather Dashboard");

        Label cityLabel = new Label("City:");
        Label temperatureLabel = new Label("Temperature: ");
        Label humidityLabel = new Label("Humidity: ");
        Label forecastLabel = new Label("Forecast: ");
        Label windSpeedLabel = new Label("Wind speed: ");

        TextField cityInput = new TextField();
        cityInput.setPromptText("Enter city name");

        Label tempValueLabel = new Label("-- °C");
        Label humidityValueLabel = new Label("-- %");
        Label windValueSpeed = new Label("-- m/s");
        Label forecastValueLabel = new Label("No data");

        Button updateButton = new Button("Update Weather");

        updateButton.setOnAction(event -> {
            String city = cityInput.getText();
            if (city.isEmpty()) return;
            WeatherData data = service.getWeatherByCity(city);
            System.out.println(data.name);
            tempValueLabel.setText(String.valueOf(data.temperature) + " °C"); 
            humidityValueLabel.setText(String.valueOf(data.humidity) + " %"); 
            windValueSpeed.setText(String.valueOf(data.windSpeed) + " m/s");
        });

        GridPane weatherGrid = new GridPane();
        weatherGrid.setAlignment(Pos.CENTER);
        weatherGrid.setHgap(10);
        weatherGrid.setVgap(10);
        weatherGrid.setPadding(new Insets(10, 10, 10, 10));

        weatherGrid.add(cityLabel, 0, 0);
        weatherGrid.add(cityInput, 1, 0);
        weatherGrid.add(temperatureLabel, 0, 1);
        weatherGrid.add(tempValueLabel, 1, 1);
        weatherGrid.add(humidityLabel, 0, 2);
        weatherGrid.add(humidityValueLabel, 1, 2);
        weatherGrid.add(windSpeedLabel, 0, 3);
        weatherGrid.add(windValueSpeed, 1, 3);
        weatherGrid.add(forecastLabel, 0, 4);
        weatherGrid.add(forecastValueLabel, 1, 4);

        HBox buttonPanel = new HBox(10);
        buttonPanel.setAlignment(Pos.CENTER);
        buttonPanel.getChildren().add(updateButton);

        VBox mainLayout = new VBox(10);
        mainLayout.setPadding(new Insets(20, 20, 20, 20));
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.getChildren().addAll(weatherGrid, buttonPanel);

        Scene scene = new Scene(mainLayout, 300, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) 
    {
        Application.launch(args);
    }
}
