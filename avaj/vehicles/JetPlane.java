package vehicles;

import tower.WeatherTower;
import vehicles.Coordinates;
import java.util.HashMap;

public class JetPlane extends Aircraft implements Flyable {
    private WeatherTower weatherTower = new WeatherTower();

    JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    public void updateConditions() {
        String weather = weatherTower.getWeather(this.coordinates);

        HashMap<String, String> weatherResponse = new HashMap<String, String>(){{
            put("SUN", "Let me put on my aviators!");
            put("RAIN", "I'll be out the rain in no time!");
            put("FOG", "Why didn't I check the weather for today!");
            put("SNOW", "Damn it's going to be a cold night!");
        }};

        if (weather == "SUN") {
            this.coordinates = new Coordinates(
                    coordinates.getLongitude() + 0,
                    coordinates.getLatitude() + 10,
                    coordinates.getHeight() + 2
            );
        }
        else if (weather == "RAIN") {
            this.coordinates = new Coordinates(
                    coordinates.getLongitude() + 0,
                    coordinates.getLatitude() + 5,
                    coordinates.getHeight() + 0
            );
        }
        else if (weather == "FOG") {
            this.coordinates = new Coordinates(
                    coordinates.getLongitude() + 0,
                    coordinates.getLatitude() + 1,
                    coordinates.getHeight() + 0
            );
        }
        else if (weather == "SNOW") {
            this.coordinates = new Coordinates(
                    coordinates.getLongitude() + 0,
                    coordinates.getLatitude() + 0,
                    coordinates.getHeight() - 7
            );
        }
        System.out.println("JetPlane#" + this.name + "(" + this.id + "): " + weatherResponse.get(weather));

        if (this.coordinates.getHeight() <= 0)
        {
            System.out.println("JetPlane#" + this.name + "(" + this.id + "): landing.");
            this.weatherTower.unregister(this);
            System.out.println("Tower says: JetPlane#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
        }
    }

    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        System.out.println("Tower says: JetPlane#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
    }
}