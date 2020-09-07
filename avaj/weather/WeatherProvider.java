package weather;

import vehicles.Coordinates;

public class WeatherProvider {
    private static WeatherProvider weatherProvider = new WeatherProvider();
    private static String[] weather = {
        "RAIN", "SUN", "FOG", "SNOW"
    };
    public static WeatherProvider getProvider(){
        return weatherProvider.weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        return weather[(coordinates.getHeight() + coordinates.getLongitude() + coordinates.getLatitude()) % 4];
    }
}