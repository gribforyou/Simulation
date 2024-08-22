package Factories;

import Sim.Map;
import Entities.Predator;

public class PredatorFactory extends Factory<Predator>{
    public Predator generate(Map map) {
    	return new Predator(map);
    }
}
