package Factories;

import Sim.Map;
import Entities.Tree;

public class TreeFactory extends Factory<Tree>{
    public Tree generate(Map map) {
    	return new Tree();
    }
}
