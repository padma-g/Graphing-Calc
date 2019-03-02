package Tests;

import java.util.*;

public class PostFixEvaluator {
	ArrayList<String> PostFixExp;
	
	public PostFixEvaluator(ArrayList<String> exp) {
		PostFixExp = exp;
	}
	public double eval(double xvalue) {
		Stack<Double> result = new Stack<Double>();
		
		for(int i = 0; i < PostFixExp.size(); i++) {
			if(PostFixExp.get(i).equals("x")) {
				result.push(xvalue);
			} 
			else if(PostFixExp.get(i).equals("e")) {
				result.push(Math.E);
			}
			else if(PostFixExp.get(i).equals("pi")) {
				result.push(Math.PI); 
			}
			else if(Character.isDigit(PostFixExp.get(i).charAt(0))) {
				result.push(Double.parseDouble(PostFixExp.get(i)));
			} 
			else {
				Function f = Function.get(PostFixExp.get(i));
				if(f != null) {
					result.push(f.Evaluate(result.pop()));
				}
				else {
					if(PostFixExp.get(i).length() != 1) {
						System.out.println("ERROR OPERATOR INVALID: " + PostFixExp.get(i));
						System.exit(-1);
					}
 					if(result.empty()) {
 						System.out.println("ERROR RESULT STACK EMPTY");
 						System.exit(-1);
 					}
 					double term2 = result.pop();
 					if(result.empty()) {
 						System.out.println("ERROR RESULT STACK DOESN'T HAVE SECOND RESULT");
 						System.exit(-1);
 					}
					double term1 = result.pop();
					switch(PostFixExp.get(i).charAt(0)) {
						case '+': 
							result.push(term1 + term2); 
							break;
						case '-': 
							result.push(term1 - term2); 
							break;
						case '*': 
							result.push(term1 * term2); 
							break;
						case '/': 
							result.push(term1 / term2); 
							break;
						case '^': 
							result.push(Math.pow(term1, term2)); 
							break;
						default:
							System.out.println("INVALID OPERATOR: " + PostFixExp.get(i));
 							System.exit(-1);
							
					}
					
				}
			}
		}
		if(result.empty()) {
			System.out.println("ERROR END RESULT STACK EMPTY");
			System.exit(-1);
		}
		double expvalue = result.pop();
		if(!result.empty()) {
			System.out.println("ERROR MORE THAN ONE RESULT IN STACK");
			System.exit(-1);
		}
		return expvalue;
	}
	//public double derivative(double xvalue) {
		
	//}
	public static void main(String [] args) {
		String[] expected_array = { "2", "x", "2", "^", "*", "5", "x", "*", "+"};
		ArrayList<String> postfixexp = new ArrayList(Arrays.asList(expected_array));
		PostFixEvaluator evaluator = new PostFixEvaluator(postfixexp);
		Double[] expected = {0.0, 1.375, 3.0, 4.875, 7.0, 9.375, 12.0, 14.875, 18.0, 21.375, 25.0, 28.875, 33.0, 37.375, 42.0, 46.875, 52.0};
		ArrayList<Double> results = new ArrayList<Double>();
		for(double i = 0; i <= 4; i+=0.25) {
			System.out.println(evaluator.eval(i));
			results.add(evaluator.eval(i));
		}
		if(!TestUtils.CompareArray(results, expected)) {
			System.out.println("Error values do not match");
		}
	}
	
}
