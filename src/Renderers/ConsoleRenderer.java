package Renderers;

import Sim.*;
import Entities.*;

public class ConsoleRenderer implements Renderer{
    @Override
    public void render(Map map, int step) {
    	//Service.ConsoleCleaner.cls();
    	System.out.println("Step: "+step);
    	Ceil cur;
    	Entity ent;
    	for(int i = 0; i<map.getX(); i++) {
    		for(int j = 0; j<map.getY(); j++) {
    			cur = new Ceil(i, j);
    			ent = map.getMatrix().get(cur);
    			if(ent == null) System.out.print("_ ");
    			else System.out.print(ent.getSign()+" ");
    		}
    		System.out.println("");
    	}
    	System.out.println("");
    }
}
