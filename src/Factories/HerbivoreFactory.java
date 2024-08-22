package Factories;

import Sim.Map;
import Entities.Herbivore;

public class HerbivoreFactory extends Factory<Herbivore>{
     public Herbivore generate(Map map) {
    	 return new Herbivore(map);
     }
}
