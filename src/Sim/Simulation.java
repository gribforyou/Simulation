package Sim;

import Factories.*;

public class Simulation extends Thread implements Runnable{
    private Map map;
    private int steps;
    Thread th;
    boolean isActive;
    
    public Simulation() {
    	super();
    	isActive = false;
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
    	map.render(0);
    	while(isActive) {
    		ConsoleCleaner.cls();
    		map.makeMove(++steps);
    		try {
    			Thread.sleep(10000);
    		}
    		catch(InterruptedException ex) {
    			System.out.println("Sleep error");
    		}
    	}
    }
    
    public void startSimulation() {
    	isActive = true;
    	this.start();
    }
    
    public void pauseSimulation() {
    	isActive = false;
    }
}
