package Factories;

import Entities.*;
import Sim.Map;

public abstract class Factory <T extends Entity>{
   public Factory() {}
   public abstract T generate(Map map);
}
