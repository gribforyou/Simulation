package Sim;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Simulation sim = new Simulation();
		sim.startSimulation();
		
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		if(s != null) {
			sim.pauseSimulation();
		}
		
	}

}
