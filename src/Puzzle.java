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
		int manhattanDist = 0;
		for (int i = 1; i < 9; i++) {
			manhattanDist += Math.abs(position(i)[0]-goal.position(i)[0]) + Math.abs(position(i)[1]-goal.position(i)[1]);
		}
		return manhattanDist;
	}

	public int[] position(int k){

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (state[i][j]==k) return new int[]{i, j};
			}
		}
		return null;
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
		int[] pos = position(0);
		if (pos[1]>0) return true;
		else return false;
	}
	
	public boolean canMoveRight() {
		int[] pos = position(0);
		if (pos[1]<2) return true;
		else return false;
	}
	
	public boolean canMoveUp() {
		int[] pos = position(0);
		if (pos[0]>0) return true;
		else return false;
	}
	
	public boolean canMoveDown() {
		int[] pos = position(0);
		if (pos[0]<2) return true;
		else return false;
	}
	
	public Puzzle moveLeft() {
		int[] pos = position(0);
		Puzzle newPuzzle = new Puzzle(copyState());
		newPuzzle.state[pos[0]][pos[1]] = newPuzzle.state[pos[0]][pos[1]-1];
		newPuzzle.state[pos[0]][pos[1]-1] = 0;
		return newPuzzle;
	}
	
	public Puzzle moveRight() {
		int[] pos = position(0);
		Puzzle newPuzzle = new Puzzle(copyState());
		newPuzzle.state[pos[0]][pos[1]] = newPuzzle.state[pos[0]][pos[1]+1];
		newPuzzle.state[pos[0]][pos[1]+1] = 0;
		return newPuzzle;
	}
	
	public Puzzle moveUp() {
		int[] pos = position(0);
		Puzzle newPuzzle = new Puzzle(copyState());
		newPuzzle.state[pos[0]][pos[1]] = newPuzzle.state[pos[0]-1][pos[1]];
		newPuzzle.state[pos[0]-1][pos[1]] = 0;
		return newPuzzle;
	}
	
	public Puzzle moveDown() {
		int[] pos = position(0);
		Puzzle newPuzzle = new Puzzle(copyState());
		newPuzzle.state[pos[0]][pos[1]] = newPuzzle.state[pos[0]+1][pos[1]];
		newPuzzle.state[pos[0]+1][pos[1]] = 0;
		return newPuzzle;
	}

	public boolean equals(Puzzle p) {
		if (p == null) return false;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				//System.out.println(state[i][j] + "!=" + p.state[i][j] + "=" + (state[i][j] != p.state[i][j]));
				if (state[i][j] != p.state[i][j]) return false;
			}
		}
		return true;

	}

	public String moveBefore(Puzzle p){
		// Checks if the inputted Puzzle is a possible move before and from which direction
		if (canMoveUp()){
			if (p.equals(moveUp())) return "D";
		}
		if (canMoveDown()){
			if (p.equals(moveDown())) return "U";
		}
		if (canMoveLeft()){
			if (p.equals(moveLeft())) return "R";
		}
		if (canMoveRight()){
			if (p.equals(moveRight())) return "L";
		}
		return "error";
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
