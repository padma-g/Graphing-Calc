package Tests;

public class Point {
	double x;
	double y;
	public Point(double xvalue, double yvalue) {
		x = xvalue;
		y = yvalue;
	}
	

	public double X() {
		return x;
	}
	
	public double Y() {
		return y;
	}
	
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
