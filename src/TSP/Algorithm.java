package TSP;

/*
    Plantilla que todo algoritmo tiene que tener

 */
public interface Algorithm{
    Route getBestRoute();
    long getRuntime();
    double getShortestDistance();
    long getIterations();

    void solve(CityRecorder recorder); //Siempre recibe el conjunto de ciudades a resolver (recorder)
    void runtime(long start, long end);

    void printShortestDistance();
    void printShortestRoute();
}
