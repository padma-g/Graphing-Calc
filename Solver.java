package Tests;

import java.util.ArrayList;

public class Solver {
	static ArrayList<Point> discontinuities = new ArrayList<Point>();
	static ArrayList<Double> vertical_asymptotes = new ArrayList<Double>();
	double xMin = GrapherFrame.xmin;
	double xMax = GrapherFrame.xmax;
	double yMin = GrapherFrame.ymin;
	double yMax = GrapherFrame.ymax;
	public Solver() {
		
	}
		
	public ArrayList<Point> solve(ArrayList<Point> function) {
		ArrayList<Point> zeros = new ArrayList<Point>();
		ArrayList<Point> leftBounds = new ArrayList<Point>();
		ArrayList<Point> rightBounds = new ArrayList<Point>();
		for (int i = 0; i < function.size()-1; i++) {
			if (  ((function.get(i).y > 0) && (function.get(i+1).y < 0)) ||
					((function.get(i).y < 0) && (function.get(i+1).y > 0))  ) {
				
				leftBounds.add(function.get(i));
				rightBounds.add(function.get(i+1));
			}
			
					     
			
		}
		
		
		for (int i = 0; i < leftBounds.size(); i++) {
			while (rightBounds.get(i).x - leftBounds.get(i).x < 0.00001) {
				Double m = (rightBounds.get(i).x + leftBounds.get(i).x)/2;
				Double ym = Poly.Evaluate(m);
				if ( (ym > 0 && leftBounds.get(i).x < 0) || 
						(ym < 0 && leftBounds.get(i).x > 0)) {
					rightBounds.get(i).x = m;
				}
				else {
					leftBounds.get(i).x = m;
				}
			}
			Point zero = new Point(Poly.Round((rightBounds.get(i).x + leftBounds.get(i).x)/2, 3), 0);
			zeros.add(zero);
		}
		
		
		
	
		
		
		
		
		
		return zeros;
		
		
	}
	
	
	public ArrayList<Point> findRelativeExtrema(ArrayList<Point> zeros) {
		ArrayList<Point> relativeExtrema = new ArrayList<Point>();
		for (Point p : zeros) {
			Point r = new Point(p.x, Poly.Evaluate(p.x));
			relativeExtrema.add(r);
		}
		
		return relativeExtrema;
	}
	
	public ArrayList<Point> findInflectionPoints(ArrayList<Point> zeros) {
		ArrayList<Point> inflectionPoints = new ArrayList<Point>();
		
		for (Point p : zeros) {
			Point r = new Point(p.x, Poly.Evaluate(p.x));
			inflectionPoints.add(r);
		}
		
		for (Point p : GrapherFrame.secondDerivative) {
			if (Double.isNaN(p.y)) {
				inflectionPoints.add(p);
			}
		}
		
		return inflectionPoints;
		
	}
	
	public void findGaps(ArrayList<Point> function) {
		Double interval = 1.0/4096;
		PostFixEvaluator postfix_evaluator = Poly.postfix_evaluator;
		for(double x = xMin; x < xMax; x+=interval) {
			if(Double.isNaN(postfix_evaluator.eval(x))) {
				if(Math.abs(postfix_evaluator.eval(x-1) - postfix_evaluator.eval(x+1)) < interval && postfix_evaluator.eval(x-1) < 2147483647) {	
					Point q = new Point(x, postfix_evaluator.eval(x-1));
					discontinuities.add(q);
				}	
				else {
					vertical_asymptotes.add(x);
				}
			}
		}

	}
	
}
