import java.util.ArrayList;
import java.util.Collections;

public class Main {

	// Angaben zu Ihrer Person:
	public static String nachname = "Duero"; // Tragen Sie hier Ihren Nachnamen ein
	public static String vorname  = "Nicolas"; // Tragen Sie hier Ihren Vornamen ein
	public static String matrikel = "1592040"; // Tragen Sie hier Ihre Matrikelnummer ein

	// Falls Sie mit einer weiteren Person zusammengarbeitet haben, tragen Sie hier den Namen dieser Person ein:
	public static String gruppe   = "Artur Abramskij";

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
		for (int i = 0; i < 200; i++) {

			Puzzle p = randomPuzzle();
			while (!p.isSolvable()) p = randomPuzzle();
			System.out.println(p);
			Summary summary = PuzzleSolver.AStar(p, PuzzleSolver.Heuristic.MANHATTAN,true,40,0);
			Summary summary1 = PuzzleSolver.AStar(p, PuzzleSolver.Heuristic.MANHATTAN,false,40,0);
			System.out.println("Summary : "+summary.path);
			System.out.println("Summary1: "+summary1.path);
			System.out.println("pathCost equals? " + (summary.pathLength == summary1.pathLength));
			if (summary1.path != null && summary.path!=null){
				//System.out.println(summary1.path.equals(summary.path));
				if (summary1.path.equals(summary.path)) continue;
			}

			Puzzle p1 = new Puzzle(p.copyState());
			if (summary.path != null){
				for (char c:
						summary.path.toCharArray()) {
					switch (c) {
						case 'L' -> p = p.moveLeft();
						case 'R' -> p = p.moveRight();
						case 'D' -> p = p.moveDown();
						case 'U' -> p = p.moveUp();
						default -> {
							System.out.println("Fail");
							System.out.println(c);
						}
					}
				}
			}
			if (summary1.path != null){

				for (char c:
						summary1.path.toCharArray()) {
					switch (c) {
						case 'L' -> p1 = p1.moveLeft();
						case 'R' -> p1 = p1.moveRight();
						case 'D' -> p1 = p1.moveDown();
						case 'U' -> p1 = p1.moveUp();
						default -> {
							System.out.println("Fail");
							System.out.println(c);
						}
					}
				}
			}
			System.out.println("P:\n"+p);
			System.out.println("P1:\n"+p1);
			System.out.println("__________________________________________________________");
		}
	}

	public static Puzzle randomPuzzle(){
		/*Funktion um Puzzle zu erstellen, welche zufällig sind*/
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