package Factories;

import Sim.Map;
import Entities.Grass;

public class GrassFactory extends Factory<Grass>{
    public Grass generate(Map map) {
    	return new Grass();
    }
}
