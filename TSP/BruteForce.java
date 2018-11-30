package TSP;

public class BruteForce {
    protected long iterations;
    protected CityRecorder recorder;
    protected Route bestRoute;
    protected double shortestDistance;
    protected long timer;

    public BruteForce(){
        this.iterations = 0;
        this.shortestDistance = -1;
        this.timer = 0;
    }

    public void solve(CityRecorder recorder){
        long startTime = System.nanoTime();

        this.recorder = recorder;
        Route currentRoute = new Route(recorder);
        for(int a = 0; a < recorder.getNumOfCities(); a++){
            currentRoute.setCity(a, recorder.getCity(a));
        }

        bestRoute = new Route(recorder);
        bestRoute.cloneRoute(currentRoute.getRoute());

        permute(currentRoute, 0);
        long endTime = System.nanoTime();
        this.timer = (endTime - startTime)/1000000; // milis: /1000000

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

    public void printShortestDistance(){
        System.out.printf("Final solution distance: %.2f (Time: %d mls.)\n", this.shortestDistance, this.timer);
    }
    public void printShortestRoute(){
        System.out.println("Shortest Route: \n" + this.bestRoute);
    }

}
