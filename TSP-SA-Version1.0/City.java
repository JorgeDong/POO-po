/* 
    City.java - Version 1.0 (02/11/18)
*/

package TSP_V1;

import java.util.Random;

/*
    City: Representa un nodo en el mapa
    -> Tiene coordenadas (x, y)
    -> Un ID que lo representa (Una letra)
 */
public class City {
    //Atributos
    private char id;
    private int x, y;

    //Constructores
    public City(double x, double y) {
        this.x = x;
        this.y = y;
        this.id = id;
    }
    public City() {
        Random r = new Random();
        this.x = r.nextInt(200)+1; // Va de 1 a 200
        this.y = r.nextInt(200)+1;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public char getId() {return id; }

    // Devuelve distancia entre dos ciudades
    public double distanceToCity(City p) {
        return Math.sqrt(Math.pow(this.x - p.x,2) + Math.pow(this.y - p.y, 2));
    }

    // Imprime coordenadas del nodo
    public String toString (){
        return String.format("ID: %c [x: %d, y: %d]",getId(), getX(), getY());
    }
}
