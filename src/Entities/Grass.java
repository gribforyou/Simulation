package Entities;

public class Grass extends Entity {
    public Grass() {
    	sign = Signs.GRASS[(int)(Math.random()*(double)Signs.GRASS.length)];
    }
}
