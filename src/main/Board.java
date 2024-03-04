package main;

import java.util.ArrayList;

public class Board {

	String[][] boardArray;
	ArrayList<Queen> queens = new ArrayList<Queen>();
	int fitness;
	int n;

	public Board(int n) {
		
		this.n = n;
		boardArray = new String[n][n];
		for ( int i = 0; i < boardArray.length; ++i) {
			for ( int j = 0; j < boardArray.length; ++j) {
			boardArray[i][j] = " - ";
			}
		}
	}
	
	public void print() {
			
			
			for ( int i = 0; i < n; ++i) {
				for ( int j = 0; j < n; ++j) {
					System.out.print(boardArray[i][j]);
				}
				System.out.println();
			}
	}
	
	public void insertQueen(int row, int col) {
		boardArray[row][col] = " Q "; 
		queens.add(new Queen(row,col));
	}
	
	public int checkQueen(Queen q) {
		
		Boolean verticalUp = false;
		Boolean verticalDown = false;
		Boolean horizontalRight = false;
		Boolean horizontalLeft = false;
		Boolean diagonalLeft = false;
		Boolean diagonalRight = false;
		Boolean diagonalLeftDown = false;
		Boolean diagonalRightDown = false;
		
		int count = 0;
		for ( Queen queen: queens) {
			if ( queen != q) {
				if (q.getRow() == queen.getRow()) {
					if(queen.getCol() > q.getCol()) horizontalRight = true;
					else horizontalLeft = true;
				}
				
				if (q.getCol() == queen.getCol()) {
					if (queen.getRow() > q.getRow()) verticalUp = true;
					else verticalDown = true;
				}
				
				int diffRow = (q.getRow() - queen.getRow());
				int diffCol = (q.getCol() - queen.getCol());
				
				if ( Math.abs(diffRow) == Math.abs(diffCol)) {
					
					if ( diffRow > 0 && diffCol < 0) diagonalRight = true;
					else if ( diffRow > 0 && diffCol > 0) diagonalLeft = true;
					else if ( diffRow < 0 && diffCol > 0) diagonalLeftDown = true;
					else if ( diffRow < 0 && diffCol < 0) diagonalRightDown = true;
				}
			}
		}
		
		if(verticalUp) ++count;
		if(verticalDown) ++count;
		if(horizontalRight) ++count;
		if(horizontalLeft) ++count;
		if(diagonalRight) ++count;
		if(diagonalLeft) ++count;
		if(diagonalLeftDown) ++count;
		if(diagonalRightDown) ++count;
		
		return count;
	}
	
	public int countPairs() {
		int count = 0;
		
		for ( Queen queen: queens) {
			count += checkQueen(queen);
		}
		fitness = count / 2;
		return count / 2;
	}
	
	public String[] getChromosome() {
		String[] chromosome = new String[queens.size()];
		int i = 0;
		
		for (Queen queen: queens) {
			chromosome[i] = Integer.toString(queen.getRow()) + Integer.toString(queen.getCol());
			++i;
		}
		
		return chromosome;
	}
	
	public void readChromosome(Chromosome chromosome) {

		for( int i = 0; i < chromosome.genes.length; ++i) {
			String val = chromosome.genes[i];
			insertQueen(Chromosome.getRow(val) , Chromosome.getCol(val) );
		}
	}
	
	public int getFitness() {
		return fitness;
	}

	public void setFitness(int fitness) {
		this.fitness = fitness;
	}
}
