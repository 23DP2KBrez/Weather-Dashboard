package lv.rvt;

import org.json.JSONObject;

public class WeatherService 
{
    String apiKey;
    String apiUrl = "https://api.openweathermap.org/data/2.5/weather";

    public WeatherService(String apiKey) 
    {
        this.apiKey = apiKey;
    }

    public WeatherService() 
    {
    }

    public WeatherData getWeatherByCity(String city)
    {
        WeatherData data = new WeatherData();
        String url = apiUrl + "?q=" + city + "&appid=" + apiKey + "&units=metric";

        Response weatherBody = RequestManager.createSimpleRequest(url);
        if (!weatherBody.status) return null;

        JSONObject weatherDataJson = new JSONObject(weatherBody.body);
        JSONObject main = weatherDataJson.getJSONObject("main");
        JSONObject wind = weatherDataJson.getJSONObject("wind");

        data.name = weatherDataJson.getString("name");
        data.temperature = main.getDouble("temp");
        data.humidity = main.getInt("humidity");

        data.windSpeed = wind.getDouble("speed");
        data.windDegrees = wind.getDouble("deg");
        return data;
    }

}
