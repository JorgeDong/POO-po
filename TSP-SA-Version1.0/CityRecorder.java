/*
      CityRecorder.java - Version 1.0 (02/11/18)
*/

package TSP_V1;

import java.util.ArrayList;

/*
    CityRecorder: Es una clase que guarda todas las ciudades en un mapa,
        La idea es que aqui es el lugar donde se "guardará" todas las ciudades que se necesiten
        y que cada algoritmo use esta clase y sus metodos
    Contiene:
    -> ArrayList allCities: Guarda todas las ciudades
    -> City baseCity: Guarda la ciudad base donde se empieza a calcular las rutas
 */
public class CityRecorder{
    // Atributos
    protected ArrayList allCities;
    protected City baseCity;

    // Constructor
    public CityRecorder(){
        this.baseCity = null;
        this.allCities = new ArrayList<City>();
    }

    //Declaración de la ciudad base
    public void declareBaseCity(City c){
        // SI no se ha asignado una ciudad base, se declara igualmente
        if(this.baseCity == null){
            this.baseCity = c;
            for(int i=0; i<this.numOfCities(); i++){
                if(this.getCity(i) == c) {
                    this.allCities.remove(i);
                    break;
                }
            }
        }
        // SI ya ha sido declarada antes, nos aseguramos de guardar la ciudad base y
        // ponerlo en la lista con las demás ciudades
        else {
            City temp = this.baseCity;
            this.allCities.add(temp);
            this.baseCity = c;
            for(int i=0; i<this.numOfCities(); i++){
                if(this.getCity(i) == c) {
                    this.allCities.remove(i);
                    break;
                }
            }
        }
    }

    public City getBaseCity(){ return this.baseCity; }
    // Devuelve la lista completa de ciudades guardades en este CityRecorder
      
    public ArrayList getRecorder() {
        return allCities;
    }

    // Añade una ciudad, siempre al final
    public void addCity(City city) {
        allCities.add(city);
    }

    //Devuelve la ciudad en la posición 'id'
    public City getCity(int ind){
        return (City)allCities.get(ind);
    }

    //Devuelve el total de ciudades dentro de la lista
    public int numOfCities(){
        return allCities.size();
    }

    //Imprime todas las ciudades (en el orden en que se fueron agregando)
    // [!] - Necesita mejora, dar una mejor formato
    public void printAllCities(){
        System.out.println("Base City: " + this.baseCity);
        String output = "";
        for(Object c : allCities){
            output = "ID: "+allCities.indexOf(c)+" - "+c.toString();
            System.out.println(output);
        }
    }
}
