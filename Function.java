package Tests;

class FunctionPriority {
	public static int getPriority() {
		return 1;
	}
}
abstract public class Function {
	String name;
	int priority;
	
	
	abstract public double Evaluate(double value);
	public static int getPriority() {
		return 1;
	}
	
	public static Function get(String name) {
		if (name.equals("sin")) {
			return new Sine();
		}
		if (name.equals("cos")) {
			return new Cosine();
		}
		if (name.equals("tan")) {
			return new Tangent();
		}
		if (name.equals("csc")) {
			return new Cosecant();
		}
		if (name.equals("sec")) {
			return new Secant();
		}
		if (name.equals("cot")) {
			return new Cotangent();
		}
		if (name.equals("arcsin")) {
			return new Arcsine();
		}
		if (name.equals("arccos")) {
			return new Arccosine();
		}
		if (name.equals("arctan")) {
			return new Arctangent();
		}
		if (name.equals("log")) {
			return new Log();
		}
		if (name.equals("ln")) {
			return new Ln();
		}
		if (name.equals("abs")) {
			return new Abs();
		}
		else {
			return null;
		}
	}
}

	class Sine extends Function {
		public double Evaluate(double value) {
			return Math.sin(value);
		}
	}
	class Cosine extends Function{
		public double Evaluate(double value) {
			return Math.cos(value);
		}
	}
	class Tangent extends Function{
		public double Evaluate(double value) {
			return Math.tan(value);
		}
	}
	class Cosecant extends Function{
		public double Evaluate(double value) {
			return 1 / Math.sin(value);
		}
	}
	class Secant extends Function{
		public double Evaluate(double value) {
			return 1 / Math.cos(value);
		}
	}
	class Cotangent extends Function{
		public double Evaluate(double value) {
			return 1 / Math.tan(value);
		}
	}
	class Arcsine extends Function{
		public double Evaluate(double value) {
			return 1 / Math.asin(value);
		}
	}
	class Arccosine extends Function{
		public double Evaluate(double value) {
			return 1 / Math.acos(value);
		}
	}
	class Arctangent extends Function{
		public double Evaluate(double value) {
			return 1 / Math.atan(value);
		}
	}
	class Log extends Function{
		public double Evaluate(double value) {
			return Math.log10(value);
		}
	}
	class Ln extends Function{
		public double Evaluate(double value) {
			return Math.log(value);
		}
	}
	class Abs extends Function{
		public double Evaluate(double value) {
			return Math.abs(value);
		}
	}
