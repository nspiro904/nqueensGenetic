package main;

import java.util.Scanner;

public class main {

	public static void main( String args[] )
	 {
		Scanner scnr = new Scanner(System.in);
		int input = 0;
	  while ( input != -1) {
		  System.out.println("Enter value between 4 and 20, or -1 to quit");
		  input = scnr.nextInt();

			if (input != -1) {
		  Darwin d = new Darwin(input);
		  d.findSolution();
			};
	  }
		
		
	 }
	
	
	
}
