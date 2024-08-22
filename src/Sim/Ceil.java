package Sim;

public class Ceil {
    private int x;
    private int y;
    
    public Ceil(int x, int y) {
    	this.x = x;
    	this.y = y;
    }
    
   	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	@Override
	public boolean equals(Object other) {
		if(other==null || !(other instanceof Ceil)) return false;
		
		Ceil otherCeil = (Ceil)other;		
		return (this.x==otherCeil.getX()&&this.y==otherCeil.getY());
		
	}
	
	@Override
	public int hashCode() {
		return 1000*getX()+getY();
	}
}
