/*
    Route.java - Version 1.0 (02/11/18)
*/

package TSP_V1;

import java.util.ArrayList;
import java.util.Collections;

/*
    Route: Representa un camino 'x' que recorre todas las ciudades y que regresa al origen
        La idea es que esta Clase guarde y utilice todas las rutas creadas por el algoritmo y
        que ofrezca los métodos suficientes para que se utilicen en cualquier algoritmo creado.

    Contiene:
    -> ArrayList route: Es una lista que guarda una ruta completa (contiene objetos de tipo City)
    -> Distance: Guarda la distancia total de la ruta
 */
public class Route{
    // Atributos
    private ArrayList route = new ArrayList<City>();
    private int distance = 0;

    // Constructor
    // Recibe la lista de ciudades que existen en el caso, ya que se necesita su tamaño
    public Route(CityRecorder recorder){
        for (int i = 0; i < recorder.numOfCities(); i++) {
            route.add(null); // Se crea una ruta con espacios vacios (para ingresar ciudades)
        }
    }

    // Crea una copia nueva de Route, solo se pasa la lista actual de ciudades
    // La distancia se resetea a 0 (ES IMPORTANTE QUE SE HAGA ESTO)
    public void cloneRoute(ArrayList route){
        this.route = (ArrayList)route.clone();
        this.distance = 0;
    }

    // Devuelve la lista de ciudades del objeto
    public ArrayList getRoute(){
        return route;
    }

    // Asigno las ciudades que tengo en mi CityRecorder en una ruta, luego la desordeno
    // [!] - Necesita mejora/Otra forma
    public void createCities(CityRecorder recorder) {
        for (int i=0; i < recorder.numOfCities(); i++) {
            setCity(i, recorder.getCity(i));
        }
        // Desordeno la lista con la clase Collections
        Collections.shuffle(route);
    }

    // Devuelve una ciudad en la posición especificada (Route)
    public City getCity(int id) {
        return (City)route.get(id);
    }

    // Ingresa una ciudad en una lista, en la posición especificada
    public void setCity(int id, City city) {
        route.set(id, city);
        // Se tiene que resetear la distancia
        distance = 0;
    }

    // Devolver la distancia total de la ruta
    // [!] - Considero que se puede mejorar
    public int getDistance(){
        if (distance == 0) {
            int tourDistance = 0;
            // Un ciclo entre todas las ciudades de la ruta
            for (int cityIndex=0; cityIndex < routeSize(); cityIndex++) {
                //Se elige una ciudad de origen y destino
                City origin = getCity(cityIndex);
                City destiny;
                // Checar si no estamos devuelta en la ciudad origen
                if(cityIndex+1 < routeSize()){
                    destiny = getCity(cityIndex+1);
                }
                else{
                    destiny = getCity(0);
                }
                // Se van acumulando las distancias
                tourDistance += origin.distanceToCity(destiny);
            }
            distance = tourDistance;
        }
        return distance;
    }

    // Devolver cantidad de ciudades en la ruta
    public int routeSize() {
        return route.size();
    }

    // Imprime una ruta especifica
    // [!] - Necesita mejora, un mejor formato de impresión
    @Override
    public String toString() {
        String output = "[";
        for (int i = 0; i < routeSize(); i++) {
            output += getCity(i)+" -> ";
        }
        output += getCity(0)+"]";
        return output;
    }
}
