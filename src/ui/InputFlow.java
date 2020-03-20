package ui;

public class InputErrors {
	public static boolean CheckOption(int num, int max) {
		if(num>max || num<0) {
			System.out.println("This number is not an option");
			System.out.println("The number must be between 1 and "+max);
			System.out.println("Try again!");
			return true;
		}else {
			return false;
		}
	}
}
