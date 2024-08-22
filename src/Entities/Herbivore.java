package Entities;

import Sim.Ceil;
import Sim.Map;
import Entities.Grass;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.HashMap;


public class Herbivore extends Creature {

	public Herbivore(Map map) {
		super(map, (int)(Math.random()*(double)(5))+9, (int)(Math.random()*(double)(4))+6);
		sign = Signs.HERBIVORE[(int)(Math.random()*(double)(Signs.HERBIVORE.length))];
	}
	
	public Herbivore(Map map, int hp, int speed) {
		super(map, hp, speed);
		sign = Signs.HERBIVORE[(int)(Math.random()*(double)(Signs.HERBIVORE.length))];
	}
	
	@Override
	public void makeMove(Ceil curCeil) {	
		HashMap<Ceil, Entity> entities = map.getMatrix();
		HashSet<Ceil> visited = new HashSet<Ceil>();
		ArrayDeque<Ceil> toVisit = new ArrayDeque<Ceil>();
		ArrayDeque<Ceil> temp = new ArrayDeque<Ceil>();
	    toVisit.add(curCeil);
	    Ceil current = null, up, down, left, right, finalCeil = null;
	    
	    for(int i = 0; i<speed; i++) {
	    	while(toVisit.size()>0) {
	    		current = toVisit.poll();
	    		visited.add(current);
	    		up = new Ceil(current.getX(), current.getY()-1);
	    		down = new Ceil(current.getX(), current.getY()+1);
	    		left = new Ceil(current.getX()-1, current.getY());
	    		right = new Ceil(current.getX()+1, current.getY());
	    		if(entities.containsKey(up) && !visited.contains(up)) {
	    			if(entities.get(up) instanceof Grass) {
	    				finalCeil = up;
	    				break;
	    			}
	    			if(entities.get(up)==null) {
	    				temp.add(up);
	    			}
	    		}
	    		if(entities.containsKey(down) && !visited.contains(down)) {
	    			if(entities.get(down) instanceof Grass) {
	    				finalCeil = down;
	    				break;
	    			}
	    			if(entities.get(down)==null) {
	    				temp.add(down);
	    			}
	    		}
	    		if(entities.containsKey(right) && !visited.contains(right)) {
	    			if(entities.get(right) instanceof Grass) {
	    				finalCeil = right;
	    				break;
	    			}
	    			if(entities.get(right)==null) {
	    				temp.add(right);
	    			}
	    		}
	    		if(entities.containsKey(left) && !visited.contains(left)) {
	    			if(entities.get(left) instanceof Grass) {
	    				finalCeil = left;
	    				break;
	    			}
	    			if(entities.get(left)==null) {
	    				temp.add(left);
	    			}
	    		}	    		
	    	}
	    	if(finalCeil!=null) {
	    		entities.put(finalCeil, null);
	    		entities.put(current, this);
	    		entities.put(curCeil, null);
	    		this.hp+=3;
	    		return;
	    	}
	    	else {
	    		while(temp.size()>0) {
	    			toVisit.add(temp.poll());
	    		}
	    	}
	    }		
		this.hp--;
		if(!isAlive()) {
			entities.put(curCeil, null);
		}
		else {
			int n = (int)(Math.random()*(double)(visited.size()));
			finalCeil = (Ceil)visited.toArray()[n];
			entities.put(finalCeil, this);
			entities.put(curCeil, null);
		}
	}

}
