import java.util.*;

public class PuzzleSolver {
	public static Summary greedy(Puzzle puzzle, Heuristic heuristic, boolean detectDouble, int maxDepth, int limit) {

		// Zuweisung der bereits bekannten Summary Attribute
		Summary summary = new Summary();
		summary.startState = puzzle;
		summary.algorithm = "Greedy";
		summary.heuristic = heuristic;
		summary.detectDouble = detectDouble;
		summary.maxDepthPermitted=maxDepth;
		summary.path ="";

		// Erstellen einer PriorityQueue zur Bewertung der Nodes
		// und einer LinkedList zur Speicherung der bereits besuchten Nodes
		PriorityQueue<PuzzleNode> queue = getPriorityQueue(heuristic,Algorithm.Greedy);
		HashMap<String, PuzzleNode> visited = new HashMap<>();

		queue.add(new PuzzleNode(null, puzzle,""));

		summary.maxDepthReached = 0;
		PuzzleNode node = null;

		while (queue.peek()!=null){
			node = queue.poll(); // Die Node mit der besten Heuristic + bisherige PathCosts wird entnommen

			if ((node.getPuzzle().equals(Puzzle.goal))) break; // Zielzustand wird geprüft.

			// Falls detectDouble wahr ist, wird gecheckt, ob der Puzzle-Zustand schon existiert hat.
			// Falls der Zustand schon erreicht worden ist, wird der Schleifendurchlauf beendet.
			if(detectDouble){
				if (alreadyVisited(node,visited)) continue;
			}

			// Feststellen der maximalen Tiefe bisher
			int depth = node.getPathCost();
			summary.maxDepthReached = Math.max(depth, summary.maxDepthReached);
			if (depth==maxDepth) continue;

			// Anzahl der bisherigen Expansionen erhöhen und mit Limit abgleichen
			summary.numExpansions++;
			if (summary.numExpansions >= limit) break;

			// Hinzufügen des Knotens zu der HashMap mit den bereits besuchten PuzzleNodes
			if (detectDouble){
				visited.put(node.getPuzzle().getString2D(),node);
			}

			expand(node,queue);

			summary.maxQueueSize = Math.max(summary.maxQueueSize, queue.size());
		}

		// Fehlende Summary Attribute werden ergänzt
		summary.queueSize = queue.size();
		summary.finalState = node.getPuzzle();
		summary.isSolution = node.getPuzzle().equals(Puzzle.goal);

		if (node.getPuzzle().equals(Puzzle.goal)){
			summary.path = node.getPath();
			summary.pathLength = node.getPathCost();
		}
		else {
			summary.path = null;
			summary.pathLength = -1;
		}

		return summary;
	}

	public static Summary AStar(Puzzle puzzle, Heuristic heuristic, boolean detectDouble, int maxDepth, int limit) {
		// Zuweisung der bereits bekannten Summary Attribute
		Summary summary = new Summary();
		summary.startState = puzzle;
		summary.algorithm = "A*";
		summary.heuristic = heuristic;
		summary.detectDouble = detectDouble;
		summary.maxDepthPermitted=maxDepth;
		summary.path ="";

		// Erstellen einer PriorityQueue zur Bewertung der Nodes
		// und einer LinkedList zur Speicherung der bereits besuchten Nodes
		PriorityQueue<PuzzleNode> queue = getPriorityQueue(heuristic,Algorithm.AStar);
		HashMap<String,PuzzleNode> visited = new HashMap<>();

		queue.add(new PuzzleNode(null, puzzle,""));

		summary.maxDepthReached = 0;
		PuzzleNode node = null;
		while (queue.peek()!=null){
			node = queue.poll(); // Die Node mit der besten Heuristic + bisherige PathCosts wird entnommen
			if ((node.getPuzzle().equals(Puzzle.goal))) break;

			// Falls detectDouble wahr ist, wird gecheckt, ob der Puzzle-Zustand schon existiert hat.
			// Falls der Zustand schon erreicht worden ist, wird der Schleifendurchlauf beendet.
			if(detectDouble){
				if (alreadyVisited(node,visited)) continue;
			}

			// Feststellen der maximalen Tiefe bisher
			int depth = node.getPathCost();
			summary.maxDepthReached = Math.max(depth, summary.maxDepthReached);
			if (depth==maxDepth) continue;

			// Anzahl der bisherigen Expansionen erhöhen und mit Limit abgleichen
			summary.numExpansions++;
			if (summary.numExpansions == limit) break;

			// Hinzufügen des Knotens zu der HashMap mit den bereits besuchten PuzzleNodes
			if (detectDouble){
				visited.put(node.getPuzzle().getString2D(),node);
			}

			expand(node,queue);

			summary.maxQueueSize = Math.max(summary.maxQueueSize, queue.size());
		}

		// Fehlende Summary Attribute werden ergänzt
		summary.queueSize = queue.size();
		summary.finalState = node.getPuzzle();
		summary.isSolution = node.getPuzzle().equals(Puzzle.goal);
		if (node.getPuzzle().equals(Puzzle.goal)){
			summary.path = node.getPath();
			summary.pathLength = node.getPathCost();
		}
		else {
			summary.path = null;
			summary.pathLength = -1;
		}

		return summary;
	}

	// Expandiert die möglichen Kinder von dem übergebenen Node und fügt diese der PriorityQueue hinzu.
	public static void expand(PuzzleNode node, PriorityQueue<PuzzleNode> queue){
		if (node.getPuzzle().canMoveLeft()) queue.add(new PuzzleNode(node,node.getPuzzle().moveLeft(),"L"));
		if (node.getPuzzle().canMoveRight()) queue.add(new PuzzleNode(node,node.getPuzzle().moveRight(),"R"));
		if (node.getPuzzle().canMoveUp()) queue.add(new PuzzleNode(node,node.getPuzzle().moveUp(),"U"));
		if (node.getPuzzle().canMoveDown()) queue.add(new PuzzleNode(node,node.getPuzzle().moveDown(),"D"));

	}

	// Methode überprüft, ob eine Node bereits besucht worden ist und überprüft auch, ob die Kosten so gering wie möglich sind.
	public static boolean alreadyVisited(PuzzleNode node, HashMap<String, PuzzleNode> visited){
		if (visited.containsKey(node.getPuzzle().getString2D())){
			if (visited.get(node.getPuzzle().getString2D()).getPathCost()>node.getPathCost()){
				visited.get(node.getPuzzle().getString2D()).setParent(node.getParent());
				visited.get(node.getPuzzle().getString2D()).setAction(node.getAction());
			}
		}
		return visited.containsKey(node.getPuzzle().getString2D());
	}

	// Erstellen einer PriorityQueue, welche die Werte nach den vorher bestimmten Heuristic sortiert.
	// Dabei wird außerdem darauf geachtet, ob es sich um A* oder Greedy handelt, und dement sprechen die PathCosts dazu addiert.
	public static PriorityQueue<PuzzleNode> getPriorityQueue(Heuristic heuristic, Algorithm algorithm){
		return new PriorityQueue<>((o1, o2) -> {
			int cost1 = 0, cost2 = 0;
			if (algorithm.equals(Algorithm.AStar)) {
				cost1 = o1.getPathCost();
				cost2 = o2.getPathCost();
			}
			if (heuristic.equals(Heuristic.WRONG_TILES)){
				return Integer.compare(o1.getPuzzle().countWrongTiles()+cost1, o2.getPuzzle().countWrongTiles()+cost2);
			}
			else return Integer.compare(o1.getPuzzle().manhattanDist()+cost1,o2.getPuzzle().manhattanDist()+cost2);
		});
	}


	// hieran bitte nichts veraendern
	public enum Heuristic {
		WRONG_TILES,
		MANHATTAN
	}

	public enum Algorithm{
		AStar,
		Greedy
	}
}
