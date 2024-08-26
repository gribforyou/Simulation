package Sim;

import Entities.*;
import Factories.*;
import java.util.HashMap;
import java.util.ArrayList;
import Renderers.*;

public class Map {
    private HashMap<Ceil, Entity> matrix;
    private int x;
    private int y;
    
    public HashMap<Ceil, Entity> getMatrix(){
    	return matrix;
    }
    
    public int getX() {
    	return x;
    }
   
    public int getY() {
    	return y;
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
    
    public boolean hasCreature() {
    	for(int i = 0; i<x; i++) {
    		for(int j = 0; j<y; j++) {
    			if(matrix.get(new Ceil(i, j)) instanceof Creature)
    				return true;
    		}
    	}
    	return false;
    }
    
    public int grassCount() {
    	int result = 0;
    	for(int i = 0; i<x; i++) {
    		for(int j = 0; j<y; j++) {
    			if(matrix.get(new Ceil(i, j)) instanceof Grass) {
    				result++;
    			}
    		}
    	}
    	return result;
    }
    
    public int predatorCount() {
    	int result = 0;
    	for(int i = 0; i<x; i++) {
    		for(int j = 0; j<y; j++) {
    			if(matrix.get(new Ceil(i, j)) instanceof Predator) {
    				result++;
    			}
    		}
    	}
    	return result;
    }
    
    public void makeMove(int step, Renderer renderer) {
    	ArrayList<Creature> creatures = new ArrayList<Creature>();
    	Creature temp;
    	Ceil tempCeil;
    	for(int i=0; i<x; i++) {
    		for(int j = 0; j<y; j++) {
    			tempCeil = new Ceil(i, j);
    			if(matrix.get(tempCeil) instanceof Creature) {
    				temp = (Creature)matrix.get(tempCeil);
    				creatures.add(temp);
    			}
    		}
    	}
    	int n = creatures.size();
    	for(int i = 0; i<n; i++) {
    		for(int k=0; k<x; k++) {
        		for(int j = 0; j<y; j++) {
        			tempCeil = new Ceil(k, j);
        			if(matrix.get(tempCeil) == creatures.get(i)) {
        				creatures.get(i).makeMove(tempCeil);
//        				renderer.render(this, step);
//        				try {
//        				   Thread.sleep(1000);
//        				}
//        				catch(Exception e) {}
        				j = y;
        				k = x;
        			}
        		}
        	}
    	}
    	renderer.render(this, step);
    }
}
