package Entities;

public class Tree extends Entity {
    public Tree() {
    	sign = Signs.TREE[(int)(Math.random()*(double)Signs.TREE.length)];
    }
}
