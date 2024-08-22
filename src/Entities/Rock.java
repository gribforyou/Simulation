package Entities;

public class Rock extends Entity {
    public Rock() {
    	sign = Signs.ROCK[(int)(Math.random()*(double)Signs.ROCK.length)];
    }
}
