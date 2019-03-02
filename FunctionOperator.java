package Tests;

import java.util.Map;

public class FunctionOperator {
	public static String[] Functions = {"sin", "cos", "tan", "csc", "sec", "cot", "arcsin", "arccos", "arctan", "log", "ln", "abs"};
	public static String[] Operators = {"sin", "cos", "tan", "csc", "sec", "cot", "arcsin", "arccos", "arctan", "log", "ln", "+", "-", "*", "/", "^", "abs"};
	public static int[] OperatorPriority = {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 1, 1, 2, 3};

	Map<String, Function> functions_map;
	
	FunctionOperator() {
		for(int i = 0; i < Functions.length; i++) {
			functions_map.put(Functions[i], Function.get(Functions[i]));
		}		
	}
	public static String getFunction(String exp) {
		int end = 0;
		for(int i = 0; i < exp.length(); i++) {
			if(exp.charAt(i) == '(') {
				end = i;
				break;
			}
		}
		if(end == 0) {
			return "";
		}
		for(int j = 0; j < Functions.length; j++) {
			if(exp.startsWith(Functions[j])){
				return Functions[j];
			}
		}
		return "";
	}
	
	public boolean isFunction(String exp, int position) {
		String part = exp.substring(position).toLowerCase();
		int end = 0;
		for(int i = 0; i < part.length(); i++) {
			if(exp.charAt(i) == '(') {
				end = i;
				break;
			}
		}
		if (end == 0) {
			return false;
		}
		part = part.substring(0, end);
		for(int i = 0; i < Functions.length; i++) {
			if(functions_map.containsKey(part)){
				return true;
			}
		}
		return false;
	}
	public static boolean isOperator(String exp) {
		for(int i = 0; i < Operators.length; i++) {
			if(exp.equals(Operators[i])) {
				return true;
			}
		}
		return false;
	}
	
	public static int getFunctionIndex(String exp) {
		int index = 0;
		for(int i = 0; i < Operators.length; i++) {
			if(Operators[i].equals(exp)) {
				index = i;
			}
		}
		return index;
	}
	public static int getPriority(String exp) {
		return OperatorPriority[getFunctionIndex(exp)];
	}
	public static double Operate(double number, String operate) {
		int operator = getFunctionIndex(operate);
		System.out.println(operator);
		switch(operator) {
			case 0: 	return Math.sin(number);
		
			case 1: 	return Math.cos(number);
			
			case 2: 	return Math.tan(number);
			
			case 3: 	return 1 / (Math.sin(number));
			
			case 4: 	return 1 / (Math.cos(number));
			
			case 5: 	return 1 / (Math.tan(number));
			
			case 6: 	return Math.asin(number);
			
			case 7: 	return Math.acos(number);
			
			case 8: 	return Math.atan(number);
			
			case 9: 	return Math.log10(number);
			
			case 10:	return Math.log(number);
			
			case 11:	return Math.abs(number);
		}
		return -1;
	}
}
