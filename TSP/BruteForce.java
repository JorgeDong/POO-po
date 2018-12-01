package TSP;

public class BruteForce extends Information implements Algorithm{

    public BruteForce(){
        super(); //Extiende a Information (inicializar mis variables)
    }

    public Route getBestRoute(){ return super.bestRoute; }
    public long getRuntime() { return super.timer; }
    public double getShortestDistance() { return super.shortestDistance;}
    public long getIterations() { return super.iterations; }

    public void solve(CityRecorder recorder){
        long startTime = System.nanoTime();

        this.recorder = recorder;
        Route currentRoute = new Route(recorder);
        for(int a = 0; a < recorder.getNumOfCities(); a++){
            currentRoute.setCity(a, recorder.getCity(a));
        }

        bestRoute = new Route(recorder);
        bestRoute.cloneRoute(currentRoute.getRoute());

        permute(currentRoute, 0); //Recursivo
        long endTime = System.nanoTime();

        runtime(startTime, endTime);

        printShortestDistance();
        printShortestRoute();
    }

    private void permute(Route route, int index){
        if(index >= route.getSize() - 1){ //Si estamos en el ultimo elemento
            //System.out.print(arr);
            //System.out.printf(" - %.2f\n", arr.getDistance());
            if(route.getDistance() < bestRoute.getDistance()){
                shortestDistance = route.getDistance();
                bestRoute.cloneRoute(route.getRoute());
            }
            super.iterations++;
            return;
        }

        for(int i = index; i < route.getSize(); i++){ //Por cada index en la ruta [index...end]

            //Intercambiar ciudades
            City swayCity_1 = route.getCity(index);
            City swapCity_2 = route.getCity(i);
            route.setCity(i, swayCity_1);
            route.setCity(index, swapCity_2);

            //Recursividad route -> index+1...end
            permute(route, index+1);

            //Acomodar como estaban
            swayCity_1 = route.getCity(index);
            swapCity_2 = route.getCity(i);
            route.setCity(i, swayCity_1);
            route.setCity(index, swapCity_2);
        }
    }

    public void runtime(long start, long end){
        super.timer = (end - start)/1000000;
    }

    public void printShortestDistance(){
        System.out.printf("Final solution distance: %.2f (Time: %d mls.)\n", getShortestDistance(), getRuntime());
    }
    public void printShortestRoute(){
        System.out.println("Shortest Route: \n" + getBestRoute());
        System.out.println("Iterations: " + getIterations());
    }

}
