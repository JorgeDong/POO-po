/*
    Aqui es donde se utiliza el SA a su gusto
*/

package TSP_V1;

public class PruebasV1 {
    public static void main(String[] args){
        // Caso 2: Ciudades establecidas
        CityRecorder case2 = new CityRecorder();
        City city = new City(60, 200, 'A');
        case2.addCity(city);
        City city2 = new City(180, 200, 'B');
        case2.addCity(city2);
        City city3 = new City(80, 180,'C');
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
        City city9 = new City(140, 120,'I');
        case2.addCity(city9);
        City city10 = new City(100, 120, 'J');
        case2.addCity(city10);

        /* IMPORTANTE:
            Actualmente solo de esta forma se puede resolver correctamente el TSP con SA: después
            de crear un CityRecorder y agregar ciudades, hacer en este orden
                1) Declarar ciudad base
                2) Crear un nuevo SA
                3) Usar el método solve()
            De otra forma, hay algunos errores con las rutas, la ciudad base no se declara o calcula
            la ciudad base doble.
            Como viene a continuación es la forma (actual) en la que se resuelve
         */
        case2.declareBaseCity(city4); //Pon comentarios aqui, para ver como se resuelve sin ciudad base
        SA s1 = new SA(case2);
        s1.solveSA();

        // Si se quiere cambiar de ciudad base, es necesario crear un nuevo SA y seguir el orden
        case2.declareBaseCity(city10);
        SA s2 = new SA(case2);
        s2.solveSA();
    }

}
