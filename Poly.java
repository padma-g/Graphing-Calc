

package Tests;

import java.util.*;

public class Poly {

final static double error = 0.01;
final static int DerivDecimal = 13;
	String expression;
	Tokenizer tokenizer;
	PostFix postfix_expression;
	static PostFixEvaluator postfix_evaluator;
	
	
	public Poly(String expression) {
		this.expression = expression;
		tokenizer = new Tokenizer(expression);
		postfix_expression = new PostFix(tokenizer.getTokens());
		postfix_evaluator = new PostFixEvaluator(postfix_expression.getPostExp());
	}	
	
	public ArrayList<Point> Evaluate(ArrayList<Double> x) {
		ArrayList<Point> points = new ArrayList<Point>();
		for(int i = 0; i < x.size(); i++) {
			Point p = new Point(x.get(i), postfix_evaluator.eval(x.get(i)));
			points.add(p);
		}
		return points;
	}
	
//	public ArrayList<Point> Evaluate(double start, double end, double interval) {
//		ArrayList<Point> points = new ArrayList<Point>();
//		for(double x = start; x <= end; x+=interval) {
//			Point p = new Point(x, postfix_evaluator.eval(x));
//			//System.out.println("Point: (" + p.X() + ", " + p.Y() + ")");
//			points.add(p);
//		}
//		return points;
//	}
	
	public static double Evaluate(double x) {
		double y = postfix_evaluator.eval(x);
		return y;
	}
	public static ArrayList<Point> Evaluate(double start, double end, double interval) {
		ArrayList<Point> points = new ArrayList<Point>();
		for(double x = start; x < end; x+=interval) {
			Point p = new Point(x, postfix_evaluator.eval(x));
			//System.out.println("Point: (" + p.X() + ", " + p.Y() + ")");
			points.add(p);
			
			
				
		}
		return points;
	}
	
	public static double takeDerivative(double x) {
		double derivative = ((Evaluate(x + error) - Evaluate(x) )/error);
		return derivative;
	}
	public static double takeSecondDerivative(double x) {
		Double y0 = takeDerivative(x); 
		Double y = takeDerivative(x+error);
		Double derivative2 = (y-y0)/error;
		
//		double derivative2 = Round( ( ( Evaluate(x+error) - 2*Evaluate(x) + Evaluate(x-error) )/(error*error) ) , DerivDecimal );
//		double derivative2 = Round((((Evaluate(x+error)-Evaluate(x))/error) - ((Evaluate(x) - (Evaluate(x-error))/error)))/error, DerivDecimal);
//		double derivative2 = Round(((takeDerivative(x + error) - takeDerivative(x) )/error), DerivDecimal);
		return derivative2;
	}
	


public static double Round(double num, int decimalplace) {
		decimalplace = (int)Math.pow(10, decimalplace);
		num = (double) Math.round(num * decimalplace) / decimalplace;
		return num;
	}


	
	public static void main(String [] args) {
		// Simple test case
		{
			String exp = "2x^2 + 5x";
			Poly polynomial = new Poly(exp);
			ArrayList<Point> points = polynomial.Evaluate(0, 4, 0.25);
			Double[] yexpected = { 0.0, 1.375, 3.0, 4.875, 7.0, 9.375, 12.0, 14.875, 18.0,
								   21.375, 25.0, 28.875, 33.0, 37.375, 42.0, 46.875
								  };
			
			ArrayList<Point> expected = new ArrayList<Point>();
			int i = 0;
			for(double x = 0; x < 4; x+=0.25) {
				expected.add(new Point(x, yexpected[i++]));
			}
			if (TestUtils.ComparePoints(points, expected)) {
				System.out.println("Success: results compare fine for " + exp);
			}

		}

		// Test case with a trigonometric function
		{
			String exp = "5(x+5(x+5(x+5(x+5(5sin(x))))))";
			Poly polynomial = new Poly(exp);
			ArrayList<Point> points = polynomial.Evaluate(0, 4, 0.25);
			// Results computed using spreadsheet
			Double[] yexpected = {
					0.0, 4060.68686335192, 7881.02404069067, 11235.6056253646, 13927.9841376234, 15802.884677431,
					16755.8591656884, 16739.7804199053, 15767.7722941513, 13912.3937013738, 11301.1272516243,
					8108.45300081768, 4545.00012593542, 844.451022967056, -2750.98793265031, -6005.64560534912
			};
			
			ArrayList<Point> expected = new ArrayList<Point>();
			int i = 0;
			for(double x = 0; x < 4; x+=0.25) {
				expected.add(new Point(x, yexpected[i++]));
			}
			if (TestUtils.ComparePoints(points, expected)) {
				System.out.println("Success: results compare fine for " + exp);
			}
		}

		// Test case with trigonometric and log functions
		{
			String exp = "5(x+5(x+5(x+5(x+5(5sin(x) + log(x+1) + ln(x+1) + tan(x+1) )))))";
			Poly polynomial = new Poly(exp);
			ArrayList<Point> points = polynomial.Evaluate(0, 4, 0.25);
			// Results computed using spreadsheet
			Double[] yexpected = {
					4866.89913954657,
					14465.7594826804,
					53765.3250229893,
					-3507.2883930342,
					10206.5382406309,
					15566.9006715048,
					18528.3855510244,
					19983.5795168547,
					20246.4816701309,
					19535.4323977296,
					18086.8042922626,
					16209.5091221349,
					14376.7989846774,
					13599.5039631067,
					18482.2057082959,
					-82069.9456157516,
			};
			
			ArrayList<Point> expected = new ArrayList<Point>();
			int i = 0;
			for(double x = 0; x < 4; x+=0.25) {
				expected.add(new Point(x, yexpected[i++]));
			}
			if (TestUtils.ComparePoints(points, expected)) {
				System.out.println("Success: results compare fine for " + exp);
			}
		}
		
		
		
		// Test case with trigonometric and log functions
		{
			String exp = "5(x+5(x+5(x+5(x+5(5log(tan(sin(cos(x)))) + log(x+1) + ln(x+1) + tan(x+1) )))))";
			Poly polynomial = new Poly(exp);
			ArrayList<Point> points = polynomial.Evaluate(0, 4, 0.25);
			// Results computed using spreadsheet
			Double[] yexpected = {
					5629.5029726820,
					11128.1675524487,
					46054.3763656741,
					-15764.256789174,
					-6813.96645705956,
					-6983.20265392995,
					-15026.0730640745,
					0.0,
					0.0,
					0.0,
					0.0,
					0.0,
					0.0,
					0.0,
					0.0,
					0.0,
					0.0,
			};
			
			ArrayList<Point> expected = new ArrayList<Point>();
			int i = 0;
			for(double x = 0; x < 4; x+=0.25) {
				expected.add(new Point(x, yexpected[i++]));
			}
			if (TestUtils.ComparePoints(points, expected)) {
				System.out.println("Success: results compare fine for " + exp);
			}
		}
		
	}
}
