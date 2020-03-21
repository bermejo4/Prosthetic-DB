package ui;
import java.io.*;

public class Menu {
	 
	private static BufferedReader reader;
	private static int max;
	private static int num;
	
	public static void main(String[] args) throws Exception {
	reader = new BufferedReader(new InputStreamReader(System.in));
	System.out.println("WELCOME! THIS IS A PROSTHETIC DATABASE");
	System.out.println("Who are you?");
	System.out.println("1.Patient");
	System.out.println("2.Doctor");
	System.out.println("3.Biomedical Engineer");
	System.out.println("4.Hospital");
	
	do {
		max =4;
		num=InputFlow.takeInteger(reader, "Introduce the number: ");
		
	}while(InputFlow.CheckOption(num, max));
	
	switch(num) {
	case 1: //code
		break;
	case 2: //code
		break;
	case 3: //code
		break;
	case 4: //code
		break;
	default:
	}
	
	//float number = InputFlow.takeFloat(reader, "Introduce a float: ");
	
	}

}
