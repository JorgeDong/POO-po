public class PruebasV1 {
    public static void main(String[] args) {

        // Caso 1: Con ciudades creadas al azar
        CityRecorder case1 = new CityRecorder();
        for (int i = 0; i < 15; i++) {
            City c1 = new City();
            case1.addCity(c1);
        }

        SA s1 = new SA(100000, 0.0003);
        //s1.solveSA(case1);

        // Caso 2: Ciudades establecidas
        CityRecorder case2 = new CityRecorder();
        City city = new City(60, 200, 'A');
        case2.addCity(city);
        City city2 = new City(180, 200, 'B');
        case2.addCity(city2);
        City city3 = new City(80, 180, 'C');
        case2.addCity(city3);
        City city4 = new City(140, 180, 'D');
        case2.addCity(city4);
        City city5 = new City(120, 160, 'E');
        case2.addCity(city5);
        City city6 = new City(100, 160, 'F');
        case2.addCity(city6);
        City city7 = new City(200, 160, 'G');
        case2.addCity(city7);
        City city8 = new City(140, 140, 'H');
        case2.addCity(city8);
        City city9 = new City(140, 120, 'I');
        case2.addCity(city9);
        City city10 = new City(100, 120, 'J');
        case2.addCity(city10);

        case2.setBaseCity(city6);

        SA s2 = new SA(100000, 0.0001);
        s2.solveSA(case2);
    }
}
