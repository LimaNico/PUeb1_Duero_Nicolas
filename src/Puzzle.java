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

	//	Rückgabe der Position als Array mit dem Wert k
	public int[] position(int k){

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (state[i][j]==k) return new int[]{i, j};
			}
		}
		return null;
	}

	// Gibt den ein Array zurück mit den Werten des derzeitigen States ohne Referenz
	public int[][] copyState(){
		int[][] copiedState = new int[3][3];
		for (int i = 0; i < 3; i++) {
			System.arraycopy(state[i], 0, copiedState[i], 0, 3);
		}
		return copiedState;
	}

	//	Check ob 0 nach links bewegt werden kann.
	public boolean canMoveLeft() {
		int[] pos = position(0);
		return pos[1] > 0;
	}

	//	Check ob 0 nach rechts bewegt werden kann.
	public boolean canMoveRight() {
		int[] pos = position(0);
		return pos[1] < 2;
	}

	//	Check ob 0 nach oben bewegt werden kann.
	public boolean canMoveUp() {
		int[] pos = position(0);
		return pos[0] > 0;
	}

	//	Check ob 0 nach unten bewegt werden kann.
	public boolean canMoveDown() {
		int[] pos = position(0);
		return pos[0] < 2;
	}

	// Bewegt 0 nach links
	public Puzzle moveLeft() {
		int[] pos = position(0);
		Puzzle newPuzzle = new Puzzle(copyState());
		newPuzzle.state[pos[0]][pos[1]] = newPuzzle.state[pos[0]][pos[1]-1];
		newPuzzle.state[pos[0]][pos[1]-1] = 0;
		return newPuzzle;
	}

	// Bewegt 0 nach rechts
	public Puzzle moveRight() {
		int[] pos = position(0);
		Puzzle newPuzzle = new Puzzle(copyState());
		newPuzzle.state[pos[0]][pos[1]] = newPuzzle.state[pos[0]][pos[1]+1];
		newPuzzle.state[pos[0]][pos[1]+1] = 0;
		return newPuzzle;
	}

	// Bewegt 0 nach oben
	public Puzzle moveUp() {
		int[] pos = position(0);
		Puzzle newPuzzle = new Puzzle(copyState());
		newPuzzle.state[pos[0]][pos[1]] = newPuzzle.state[pos[0]-1][pos[1]];
		newPuzzle.state[pos[0]-1][pos[1]] = 0;
		return newPuzzle;
	}

	// Bewegt 0 nach unten
	public Puzzle moveDown() {
		int[] pos = position(0);
		Puzzle newPuzzle = new Puzzle(copyState());
		newPuzzle.state[pos[0]][pos[1]] = newPuzzle.state[pos[0]+1][pos[1]];
		newPuzzle.state[pos[0]+1][pos[1]] = 0;
		return newPuzzle;
	}

	// Überprüft, ob dieses Puzzle und das übergebene Puzzle p gleich sind.
	public boolean equals(Puzzle p) {
		if (p == null) return false;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (state[i][j] != p.state[i][j]) return false;
			}
		}
		return true;

	}

	public int getInvCount(int[] arr)
	{
		int inv_count = 0;
		for (int i = 0; i < 9; i++)
			for (int j = i + 1; j < 9; j++)
					// Value 0 is used for empty space
				if (arr[i] > 0 &&
						arr[j] > 0 && arr[i] > arr[j])
					inv_count++;
		return inv_count;
	}

	// Gibt aus, ob ein Puzzle lösbar ist.
	public boolean isSolvable()
	{
		int[] linearPuzzle;
		linearPuzzle = new int[9];
		int k = 0;
		for(int i=0; i<3; i++)
			for(int j=0; j<3; j++)
				linearPuzzle[k++] = state[i][j];
		int invCount = getInvCount(linearPuzzle);
		return !(invCount % 2 == 0);
	}

	// Gibt den State als String aus, um ein HashKey zu erzeugen
	public String getString2D(){
		StringBuilder state2D = new StringBuilder();
		for (int[] row:
			 state) {
			for (int value:
				 row) {
				state2D.append(value);
			}
		}
		return state2D.toString();
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
