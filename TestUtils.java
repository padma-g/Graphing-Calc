package Tests;

import java.util.ArrayList;

public class TestUtils {
	public static <T> boolean CompareArray(ArrayList<T> array1, T[] array2) {
		if (array1.size() != array2.length) {
			System.out.println("Length mismatch: " + array1.size() + " " + array2.length);
			return false;
		}
		for(int i = 0; i < array1.size(); i++) {
			if (!array1.get(i).equals(array2[i])) {
				System.out.println("Items mismatch: " + array1.get(i) + " " + array2[i]);
				return false;
			}
		}
		return true;
	}
	public static <T> void PrintArray(T[] array) {
		System.out.println();
		for(int i = 0; i < array.length; i++) {
			if(array[i] == null) {
				break;
			}
			if(i == 0) {
				System.out.print(array[i]);
			}
			else {
				System.out.print(", " + array[i]);
			}
		}
	}
	public static boolean ComparePoints(ArrayList<Point> results, ArrayList<Point> expected) {
		if(results.size() != expected.size()) {
			System.out.println("Length mismatch: " + results.size() + " " + expected.size());
			return false;
		}
		double tolerance = 1e-10;
		
		for(int i = 0; i < results.size(); i++) {
			if (Math.abs(results.get(i).X() - expected.get(i).x) > tolerance ||
			    Math.abs(results.get(i).Y() - expected.get(i).y) > tolerance) {
				System.out.println("Value mismatch for " + i + " : (" + results.get(i).X() + ", " + results.get(i).Y() + ") != ("
						+ expected.get(i).X() + ", " + expected.get(i).Y() + ")");
				return false;
			}
		}
		return true;
	}
}
