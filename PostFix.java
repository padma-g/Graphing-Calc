package Tests;

import java.util.Stack;
import java.util.ArrayList;

public class PostFix {
	ArrayList<String> PostExp = new ArrayList<String>();
	
	public PostFix(ArrayList<String> Exp) {
		Stack<String> operators = new Stack<String>();
		
		for(int i = 0; i < Exp.size(); i++) {
			
			if(Exp.get(i).equals(")")) {
				while(!operators.empty() && !operators.peek().equals("(")) {
					PostExp.add(operators.pop());
				}
				if (!operators.empty()) {
					operators.pop();
				}
				if(!operators.empty() && !FunctionOperator.getFunction(operators.peek()).equals("")) {
					PostExp.add(operators.pop());
				}
			} else if(Character.isDigit(Exp.get(i).charAt(0))) {
				PostExp.add(Exp.get(i));
			} else if(FunctionOperator.isOperator(Exp.get(i))) {
				while ((!operators.empty()) && !operators.peek().equals("(") && (FunctionOperator.getPriority(Exp.get(i)) <= FunctionOperator.getPriority(operators.peek()))) {
					PostExp.add(operators.pop());
				}
				operators.push(Exp.get(i));
			} else if(Exp.get(i).equals("(")) {
				operators.push(Exp.get(i));
			} else {
				PostExp.add(Exp.get(i));
			}
 		}
		while(!operators.empty()) {
			PostExp.add(operators.pop());
		}
	}
	public ArrayList<String> getPostExp() {
		return PostExp;
	}
	public static void main(String [] args) {
		{
			String exp = "2x^2 + 5x";
			Tokenizer t = new Tokenizer(exp);
			Tokenizer.PrintArray(t.getTokens());
			PostFix p = new PostFix(t.getTokens());
			
			String[] expected_array = { "2", "x", "2", "^", "*", "5", "x", "*", "+"};
			if (!TestUtils.CompareArray(p.getPostExp(), expected_array)) {
				System.out.println("ERROR:: PostFix failed for: " + exp);
				Tokenizer.PrintArray(p.getPostExp());				
			}
		}

		{
			String exp = "5(x+6) ^ (x+7) sin(x+8)";
			Tokenizer t = new Tokenizer(exp);
			Tokenizer.PrintArray(t.getTokens());
			PostFix p = new PostFix(t.getTokens());
			Tokenizer.PrintArray(p.getPostExp());

			String[] expected_array = { "5", "x", "6", "+", "x", "7", "+", "^", "*", "x", "8", "+", "sin", "*"};
			if (!TestUtils.CompareArray(p.getPostExp(), expected_array)) {
				System.out.println("ERROR:: PostFix failed for: " + exp);
				Tokenizer.PrintArray(p.getPostExp());				
			}
		}

		{
			String exp = "5(x+5(x+5(x+5(x+5(5sin(x))))))";
			Tokenizer t = new Tokenizer(exp);
			Tokenizer.PrintArray(t.getTokens());
			PostFix p = new PostFix(t.getTokens());
			Tokenizer.PrintArray(p.getPostExp());

			String[] expected_array = {"5", "x", "5", "x", "5", "x", "5", "x", "5", "5", "x", "sin", "*", "*", "+", "*", "+", "*", "+", "*", "+", "*"};
			if (!TestUtils.CompareArray(p.getPostExp(), expected_array)) {
				System.out.println("ERROR:: PostFix failed for: " + exp);
				Tokenizer.PrintArray(p.getPostExp());				
			}
		}
	}
}
