package Entities;

import Sim.Ceil;
import Sim.Map;
import java.util.ArrayList;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;

public class Predator extends Creature {

	private int attack;
	
	public Predator(Map map) {
		super(map, (int)(Math.random()*(double)(5))+9, (int)(Math.random()*(double)(4))+7);
		attack = (int)(Math.random()*(double)(3))+3;
		sign = Signs.PREDATOR[(int)(Math.random()*(double)(Signs.PREDATOR.length))];
	}
	
	public Predator(Map map, int hp, int speed, int attack) {
		super(map, hp, speed);
		this.attack = attack;
		sign = Signs.PREDATOR[(int)(Math.random()*(double)(Signs.PREDATOR.length))];
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
	    			if(entities.get(up) instanceof Herbivore) {
	    				Herbivore h = (Herbivore)entities.get(up);
	    				h.setHp(h.getHp()-attack);
	    				finalCeil = up;
	    				break;
	    			}
	    			if(entities.get(up)==null) {
	    				temp.add(up);
	    			}
	    		}
	    		if(entities.containsKey(down) && !visited.contains(down)) {
	    			if(entities.get(down) instanceof Herbivore) {
	    				Herbivore h = (Herbivore)entities.get(down);
	    				h.setHp(h.getHp()-attack);
	    				finalCeil = down;
	    				break;
	    			}
	    			if(entities.get(down)==null) {
	    				temp.add(down);
	    			}
	    		}
	    		if(entities.containsKey(right) && !visited.contains(right)) {
	    			if(entities.get(right) instanceof Herbivore) {
	    				Herbivore h = (Herbivore)entities.get(right);
	    				h.setHp(h.getHp()-attack);
	    				finalCeil = right;
	    				break;
	    			}
	    			if(entities.get(right)==null) {
	    				temp.add(right);
	    			}
	    		}
	    		if(entities.containsKey(left) && !visited.contains(left)) {
	    			if(entities.get(left) instanceof Herbivore) {
	    				Herbivore h = (Herbivore)entities.get(left);
	    				h.setHp(h.getHp()-attack);
	    				finalCeil = left;
	    				break;
	    			}
	    			if(entities.get(left)==null) {
	    				temp.add(left);
	    			}
	    		}	    		
	    	}
	    	if(finalCeil!=null) {
                Herbivore h = (Herbivore)entities.get(finalCeil);
                if(!h.isAlive()) {
                	entities.put(finalCeil, null);
                	this.hp+=5;
                }
	    		
	    		entities.put(current, this);
	    		entities.put(curCeil, null);
	    		return;
	    	}
	    	else {
	    		while(temp.size()>0) {
	    			toVisit.add(temp.poll());
	    		}
	    	}
	    }		
		this.hp-=2;;
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
