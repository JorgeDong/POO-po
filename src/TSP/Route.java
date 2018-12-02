package TSP;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.markers.SeriesMarkers;

/*
    Route: Es una clase que guarda una ruta de n ciudades
    Utiliza el recorder para saber el número de ciudades que tiene que guardar

    OJO: Si existe una ciudad base, el tamaño de la ruta no toma en cuenta la base, ya que la ciudad
        base se maneja aparte en el recorder. Esto no afecta en el calculo de getDistance()
        ya que toma en cuenta la ciudad base que da el recorder
 */
public class Route {

    private ArrayList<City> route = new ArrayList<>();
    private double distance = 0;
    private static City baseCity; //Recibo la ciudad base del recorder y lo manejo como variable
                                   //global para toda Route creada

    //Crea una ruta con N espacios PERO vacia (null)
    //En metodo fillCities() lleno la ruta con las ciudades que tengo en el recorder
    public Route(CityRecorder recorder) {
        baseCity = recorder.getBaseCity();
        for (int i = 0; i < recorder.getNumOfCities(); i++) {
            route.add(recorder.getCity(i));
        }
    }

    public ArrayList getRoute() { return route; }
    public City getBaseCity() { return baseCity; }

    public int getSize() {
        return route.size();
    }
    public City getCity(int id) { return route.get(id); }

    public void setCity(int id, City city) {
        this.route.set(id, city);
        this.distance = 0;
    }

    public void cloneRoute(ArrayList route){
        this.route = (ArrayList)route.clone();
        this.distance = 0;
    }

    // Llenar una ruta con ciudades del recorder (ordenamineto al azar)
    public void fillCities(CityRecorder recorder) {
        for (int i=0; i < recorder.getNumOfCities(); i++) {
            setCity(i, recorder.getCity(i));
        }
        Collections.shuffle(route);
    }
public double getDistance(){
        if(getBaseCity() == null){      //NO existe una ciudad base
            if (this.distance == 0) {
                double tempDistance = 0;
                // Un ciclo entre todas las ciudades de la ruta
                for (int cityIndex=0; cityIndex < getSize(); cityIndex++) {
                    //Se elige una ciudad de origen y destino
                    City origin = getCity(cityIndex);
                    City destiny;
                    // Checar si no estamos devuelta en la ciudad origen
                    if(cityIndex+1 < getSize()){
                        destiny = getCity(cityIndex+1);
                    }
                    else{
                        destiny = getCity(0);
                    }
                    // Se van acumulando las distancias

                    tempDistance += origin.distanceToCity(destiny);
                }
                this.distance = tempDistance;
            }
        }
        else{
            if (distance == 0) {       // SI EXISTE una ciudad base...
                City lastCity = getCity(getSize()-1); //Eligimos la ultima ciudad de la ruta (mas abajo porque)
                double tempDistance = 0;
                // Un ciclo entre todas las ciudades de la ruta
                for (int cityIndex=0; cityIndex < getSize(); cityIndex++) {
                    //Se elige una ciudad de origen y destino
                    
                    City origin = getCity(cityIndex);
                    City destiny;
                    
                    if(cityIndex+1 < getSize()){
                        destiny = getCity(cityIndex+1);
                    }
                    else{
                        destiny = this.getBaseCity();
                    }
                    tempDistance += origin.distanceToCity(destiny);
                }

                tempDistance += this.getBaseCity().distanceToCity(getCity(0));
                this.distance = tempDistance;
            }
        }
        return this.distance;
    }
    @Override
    public String toString() {
        if(getBaseCity() == null){      // NO existe una ciudad base
            String output = "";
            for (int i = 0; i < getSize(); i++) {
                output += getCity(i)+" -> ";
            }
            output += ""+getCity(0);
            return output;
        }else{                          // SI existe una ciudad base
            String output = ""+getBaseCity();
            for (int i = 0; i < getSize(); i++) {
                output += " -> "+getCity(i);
            }
            output += " -> "+getBaseCity();
            return output;
        }
    }
    
    public void paint(int minX, int maxX,int minY, int maxY, boolean dots, int sleep){
        
        int[][] data; 
        
        if(this.getBaseCity()!=null){
            data= new int[this.getSize()+2][2];
            data[0][0]=this.getBaseCity().getX();
            data[0][1]=this.getBaseCity().getY();
            for(int i=0; i<this.getSize();i++){
                data[i+1][0]=this.getCity(i).getX();
                data[i+1][1]=this.getCity(i).getY();
            }
            data[this.getSize()+1][0]=this.getBaseCity().getX();
            data[this.getSize()+1][1]=this.getBaseCity().getY();
        }else{
            data= new int[this.getSize()+1][2];
            //System.out.println("asdfasdfasdfasfd");
            for(int i=0; i<this.getSize();i++){
                            //System.out.println(this.getCity(i).getId());

                data[i][0]=this.getCity(i).getX();
                data[i][1]=this.getCity(i).getY();
            }
            data[this.getSize()][0]=this.getCity(0).getX();
            data[this.getSize()][1]=this.getCity(0).getY();
            
        }
        //System.out.println(Arrays.deepToString(data));



        XYChart chart = new XYChartBuilder().title("").xAxisTitle("X").yAxisTitle("Y").build();
        chart.getStyler().setChartPadding(0);
        chart.getStyler().setYAxisTitleVisible(false);
        chart.getStyler().setYAxisTicksVisible(false);
        chart.getStyler().setXAxisTicksVisible(false);
        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setChartFontColor(Color.black);
        chart.getStyler().setToolTipsEnabled(false);
        chart.getStyler().setChartTitleBoxVisible(false);
        chart.getStyler().setMarkerSize(14);
        chart.getStyler().setYAxisMax(new Double(maxY));
        chart.getStyler().setXAxisMax(new Double(maxX));
        chart.getStyler().setYAxisMin(new Double(minY));
        chart.getStyler().setXAxisMin(new Double(minX));
        

        SwingWrapper sw = new SwingWrapper(chart);
        sw.displayChart();
        
        

        if(dots){
            for(int i=0; i<data.length;i++){

            XYSeries s1 =chart.addSeries("yup"+i+i, new int[] {data[i][0]}, new int[] {data[i][1]});


            s1.setMarker(SeriesMarkers.CIRCLE);
            s1.setMarkerColor(Color.black);
            s1.setShowInLegend(true);
            s1.setLineColor(Color.blue);
            s1.setShowInLegend(true);
            s1.setLineWidth(2);
            
            sleep(sleep);
            sw.repaintChart();
        }
            
        }else{
            for(int i=0; i<data.length-1;i++){

            XYSeries s1 =chart.addSeries("yup"+i+i, new int[] {data[i][0],data[i+1][0]}, new int[] {data[i][1],data[i+1][1]});


            s1.setMarker(SeriesMarkers.CIRCLE);
            s1.setMarkerColor(Color.black);
            s1.setShowInLegend(true);
            s1.setLineColor(Color.blue);
            s1.setShowInLegend(true);
            s1.setLineWidth(2);
            
            sleep(sleep);
            sw.repaintChart();
        }
            
        }
        
        
    }
    
    
    public void sleep(int ms){
        try{
            Thread.sleep(ms);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }    
                
    }
}
