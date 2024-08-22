package Factories;

import Entities.*;
import Sim.Map;

public class RockFactory extends Factory<Rock> {
     public Rock generate(Map map) {
    	 return new Rock();
     }
}
