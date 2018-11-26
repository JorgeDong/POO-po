public class SA {
    private double temp, coefficient, energy, newEnergy;
    private Route bestRoute;
    private long iterations;
    private CityRecorder recorder;

    public SA(){
        this.temp = 100000;
        this.coefficient = 0.0003;
        this.iterations = 0;
    }

    public SA(double temp, double coefficient){
        this.temp = temp;
        this.coefficient = coefficient;
        this.iterations = 0;
    }

    public void setTemp(double temp) { this.temp = temp; }
    public void setIterations(long iterations) { this.iterations = iterations; }
    public void setEnergy(double energy) { this.energy = energy; }
    public void setNewEnergy(double newEnergy) { this.newEnergy = newEnergy; }

    public double getTemp() { return temp; }
    public double getCoefficient() { return coefficient; }
    public long getIterations() { return iterations; }
    public double getEnergy() { return energy; }
    public double getNewEnergy() { return newEnergy; }
    public Route getBestRoute() { return bestRoute; }

    public double checkProbability(double energy, double newEnergy) {
        if (newEnergy < energy) {
            return 1.0;
        }
        return Math.exp((energy - newEnergy) / getTemp());
    }

    public void solveSA(CityRecorder recorder){
        this.recorder = recorder;
        Route currentRoute = new Route(recorder);
        currentRoute.createCities(recorder);
        this.bestRoute = new Route(recorder);
        bestRoute.cloneRoute(currentRoute.getRoute());

        // Mientras que la temperatura no baje de '1'
        while(getTemp() > 1) {
            Route nextRoute = new Route(recorder);   // Inicializamos nuestra ruta a considerar
            nextRoute.cloneRoute(currentRoute.getRoute());

            // Agarramos dos posiciones al azar (de 0 al tamaño de la lista)
            int randomPos_1 = (int) (nextRoute.getRouteSize() * Math.random());
            int randomPos_2 = (int) (nextRoute.getRouteSize() * Math.random());

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
        shortestDistance(getBestRoute());
        shortestRoute(getBestRoute());
    }

    // Imprime la distancia más corta encontrada
    public void shortestDistance(Route bestRoute){
        System.out.printf("Final solution distance: %.2f\n", bestRoute.getDistance());
    }

    // Imprime la ruta mas corta encontrada y las iteraciones totales
    public void shortestRoute(Route bestRoute){
        System.out.println("Shortest Route: \n" + bestRoute);
        System.out.println("Iterations: " + getIterations());
    }
}
