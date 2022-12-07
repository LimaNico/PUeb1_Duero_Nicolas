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
	public static Boolean q1 = null; // zu beantworten mit true oder false

	// Behauptung q2: "Greedy findet immer eine Loesung."
	public static Boolean q2 = null; // zu beantworten mit true oder false

	// Behauptung q3: "Wenn A* einen Loesungsweg gefunden hat, ist dieser immer optimal."
	public static Boolean q3 = null; // zu beantworten mit true oder false

	// Behauptung q4: "Jedes Puzzle ist loesbar."
	public static Boolean q4 = null; // zu beantworten mit true oder false

	// Behauptung q5: "Die Methode countWrongTiles() in Puzzle.java ist KEINE zulaessige Heuristik."
	public static Boolean q5 = null; // zu beantworten mit true oder false


	// Hier ist Platz fuer Ihre Tests
	public static void main(String[] args) {
		//Puzzle example = new Puzzle(0, 1, 2, 3, 4, 5, 6, 7, 8);
		Puzzle example = new Puzzle(
				3,1,5,
				2,7,4,
				8,0,6);
		//Puzzle example = new Puzzle(1,8,2,0,4,3,7,6,5);
		Summary summary = PuzzleSolver.greedy(example, PuzzleSolver.Heuristic.MANHATTAN, true,0,0);
		System.out.println(summary);
		summary = PuzzleSolver.AStar(example, PuzzleSolver.Heuristic.MANHATTAN, false, 0, 0);
		System.out.println(summary);
	}
}