package Sim;

import Factories.*;
import Renderers.*;

public class Simulation implements Runnable{
    private Map map;
    private int steps;
    Thread th;
    boolean isActive;
    Renderer renderer;
    
    public Simulation() {
    	isActive = false;
    	renderer = new ConsoleRenderer();
    	map = new Map(30, 50);
    	map.generate(new RockFactory(), 15);
    	map.generate(new GrassFactory(), 35);
    	map.generate(new TreeFactory(), 15);
    	map.generate(new HerbivoreFactory(), 20);
    	map.generate(new PredatorFactory(), 15);
    	steps = 0;
    	th = new Thread(this);
    }
    
    public void run() {
    	renderer.render(map, steps);
    	while(isActive){
    		//Service.ConsoleCleaner.cls();
    		map.makeMove(++steps, renderer);
    		
    		if(map.grassCount()<map.getX()*map.getY()/40) {
    			map.generate(new GrassFactory(), 5);
    		}
    		
    		try {
    			Thread.sleep(3000);
    		}
    		catch(InterruptedException ex) {
    			System.out.println("Sleep error");
    		}
    		if(map.predatorCount()<=0) {
    			System.out.println("End of simulation");
    			isActive = false;
    		}
    	}
    }
    
    public void startSimulation() {
    	isActive = true;
    	th.start();
    }
    
    public void pauseSimulation() {
    	isActive = false;
    }
}
