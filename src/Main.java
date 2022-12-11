import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class Main {

	// Angaben zu Ihrer Person:
	public static String nachname = "Duero"; // Tragen Sie hier Ihren Nachnamen ein
	public static String vorname  = "Nicolas"; // Tragen Sie hier Ihren Vornamen ein
	public static String matrikel = "1592040"; // Tragen Sie hier Ihre Matrikelnummer ein

	// Falls Sie mit einer weiteren Person zusammengarbeitet haben, tragen Sie hier den Namen dieser Person ein:
	public static String gruppe   = "";

	/* Vergessen Sie nicht, die nachfolgenden Behauptungen zu pruefen!
	 * Wahr:   qX = true;
	 * Falsch: qX = false;
	 */

	// Behauptung q1: "Greedy findet NICHT immer eine vorhandene Loesung, aber
	//                wenn eine Loesung gefunden wird, ist der Loesungsweg optimal."
	public static Boolean q1 = false; // zu beantworten mit true oder false

	// Behauptung q2: "Greedy findet immer eine Loesung."
	public static Boolean q2 = false; // zu beantworten mit true oder false

	// Behauptung q3: "Wenn A* einen Loesungsweg gefunden hat, ist dieser immer optimal."
	public static Boolean q3 = true; // zu beantworten mit true oder false

	// Behauptung q4: "Jedes Puzzle ist loesbar."
	public static Boolean q4 = false; // zu beantworten mit true oder false

	// Behauptung q5: "Die Methode countWrongTiles() in Puzzle.java ist KEINE zulaessige Heuristik."
	public static Boolean q5 = false; // zu beantworten mit true oder false


	// Hier ist Platz fuer Ihre Tests
	public static void main(String[] args) {
		//Puzzle example = new Puzzle(0, 1, 2, 3, 4, 5, 6, 7, 8);
		/*Puzzle example = new Puzzle(
				8,1,2,
				0,4,3,
				7,6,5);
		//Puzzle example = new Puzzle(1,8,2,0,4,3,7,6,5);

		 */

		int[][][] states = new int[][][]{
				{
						{7, 6, 1},
						{2, 0, 5},
						{3, 4, 8}
				}
		};
		/*
		for (int[][] state:
			 states) {
			Puzzle example = new Puzzle(state);
			Summary summary = PuzzleSolver.greedy(example, PuzzleSolver.Heuristic.MANHATTAN, true,0,0);
			System.out.println("Greedy:"+summary);
			//if (example.isSolvable()!= summary.isSolution) System.out.println("Greedy: " + summary);
			System.out.println("**********************");
			summary = PuzzleSolver.AStar(example, PuzzleSolver.Heuristic.MANHATTAN, true, 0, 0);
			System.out.println("A*:"+summary);
			//if (example.isSolvable()!= summary.isSolution) System.out.println("A*: " + summary);
			System.out.println("------------------");
		}

		 */

		for (int i = 0; i < 100; i++) {
			Puzzle example = randomPuzzle();
			//System.out.println(example);
			//System.out.println("Lösbar:"+example.isSolvable());
			if (!example.isSolvable()) continue;
			Summary summary = PuzzleSolver.greedy(example, PuzzleSolver.Heuristic.MANHATTAN, true,0,0);
			//System.out.println("Greedy:"+summary.isSolution);
			if (example.isSolvable()!= summary.isSolution) System.out.println("Greedy: " + summary);
			summary = PuzzleSolver.AStar(example, PuzzleSolver.Heuristic.MANHATTAN, true, 0, 0);
			//System.out.println("A*:"+summary.isSolution);
			if (example.isSolvable()!= summary.isSolution) System.out.println("A*: " + summary);
			System.out.println("------------------");
		}
	}

	public static Puzzle randomPuzzle(){
		ArrayList<Integer> state = new ArrayList<>();
		for (int i = 0; i < 9; i++) {
			state.add(i);
		}
		Collections.shuffle(state);
		int k = 0;
		int[][] list = new int[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j<3; j++){
				list[i][j] = state.get(k);
				k++;
			}

		}
		return new Puzzle(list);
	}
}