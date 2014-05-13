package model;

public class Point {
	public int x;
	public int y;
	
	public Point() {
		x = y = 0;
	}
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean isEqual(Point p) {
		return x == p.x && y == p.y;
	}
	
	public String toString() {
		return "" + x + "," + y; 
	}
}
