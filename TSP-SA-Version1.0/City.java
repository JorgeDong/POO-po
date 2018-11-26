
import java.util.Random;

public class City {
    private static int count = 65;

    private char id;
    private int x, y;

    public City(int x, int y, char id) {
        this.x = x;
        this.y = y;
        this.id = id;
        count++;
    }
    public City() {
        Random r = new Random();
        this.x = r.nextInt(200)+1; // Va de 1 a 200
        this.y = r.nextInt(200)+1;
        this.id = (char)count++;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public char getId() {return id; }

    public double distanceToCity(City c) {
        return Math.sqrt(Math.pow(this.x - c.x, 2) + Math.pow(this.y - c.y, 2));
    }

    public String toString (){

        return String.format("[%c]",getId());
        //return String.format("ID: %c [x: %d, y: %d]",getId(), getX(), getY());
    }
}
