import java.util.ArrayList;
import java.util.Collections;

public class Route {

    private ArrayList route = new ArrayList<City>();
    private double distance = 0;
    private static City baseCity;

    public Route(CityRecorder recorder) {
        baseCity = recorder.getBaseCity();
        for (int i = 0; i < recorder.getCitiesSize(); i++) {
            route.add(null);
        }
    }

    public ArrayList getRoute() { return route; }
    public City getBaseCity() { return baseCity; }
    public int getRouteSize() {
        return route.size();
    }
    public City getCity(int id) {
        return (City) route.get(id);
    }

    public void setCity(int id, City city) {
        route.set(id, city);
        distance = 0;
    }

    public void cloneRoute(ArrayList route){
        this.route = (ArrayList)route.clone();
        this.distance = 0;
    }

    public void createCities(CityRecorder recorder) {
        for (int i=0; i < recorder.getCitiesSize(); i++) {
            setCity(i, recorder.getCity(i));
        }
        Collections.shuffle(route);
    }

    public double getDistance(){
        if(getBaseCity() == null){ //Si no existe una ciudad base
            if (this.distance == 0) {
                double tempDistance = 0;
                // Un ciclo entre todas las ciudades de la ruta
                for (int cityIndex=0; cityIndex < getRouteSize(); cityIndex++) {
                    //Se elige una ciudad de origen y destino
                    City origin = getCity(cityIndex);
                    City destiny;
                    // Checar si no estamos devuelta en la ciudad origen
                    if(cityIndex+1 < getRouteSize()){
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
            if (distance == 0) { // Si EXISTE una ciudad base...
                City lastCity = getCity(getRouteSize()-1); //Eligimos la ultima ciudad de la ruta (mas abajo porque)
                double tempDistance = 0;
                // Se saca la distancia entre la ciudad base y la primera ciudad de la ruta
                tempDistance += getBaseCity().distanceToCity(getCity(0));
                for (int cityIndex=0; cityIndex < getRouteSize(); cityIndex++) {
                    //Se elige una ciudad de origen y destino
                    City origin = getCity(cityIndex);
                    City destiny;
                    // Checar si no estamos devuelta en la ciudad origen
                    if(cityIndex+1 < getRouteSize()){
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
        // Si NO existe una ciudad base
        if(getBaseCity() == null){
            String output = "";
            for (int i = 0; i < getRouteSize(); i++) {
                output += getCity(i)+" -> ";
            }
            output += ""+getCity(0);
            return output;
        }else{
            // Si EXISTE una ciudad base
            String output = ""+getBaseCity();
            for (int i = 0; i < getRouteSize(); i++) {
                output += " -> "+getCity(i);
            }
            output += " -> "+getBaseCity();
            return output;
        }
    }
}
