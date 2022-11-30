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
		// ToDo
		return 0;
	}
	
	// Berechnung der Summe aller (vertikalen und horizontalen) Distanzen der Kacheln 1 bis 8 zur jeweiligen Zielposition
	public int manhattanDist() {
		// ToDo
		return 0;
	}

	public int[] position(){
		// ToDo Where is the zero?
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
		// ToDo canMoveLeft()
		int[] pos = position();
		if (pos[0]>0) return true;
		else return false;
	}
	
	public boolean canMoveRight() {
		// ToDo canMoveRight()
		int[] pos = position();
		if (pos[0]<2) return true;
		else return false;
	}
	
	public boolean canMoveUp() {
		// ToDo canMoveUp()
		int[] pos = position();
		if (pos[1]>0) return true;
		else return false;
	}
	
	public boolean canMoveDown() {
		// ToDo canMoveDown()
		int[] pos = position();
		if (pos[1]<2) return true;
		else return false;
	}
	
	public Puzzle moveLeft() {
		// ToDo moveLeft()
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
		// ToDo
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
		// ToDo
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
		// ToDo
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
		// ToDo
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
