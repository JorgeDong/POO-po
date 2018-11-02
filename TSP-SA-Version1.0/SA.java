/*
    SA (Simulated Annealing Algorithm) - Version 1.0 (02/11/18)
*/

package TSP_V1;

/*
    SA: Es el algoritmo de Simulated Annealing (Luego pongo explicacion de que es)
        El fin de esta clase es que se reciba un CityRecorder con las ciudades y poder hacer
        los calculos necesarios y arrojar información de lo encontrado
    Contiene:
    -> temp: Es nuestra variable de "Temperatura Maxima". Se le asigna un valor inicial y se ira
             reduciendo hasta llegar a una "temperatura minima" (en este caso es '1')
    -> coefficient: Es nuestro "enfriador", significa que esta variable va ir reduciendo poco a poco
                    a temp hasta llegar a su mínimo
    -> energy: Es nuestra variable que nos indica que tan buena es nuesta solucion (en este caso es
                que tan buena es nuestra distancia)
    -> newEnergy: Es el cambio de configuración, se define una nueva energia

    -> currentRoute: Una lista con un acomodo de ciudades actual
    -> nextRoute: Una lista tentativa de acomodo de ciudades a considerar y comparar
    -> bestRoute: Es el acomodo de ciudades que contiene el menor costo

    -> iterations: Va ser el contador de combinaciones (cambios de configuracion en la energia) que
                   se calcularon en el algoritmo
    -> recorder: Es nuestra lista de ciudades con las que se va a trabajar
 */

public class SA {
    private double temp, coefficient, energy, newEnergy;
    private Route currentRoute, nextRoute, bestRoute;
    private int iterations;
    private CityRecorder recorder;

    // Constructores
    //Solo recibe la lista de ciudades que se va a utilizar
    public SA(CityRecorder recorder){
        this.temp = 10000;
        this.coefficient = 0.003;
        this.iterations = 0;
        this.recorder = recorder;
        this.currentRoute = new Route(recorder);
        currentRoute.createCities(recorder);
        this.bestRoute = new Route(recorder);
        bestRoute.cloneRoute(currentRoute.getRoute());
    }

    // Recibe la lista de ciudades y la temperatura maxima inicial y el coeficiente que se resta
    // Mientras mas grande sea temp = Mayores iteraciones
    // Mientras mas pequeño sea coefficient = Mayores iteraciones
    public SA(CityRecorder recorder, double temp, double coefficient){
        this.temp = temp;
        this.coefficient = coefficient;
        this.iterations = 0;
        this.recorder = recorder;
        this.currentRoute = new Route(recorder);
        currentRoute.createCities(recorder);
        this.bestRoute = new Route(recorder);
        bestRoute.cloneRoute(currentRoute.getRoute());
    }

    public void setTemp(double temp) { this.temp = temp; }
    public void setIterations(int iterations) { this.iterations = iterations; }
    public void setEnergy(double energy) { this.energy = energy; }
    public void setNewEnergy(double newEnergy) { this.newEnergy = newEnergy; }

    public double getTemp() { return temp; }
    public double getCoefficient() { return coefficient; }
    public int getIterations() { return iterations; }
    public double getEnergy() { return energy; }
    public double getNewEnergy() { return newEnergy; }

    // Va a revisar que tan buena es la solucion encontrada
    // Si no es buena, no se descarta, ya que si aún se tiene una temp grande, hay
    // mas probabilidades que se acepte la solucion
    public double checkProbability(double energy, double newEnergy) {
        if (newEnergy < energy) {
            return 1.0;
        }
        return Math.exp((energy - newEnergy) / getTemp());
    }

    // Aqui ya se empieza a resolver el TSP
    // Necesita mejora, ver implementación tentativa de la Version 2 (TSP_V2)
    public void solveSA(){
        // Mientras que la temperatura no baje de '1'
        while(getTemp() > 1) {
            this.nextRoute = new Route(recorder);   // Inicializamos nuestra ruta a considerar
            nextRoute.cloneRoute(currentRoute.getRoute());

            // Agarramos dos posiciones al azar (de 0 al tamaño de la lista)
            int randomPos_1 = (int) (nextRoute.routeSize() * Math.random());
            int randomPos_2 = (int) (nextRoute.routeSize() * Math.random());

            // Intercambiamos las ciudades en las posiciones que se eligieron
            City swayCity_1 = nextRoute.getCity(randomPos_1);
            City swapCity_2 = nextRoute.getCity(randomPos_2);
            nextRoute.setCity(randomPos_2, swayCity_1);
            nextRoute.setCity(randomPos_1, swapCity_2);

            //Revisamos la nueva distancia creada
            setEnergy(currentRoute.getDistance());
            setNewEnergy(nextRoute.getDistance());

            //Verificamos si nuestra ruta es "buena"
            if(checkProbability(getEnergy(),getNewEnergy()) > Math.random()){
                currentRoute.cloneRoute(nextRoute.getRoute());
            }
            //Si llega a ser mejor que la que ya teníamos, se vuelve la mejor ruta
            if (currentRoute.getDistance() < bestRoute.getDistance()) {
                bestRoute.cloneRoute(currentRoute.getRoute());
            }

            // Reducimos nuestra temperatura
            setTemp(this.temp*(1-getCoefficient()));
            setIterations(this.iterations+1);

        }
        // Metodos que imprimen los resultados
        // [!] - Necesitan mejora, ver otras opciones talvez
        shortestDistance(this.bestRoute);
        shortestRoute(this.bestRoute);
        //return bestRoute.getDistance();
    }

    // Imprime la distancia más corta encontrada
    public void shortestDistance(Route bestRoute){
        System.out.println("Final solution distance: " + bestRoute.getDistance());
    }

    // Imprime la ruta mas corta encontrada y las iteraciones totales
    public void shortestRoute(Route bestRoute){
        System.out.println("Shortest Route: " + bestRoute);
        System.out.println("Iterations: " + getIterations());
    }
}
