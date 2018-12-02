package TSP;


public class NN extends Information implements Algorithm{

    public NN(){
        super(); //Extiende a Information (inicializar mis variables)
    }

    public Route getBestRoute(){ return super.bestRoute; }
    public long getRuntime() { return super.timer; }
    public double getShortestDistance() { return super.shortestDistance;}
    public long getIterations() { return super.iterations; }

    public void solve(CityRecorder recorder){
        long startTime = System.nanoTime();

        Route r2 = new Route(recorder);
        r2.paint(-20,250,-20,250,false,0);
        
        
        //for(int k=0;k<15;k++){
            Route temp = r2;
            
            /*City swayCity_12 = temp.getCity(0);
            City swapCity_22 = temp.getCity(k+1);
                System.out.println("asdf");
            temp.setCity(k+1, swayCity_12);
            temp.setCity(0, swapCity_22);*/
            
            for(int j =0;j<temp.getSize()-1;j++){


                    int pos =j;
                    //System.out.println(temp.getCity(pos).getId());
                    double bestroute=1000000000;
                    int poo=0;
                    City swayCity_1 = temp.getCity(pos+1);
                    City swapCity_2 = null;

                    for(int i=pos+1;i<=temp.getSize()-1;i++){
                        //System.out.println(r2.getCity(pos).getId()+":"+r2.getCity(i).getId()+": "+r2.getCity(pos).distanceToCity(r2.getCity(i)));
                        if(bestroute>temp.getCity(pos).distanceToCity(temp.getCity(i))){
                            //System.out.println("Masdf");
                            bestroute=temp.getCity(pos).distanceToCity(temp.getCity(i));
                            swapCity_2 = temp.getCity(i);
                            poo=i;
                        }
                    }

                    temp.setCity(poo, swayCity_1);
                    temp.setCity(pos+1, swapCity_2);
                    temp.paint(-20,250,-20,250,false,60);
                    sleep(500);
                    
                    bestRoute = new Route(recorder);
                    bestRoute.cloneRoute(temp.getRoute());
                    super.shortestDistance = bestRoute.getDistance();


            }
                                
        //}
        
        long endTime = System.nanoTime();
        runtime(startTime, endTime);
        
        
        
        printShortestDistance();
        printShortestRoute();
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
