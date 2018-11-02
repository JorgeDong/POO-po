/*
    Aqui es donde se utiliza el SA a su gusto
*/

package TSP_V1;

public class PruebasV1 {
    public static void main(String[] args){

        // Caso 1: Con ciudades creadas al azar
        CityRecorder case1 = new CityRecorder();
        for(int i=0; i<12; i++){
            City c1 = new City();
            case1.addCity(c1);
        }

        // Caso 2: Ciudades establecidas
        CityRecorder case2 = new CityRecorder();
        City city = new City(60, 200);
        case2.addCity(city);
        City city2 = new City(180, 200);
        case2.addCity(city2);
        City city3 = new City(80, 180);
        case2.addCity(city3);
        City city4 = new City(140, 180);
        case2.addCity(city4);
        City city5 = new City(20, 160);
        case2.addCity(city5);
        City city6 = new City(100, 160);
        case2.addCity(city6);
        City city7 = new City(200, 160);
        case2.addCity(city7);
        City city8 = new City(140, 140);
        case2.addCity(city8);
        City city9 = new City(40, 120);
        case2.addCity(city9);
        City city10 = new City(100, 120);
        case2.addCity(city10);

        SA s1 = new SA(case2);
        s1.solveSA();
    }

}
