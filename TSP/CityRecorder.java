package TSP;

import java.util.ArrayList;

public class CityRecorder{
    private ArrayList<City> cities;
    private City baseCity;


    public CityRecorder(){
        this.baseCity = null;
        this.cities = new ArrayList<>();
    }

    public City getBaseCity(){ return this.baseCity; }
    public ArrayList getCities() {
        return this.cities;
    }

    public int getNumOfCities(){ return this.cities.size(); }
    public City getCity(int ind){
        return cities.get(ind);
    }

    public void addCity(City c) {
        if (c != baseCity && searchDuplicate(c))
            cities.add(c);
        else{
            System.out.printf("Found duplicate! [%c]\n", c.getId());
        }
    }

    public void setBaseCity(City c){
        if(this.baseCity == null){
            this.baseCity = c;
            for(int i=0; i<this.getNumOfCities(); i++){
                if(this.getCity(i) == c) {
                    getCities().remove(i);
                    break;
                }
            }
        } else {
            City temp = this.baseCity;
            cities.add(temp);
            this.baseCity = c;
            for(int i=0; i<this.getNumOfCities(); i++){
                if(this.getCity(i) == c) {
                    getCities().remove(i);
                    break;
                }
            }
        }
    }

    public boolean searchDuplicate(City c){
        for(City cityInRecorder : cities){
            if(cityInRecorder.equals(c))
                return false;
        }
        return true;
    }

    public boolean isEmpty(){
        if(this.cities.size() == 0)
            return true;
        else
            return false;
    }

    public void printRecorder(){
        if(isEmpty()){
            System.out.println("Case Empty!");
            return;
        }
        System.out.println("Base City: " + this.baseCity);
        String output;
        for(City c : cities){
            output = "ID: "+cities.indexOf(c)+" - "+c.toString()+" ("+c.getX()+", "+c.getY()+")";
            System.out.println(output);
        }
    }
}
