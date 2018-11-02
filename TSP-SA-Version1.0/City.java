/* 
    City.java - Version 1.0 (02/11/18)
*/

package TSP_V1;

import java.util.Random;

/*
    City: Representa un nodo en el mapa
    -> Tiene coordenadas (x, y)
    -> Un ID que lo represena (AUN NO USADO)
 */
public class City {
    //Atributos
    private int id;
    private double x, y;

    //Constructores
    public City(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public City() {
        Random r = new Random();
        this.x = r.nextInt(200)+1; // Va de 1 a 200
        this.y = r.nextInt(200)+1;
    }

    public double getX() { return x; }
    public double getY() { return y; }

    // Devuelve distancia entre dos ciudades
    public double distanceToCity(City p) {
        return Math.sqrt(Math.pow(this.x - p.x,2) + Math.pow(this.y - p.y, 2));
    }

    // Imprime coordenadas del nodo
    // [!] - NOTA: Mejorar para que imprima un ID (Una letra en vez de numero?)
    public String toString (){
        return String.format("[x: %.2f, y: %.2f]", getX(), getY());
    }
}
