package vehicles;

public class Coordinates {
    private int longitude;
    private int latitude;
    private int height;

    private static int checkVal(int val) {
        if (val < 0)
            return 0;
        return val;
    };

    public Coordinates(int longitude, int latitude, int height){
        if (height > 100)
            height = 100;
        
        this.longitude = checkVal(longitude);
        this.latitude = checkVal(latitude);
        this.height = checkVal(height);
    }

    

    public int getLongitude(){
        return this.longitude;
    }

    public int getLatitude(){
        return this.latitude;
    }

    public int getHeight(){
        return this.height;
    }
}