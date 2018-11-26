
import java.util.ArrayList;

public class CityRecorder{
    // Atributos
    private ArrayList cities;
    protected City baseCity;

    // Constructor
    public CityRecorder(){
        this.baseCity = null;
        this.cities = new ArrayList<City>();
    }

    public City getBaseCity(){ return this.baseCity; }
    public ArrayList getCities() {
        return cities;
    }
    public int getCitiesSize(){
        return cities.size();
    }

    public City getCity(int ind){
        return (City)cities.get(ind);
    }
    public void addCity(City city) {
        if (city != baseCity)
            cities.add(city);
    }

    public void setBaseCity(City c){
        if(this.baseCity == null){
            this.baseCity = c;
            for(int i=0; i<this.getCitiesSize(); i++){
                if(this.getCity(i) == c) {
                    getCities().remove(i);
                    break;
                }
            }
        } else {
            City temp = this.baseCity;
            getCities().add(temp);
            this.baseCity = c;
            for(int i=0; i<this.getCitiesSize(); i++){
                if(this.getCity(i) == c) {
                    getCities().remove(i);
                    break;
                }
            }
        }
    }

    public void printRecorder(){
        System.out.println("Base City: " + this.baseCity);
        String output = "";
        for(Object c : cities){
            output = "ID: "+cities.indexOf(c)+" - "+c.toString();
            System.out.println(output);
        }
    }
}
