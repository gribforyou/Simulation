package Sim;

public class ConsoleCleaner {
	public static void cls() {
    	try {
    		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    	}
    	catch(Exception e) {
    		System.out.println("cleaning error");
    	}
    }
}
