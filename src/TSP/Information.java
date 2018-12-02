package TSP;

/*
    Information: guarda todas las variables necesarias y que tiene que manejar
    cualquier algoritmo que resuelva TSP.

 */
public abstract class Information {
    protected long iterations;          //Numero de casos que calcula
    protected CityRecorder recorder;    // Cual caso estamos resolviendo
    protected Route bestRoute;          // Mi mejor ruta (la mas corta)
    protected double shortestDistance;  // La distancia mas corta encontrada
    protected long timer;               // Tiempo de ejecución

    protected Information(){
        this.iterations = 0;
        this.recorder = null;
        this.bestRoute = null;
        this.shortestDistance = -1;
        this.timer = 0;
    }
}
