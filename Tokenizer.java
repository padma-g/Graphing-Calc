package Tests;

import java.util.ArrayList;

public class Tokenizer {
	ArrayList<String> Tokens = new ArrayList<String>();
	
	public Tokenizer(String exp) {
		for(int i = 0; i < exp.length(); i++) {
			String function = FunctionOperator.getFunction(exp.substring(i));
			if(!function.equals("")) {
				Tokens.add(function);
				i += function.length() - 1;
			}
			else if(exp.substring(i).startsWith(" ")) {
				continue;
			}
			else {
				if(Character.isDigit(exp.charAt(i))) {
					String number = getNumber(exp, i);
					Tokens.add(number);
					i += number.length() - 1;
					if((i != exp.length() - 1) && (exp.charAt(i+1) >= 'a' && exp.charAt(i+1) <= 'z') ) {
						Tokens.add("*");
					}
				}
				else {
					Tokens.add(exp.substring(i, i + 1));
				}
				//System.out.println("index: " + i);
				//System.out.println("character: " + exp.substring(i, i + 1));
			}
		}
		//PrintArray(Tokens);
		Format();
	}
	public static String getNumber(String exp, int start) {
		int end = 0;
		for(int i = start; i < exp.length(); i++) {
			if(Character.isDigit(exp.charAt(i)) || exp.charAt(i) == '.') {
				continue;
			}
			else {
				end = i;
				break;
			}
		}
		if (end == 0) {
			end = exp.length();
		}
		return exp.substring(start, end);
	}
	public ArrayList<String> getTokens() {
		return Tokens;
	}
	private void Format() {
		for(int i = 0; i < Tokens.size() - 1; i++) {
			String term = Tokens.get(i);
			String term2 = Tokens.get(i+1);
			//System.out.println("before if: " + term + " " + term2);
			//2*x(x) = 2*x*(x)
			if(((Character.isDigit(term.charAt(0)) || term.equals("x") || term.equals(")")) && term2.equals("("))) {
				Tokens.add(i + 1, "*");
				//System.out.println("first if: " + term + " " + term2);
			}
			//(sin(x))x = (sin(x))*x
			else if(term.equals(")") && (Character.isDigit(term2.charAt(0)) || term2.equals("x") || term2.equals("(") || Character.isLetter(term2.charAt(0)))) {
				Tokens.add(i + 1, "*");
				//System.out.println("second if: " + term + " " + term2);
			}
		}
	}
	public static void main(String [] args) {
		{
			String exp = "2x^2 + 5x";
			Tokenizer t = new Tokenizer(exp);
			PrintArray(t.getTokens());
		}
		{
			String exp = "5(x+5(x+5(x+5(x+5(5sin(x))))))";
			Tokenizer t = new Tokenizer(exp);
			PrintArray(t.getTokens());
		}
		
	}
	public static void PrintArray(ArrayList<String> array) {
		System.out.println();
		for(int i = 0; i < array.size(); i++) {
			if(i == 0) {
				System.out.print(array.get(i));
			}
			else {
				System.out.print(", " + array.get(i));
			}
		}
	}
}
