package vehicles;

import tower.WeatherTower;
import vehicles.Coordinates;
import java.util.HashMap;

public class Baloon extends Aircraft implements Flyable {
    private WeatherTower weatherTower = new WeatherTower();

    Baloon(String name, Coordinates coordinates){
        super(name, coordinates);
    }

    public void updateConditions() {
        String weather = weatherTower.getWeather(this.coordinates);

        HashMap<String, String> weatherResponse = new HashMap<String, String>(){{
            put("SUN", "What beautiful weather we're having!");
            put("RAIN", "This rain is going to ruin my baloon!");
            put("FOG", "I can't see! How am I supposed to land.");
            put("SNOW", "If I don't land I will die!");
        }};

        if (weather == "SUN") {
            this.coordinates = new Coordinates(
                coordinates.getLongitude() + 2,
                coordinates.getLatitude() + 0,
                coordinates.getHeight() + 4
            );
        }
        else if (weather == "RAIN") {
            this.coordinates = new Coordinates(
                    coordinates.getLongitude() + 0,
                    coordinates.getLatitude() + 0,
                    coordinates.getHeight() - 5
            );
        }
        else if (weather == "FOG") {
            this.coordinates = new Coordinates(
                    coordinates.getLongitude() + 0,
                    coordinates.getLatitude() + 0,
                    coordinates.getHeight() - 3
            );
        }
        else if (weather == "SNOW") {
            this.coordinates = new Coordinates(
                    coordinates.getLongitude() + 0,
                    coordinates.getLatitude() + 0,
                    coordinates.getHeight() - 15
            );
        }
        System.out.println("Baloon#" + this.name + "(" + this.id + "): " + weatherResponse.get(weather));

        if (this.coordinates.getHeight() <= 0)
        {
            System.out.println("Baloon#" + this.name + "(" + this.id + "): landing.");
            this.weatherTower.unregister(this);
            System.out.println("Tower says: Baloon#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
            System.out.println("Baloon#" + this.name + "(" + this.id + "): Coordinates(Longitude: " + coordinates.getLongitude() + ", Latitude: " + coordinates.getLatitude() + ", Height: " + coordinates.getHeight() + ").");
        }
    }

    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        System.out.println("Tower says: Baloon#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
    }
}