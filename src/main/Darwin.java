package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;





public class Darwin {

	Random rand = new Random();
	int n;
	int generation = 0;
	Chromosome[] chromosomes = new Chromosome[8];
	Chromosome best = new Chromosome(n);
	
	public Darwin(int n) {
		this.n = n;
	}
	
	public void findSolution() {
		
		Boolean finished = false;
		long timeStart = System.currentTimeMillis();
		for ( int i = 0; i < 8; ++i) {
			chromosomes[i] = new Chromosome(n);
		}
		
		while(!finished && System.currentTimeMillis() - timeStart < 60000) {
		finished = assessFitness();
		chromosomes = reproduce();
		++generation;
		}
		
		long timeTotal = System.currentTimeMillis() - timeStart;
		Board b = new Board(n);
		b.readChromosome(best);
		System.out.println("Time Taken:  			" + timeTotal + "ms");
		System.out.println("Generation:  			" + generation);
		System.out.println("Best fitness achieved:  " + best.fitness);
		b.print();
		
	}
	
	private Boolean assessFitness() {
		
		Boolean done = false;
		
		int normFactor = n * n;
		
		for ( int i = 0; i < 8; ++i) {
			
			Board b = new Board(n);
			b.readChromosome(chromosomes[i]);
			int fitnessRaw = b.countPairs();
			
			double fitness = (double) (normFactor - fitnessRaw) / (double) normFactor;
			if ( fitness == 1) done = true;
				chromosomes[i].setFitness(fitness);
				if ( fitness > best.fitness) best = chromosomes[i];
				
			}
			
		return done;
	}
	
	
	private Chromosome[] reproduce() {
		Chromosome[] offspring = new Chromosome[8];
		
		ArrayList<Chromosome> local =  new ArrayList<Chromosome>(Arrays.asList(chromosomes));
		
		Chromosome[] parents = new Chromosome[4];
		
		for ( int i = 0; i < 4; ++i) {
			
			double max = 0;
			int maxIndex = 0;
			int count = 0;
			for ( Chromosome c : local) {
				
				if ( c.getFitness() > max) { 
					max = c.getFitness();
					maxIndex = count;
				}
				++count;
			}
			parents[i] = local.get(maxIndex);
			local.remove(maxIndex);
		}
		
		offspring[0] = crossParents(parents[0], parents[1]);
		offspring[1] = crossParents(parents[0], parents[2]);
		offspring[2] = crossParents(parents[0], parents[3]);
		offspring[3] = crossParents(parents[1], parents[2]);
		offspring[4] = parents[0];
		offspring[5] = parents[1];
		offspring[6] = new Chromosome(n);
		offspring[7] = new Chromosome(n);
		return offspring;
	}
	
	private Chromosome crossParents(Chromosome pi, Chromosome pi2) {
		
		Chromosome p1;
		Chromosome p2;
		Chromosome child = new Chromosome(n);
		
		String[] genes = new String[n];
		int c = rand.nextInt(n);
		
		if ( rand.nextInt(100) % 2 == 0) {
			p1 = pi;
			p2 = pi2;
		}
		else {
			p1 = pi2;
			p2 = pi;
		}
		
		for ( int i = 0; i < n; ++i) {
			if (i <= c) genes[i] = p1.genes[i];
			else genes[i] = p2.genes[i];
		}
		
		
		int muti = rand.nextInt(n);
		genes[muti] = randNumber();
		child.setChromosome(genes);
		return child;
	}
	
	
	public String randNumber() {
		
		int row = rand.nextInt(n);
		int col = rand.nextInt(n);
		String result = row + "/" + col;
		return result;
	}
	
	
	
	public void printChromosome(String[] chromosome) {
		for ( int i = 0; i < chromosome.length; ++i) {
			System.out.print(chromosome[i] + " ");
		}
		System.out.println();
	}
}
