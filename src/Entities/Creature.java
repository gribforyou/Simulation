package Entities;

import Sim.Map;
import Sim.Ceil;
import java.util.Queue;
import java.util.ArrayDeque;

public abstract class Creature extends Entity {
    protected Map map;
    protected int hp;
    protected int speed;
      
    public Creature(Map map, int hp, int speed) {
    	this.map = map;
    	this.hp = hp;
    	this.speed = speed;
    }
  
	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public abstract void makeMove(Ceil curCeil);
	
	public Boolean isAlive() {
		return hp>0;
	}
}
