package Sim;

import Entities.*;
import Factories.*;
import java.util.HashMap;

public class Map {
    private HashMap<Ceil, Entity> matrix;
    private int x;
    private int y;
    
    public HashMap<Ceil, Entity> getMatrix(){
    	return matrix;
    }
    
    public Map(int x, int y) {
    	this.x = x;
    	this.y = y;
    	matrix = new HashMap<Ceil, Entity>();
    	for(int i = 0; i<x; i++) {
    		for(int j = 0; j<y; j++) {
    			matrix.put(new Ceil(i, j), null);
    		}
    	}
    }
    
    public void render(int step) {
    	//System.out.print("\033c");
    	System.out.println("Step: "+step);
    	Ceil cur;
    	Entity ent;
    	for(int i = 0; i<x; i++) {
    		for(int j = 0; j<y; j++) {
    			cur = new Ceil(i, j);
    			ent = matrix.get(cur);
    			if(ent == null) System.out.print("_ ");
    			else System.out.print(ent.getSign()+" ");
    		}
    		System.out.println("");
    	}
    	System.out.println("");
    }
    
    public void makeMove(int step) {
    	
    	matrix.forEach(((cell, entity) -> {
            if (entity != null && entity instanceof Creature) {
                ((Creature) entity).makeMove(cell);
                
            }
        }));
    	render(step);
    	generate(new GrassFactory(), 7);
    }

    public void generate(Factory factory, int n) {
    	int tempX, tempY;
        for(int i = 0; i<n; i++) {
        	do {
        	tempX = (int)(Math.random()*(double)(x));
        	tempY = (int)(Math.random()*(double)(y));
        	} while(matrix.get(new Ceil(tempX, tempY))!=null);
        	matrix.put(new Ceil(tempX, tempY), factory.generate(this));
        }
    }
}
