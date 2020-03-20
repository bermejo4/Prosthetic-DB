package ui;
import java.io.*;

public class InputFlow {
	
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
	
    public static int takeInteger(BufferedReader reader, String text) {
        boolean check = false;
        int dato = 0;

        System.out.println(text);

        while (!check||dato<0) {
            try {
                dato = Integer.parseInt(reader.readLine());
                check = true;
            } catch (IOException ex) {
                System.out.println("Error reading");
            } catch (NumberFormatException nfex) {
                System.out.println("You have not introduced a Integer, you must do it.");
                System.out.println(text);
            }

        }
        return dato;
    }
    
    public static float takeFloat(BufferedReader reader, String text) {
        boolean check = false;
        float dato = 0;

        System.out.println(text);

        while (!check||dato<0) {
            try {
                dato = Float.parseFloat(reader.readLine());
                check = true;
            } catch (IOException ex) {
                System.out.println("Error reading");
            } catch (NumberFormatException nfex) {
                System.out.println("You have not introduced a Float, you must do it.");
                System.out.println(text);
            }

        }
        return dato;
    }
	
}
