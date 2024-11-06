package lv.rvt;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
    public void start(Stage stage) 
    {
        settings = new ApplicationVariables();
        service = new WeatherService("8045d2ac8814d11d0b326f9b72a04c72");

        Group root = new Group();
        Scene scene = new Scene(root, settings.ResolutionX, settings.ResolutionY);
        scene.setFill(Color.LIGHTGRAY);

        Circle circle = new Circle(60, 40, 30, Color.GREEN);

        Text text = new Text(10, 90, "Weather Dashboard");
        text.setFill(Color.DARKRED);

        Font font = new Font(20);
        text.setFont(font);

        Button button = new Button("Weather");
        button.setLayoutX(100);
        button.setLayoutY(100);
        
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) 
            {
                WeatherData data;
                data = service.getWeatherByCity("Riga");
                System.out.println(data.name);
                System.out.println(data.temperature);
                System.out.println(data.humidity);
            }
        };

        button.setOnAction(event);

        root.getChildren().add(button);
        root.getChildren().add(circle);
        root.getChildren().add(text);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) 
    {
        Application.launch(args);
    }
}
