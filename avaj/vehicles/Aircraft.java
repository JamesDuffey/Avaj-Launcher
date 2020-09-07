package vehicles;

public class Aircraft {
    protected Long id = 0L;
    protected String name;
    protected Coordinates coordinates;
    private static Long idCounter = 1L;

    protected Aircraft(String name, Coordinates coordinates) {
        this.id = nextId();
        this.name = name;
        this.coordinates = coordinates;
    }

    private Long nextId(){
        return idCounter++;
    }
}