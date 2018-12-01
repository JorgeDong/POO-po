package TSP;

import java.util.ArrayList;
import java.util.Collections;

/*
    Route: Es una clase que guarda una ruta de n ciudades
    Utiliza el recorder para saber el número de ciudades que tiene que guardar

    OJO: Si existe una ciudad base, el tamaño de la ruta no toma en cuenta la base, ya que la ciudad
        base se maneja aparte en el recorder. Esto no afecta en el calculo de getDistance()
        ya que toma en cuenta la ciudad base que da el recorder
 */
public class Route {

    private ArrayList<City> route = new ArrayList<>();
    private double distance = 0;
    private static City baseCity; //Recibo la ciudad base del recorder y lo manejo como variable
                                   //global para toda Route creada

    //Crea una ruta con N espacios PERO vacia (null)
    //En metodo fillCities() lleno la ruta con las ciudades que tengo en el recorder
    public Route(CityRecorder recorder) {
        baseCity = recorder.getBaseCity();
        for (int i = 0; i < recorder.getNumOfCities(); i++) {
            route.add(null);
        }
    }

    public ArrayList getRoute() { return route; }
    public City getBaseCity() { return baseCity; }

    public int getSize() {
        return route.size();
    }
    public City getCity(int id) { return route.get(id); }

    public void setCity(int id, City city) {
        this.route.set(id, city);
        this.distance = 0;
    }

    public void cloneRoute(ArrayList route){
        this.route = (ArrayList)route.clone();
        this.distance = 0;
    }

    // Llenar una ruta con ciudades del recorder (ordenamineto al azar)
    public void fillCities(CityRecorder recorder) {
        for (int i=0; i < recorder.getNumOfCities(); i++) {
            setCity(i, recorder.getCity(i));
        }
        Collections.shuffle(route);
    }

    public double getDistance(){
        if(getBaseCity() == null){      //NO existe una ciudad base
            if (this.distance == 0) {
                double tempDistance = 0;
                // Un ciclo entre todas las ciudades de la ruta
                for (int cityIndex=0; cityIndex < getSize(); cityIndex++) {
                    //Se elige una ciudad de origen y destino
                    City origin = getCity(cityIndex);
                    City destiny;
                    // Checar si no estamos devuelta en la ciudad origen
                    if(cityIndex+1 < getSize()){
                        destiny = getCity(cityIndex+1);
                    }
                    else{
                        destiny = getCity(0);
                    }
                    // Se van acumulando las distancias
                    tempDistance += origin.distanceToCity(destiny);
                }
                this.distance = tempDistance;
            }
        }
        else{
            if (distance == 0) {       // SI EXISTE una ciudad base...
                City lastCity = getCity(getSize()-1); //Eligimos la ultima ciudad de la ruta (mas abajo porque)
                double tempDistance = 0;
                // Se saca la distancia entre la ciudad base y la primera ciudad de la ruta
                tempDistance += getBaseCity().distanceToCity(getCity(0));
                for (int cityIndex=0; cityIndex < getSize(); cityIndex++) {
                    //Se elige una ciudad de origen y destino
                    City origin = getCity(cityIndex);
                    City destiny;
                    // Checar si no estamos devuelta en la ciudad origen
                    if(cityIndex+1 < getSize()){
                        destiny = getCity(cityIndex+1);
                    }
                    else{
                        destiny = getCity(0);
                    }
                    // Se van acumulando las distancias
                    tempDistance += origin.distanceToCity(destiny);
                }
                // AL final, se saca la distancia entre la ultima ciudad (lastCity) y la ciudad base
                tempDistance += lastCity.distanceToCity(getBaseCity());
                this.distance = tempDistance;
            }
        }
        return this.distance;
    }

    @Override
    public String toString() {
        if(getBaseCity() == null){      // NO existe una ciudad base
            String output = "";
            for (int i = 0; i < getSize(); i++) {
                output += getCity(i)+" -> ";
            }
            output += ""+getCity(0);
            return output;
        }else{                          // SI existe una ciudad base
            String output = ""+getBaseCity();
            for (int i = 0; i < getSize(); i++) {
                output += " -> "+getCity(i);
            }
            output += " -> "+getBaseCity();
            return output;
        }
    }
}
