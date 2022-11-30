import java.lang.reflect.Array;
import java.util.Arrays;

public class Puzzle {
	// gesuchter Zielzustand
	public static Puzzle goal = new Puzzle(new int[][] {
			{1, 2, 3},
			{8, 0, 4},
			{7, 6, 5}
	});

	public int[][] state;

	// initialisiere Puzzle mit gegebenen Werten
	public Puzzle (int a, int b, int c, int d, int e, int f, int g, int h, int i) {
		this.state = new int[][]{
			{a, b, c},
			{d, e, f},
			{g, h, i}
		};
	}


	// initialisiere Puzzle mit 2D-Array
	public Puzzle (int[][] state) {
		this.state = state;
	}
	
	// Zaehlung der falsch platzierten Kacheln 1 bis 8
	public int countWrongTiles() {
		int wrongTiles = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (state[i][j]!=goal.state[i][j]) wrongTiles++;
			}
		}
		return wrongTiles;
	}
	
	// Berechnung der Summe aller (vertikalen und horizontalen) Distanzen der Kacheln 1 bis 8 zur jeweiligen Zielposition
	public int manhattanDist() {
		//ToDo
		int[][] positions = new int[][]{
				{1,1},{0,0},{0,1},{0,2},{1,2},{2,2},{2,1},{2,0},{1,0}
		};
		int manhattanDist = 0;
		for (int i = 0; i < 3; i++) {
			for (int k = 0; k < 3; k++) {
				if(state[i][k] != 0){
					//System.out.println(state[i][k]);
					//System.out.print(i+"-"+positions[state[i][k]][0]+"+"+ k +"-"+positions[state[i][k]][1]);

					int sum = Math.abs(i - positions[state[i][k]][0]) + Math.abs(k - positions[state[i][k]][1]);
					//System.out.println("="+sum);

					manhattanDist += Math.abs(i - positions[state[i][k]][0]) + Math.abs(k - positions[state[i][k]][1]);
				}
			}
		}

		return manhattanDist;
	}

	public int[] position(){

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (state[i][j]==0) return new int[]{i, j};
			}
		}
		return null;
	}

	public int[][] getState() {
		return state;
	}
	public int[][] copyState(){
		int[][] copiedState = new int[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				copiedState[i][j] = state[i][j];
			}
		}
		return copiedState;
	}


	public boolean canMoveLeft() {
		int[] pos = position();
		if (pos[0]>0) return true;
		else return false;
	}
	
	public boolean canMoveRight() {
		int[] pos = position();
		if (pos[0]<2) return true;
		else return false;
	}
	
	public boolean canMoveUp() {
		int[] pos = position();
		if (pos[1]>0) return true;
		else return false;
	}
	
	public boolean canMoveDown() {
		int[] pos = position();
		if (pos[1]<2) return true;
		else return false;
	}
	
	public Puzzle moveLeft() {
		if (canMoveLeft()){
			int[] pos = position();
			Puzzle newPuzzle = new Puzzle(copyState());
			newPuzzle.state[pos[0]][pos[1]] = newPuzzle.state[pos[0]][pos[1]-1];
			newPuzzle.state[pos[0]][pos[1]-1] = 0;
			return newPuzzle;
		}
		else return null;
	}
	
	public Puzzle moveRight() {
		if (canMoveRight()){
			int[] pos = position();
			Puzzle newPuzzle = new Puzzle(copyState());
			newPuzzle.state[pos[0]][pos[1]] = newPuzzle.state[pos[0]][pos[1]+1];
			newPuzzle.state[pos[0]][pos[1]+1] = 0;
			return newPuzzle;
		}
		else return null;
	}
	
	public Puzzle moveUp() {
		if (canMoveUp()){
			int[] pos = position();
			Puzzle newPuzzle = new Puzzle(copyState());
			newPuzzle.state[pos[0]][pos[1]] = newPuzzle.state[pos[0]-1][pos[1]];
			newPuzzle.state[pos[0]-1][pos[1]] = 0;
			return newPuzzle;
		}
		else return null;
	}
	
	public Puzzle moveDown() {
		if (canMoveDown()){
			int[] pos = position();
			Puzzle newPuzzle = new Puzzle(copyState());
			newPuzzle.state[pos[0]][pos[1]] = newPuzzle.state[pos[0]+1][pos[1]];
			newPuzzle.state[pos[0]+1][pos[1]] = 0;
			return newPuzzle;
		}
		else return null;
	}

	public boolean equals(Puzzle p) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (state[i][j]!=p.state[i][j]) return false;
			}
			return true;

		}
		return false;
	}

	// Ausgabe des Zustands als String
	public String toString() {
		String str = "\n";
		for(int r=0; r<3; r++) {
			str += "[";
			for(int c=0; c<3; c++) {
				str += state[r][c];
				if(c<2) str += ", ";
			}
			str += "]\n";
		}
		return str;
	}
}
