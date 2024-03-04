package main;

import java.util.Random;

public class Chromosome {

	int n;
	double fitness = 0;
	String[] genes;

	Random rand = new Random();
	
	public Chromosome(int n) {
		this.n = n;
		genes = new String[n];
		for ( int i = 0; i < n; ++i) {
			genes[i] = randNumber();
		}
		
		int dupe = checkDuplicates(genes);
		
		while ( dupe != -1) {
			genes[dupe] = randNumber();
			dupe = checkDuplicates(genes);
		}
		
	}
	
	//should be string array just shouldnt be doing this right now
	
	public void setChromosome(String[] c) {

		int dupe = checkDuplicates(c);

		while ( dupe != -1) {
			c[dupe] = randNumber();
			dupe = checkDuplicates(c);
		}
		genes = c;
	}
	
	public int checkDuplicates(String[] c) {
		
		for ( int i = 0; i < c.length; ++i) {
			for ( int j = 0; j < c.length; ++j) {
				if ( getRow(c[i]) == getRow(c[j]) && getCol(c[i]) == getCol(c[j]) && i != j) return i;
			}
		}
		return -1;
	}

	
	public static int getRow(String s) {
		String[] split = s.split("/");
		return Integer.parseInt(split[0]);
	}
	
	public static int getCol(String s) {
		String[] split = s.split("/");
		return Integer.parseInt(split[1]);
	}
	
	public String randNumber() {
		int row = rand.nextInt(n);
		int col = rand.nextInt(n);
		String result = row + "/" + col;
		
		
		return result;
	}

	public double getFitness() {
		return fitness;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}
	
	public void print() {
		
		for ( int i = 0; i < n; ++i) {
			System.out.print(genes[i] + " ");		
	}
	System.out.println();
	}
}
