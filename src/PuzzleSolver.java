import java.util.*;

public class PuzzleSolver {
	public static Summary greedy(Puzzle puzzle, Heuristic heuristic, boolean detectDouble, int maxDepth, int limit) {
		Summary summary = new Summary();
		summary.startState = puzzle;
		summary.algorithm = "Greedy";
		summary.heuristic = heuristic;
		summary.detectDouble = detectDouble;
		summary.maxDepthPermitted=maxDepth;
		summary.path ="";

		PriorityQueue<PuzzleNode> queue = getPriorityQueue(heuristic,Algorithm.Greedy);

		LinkedList<PuzzleNode> visited = new LinkedList<>();

		queue.add(new PuzzleNode(null, puzzle,""));

		summary.maxDepthReached = 0;
		PuzzleNode node = null;

		while (queue.peek()!=null){
			node = queue.poll();
			if ((node.getPuzzle().equals(Puzzle.goal))) break;

			while (alreadyVisited(node,visited) && detectDouble) node = queue.poll();

			summary.maxDepthReached = Math.max(currentDepth(node), summary.maxDepthReached);
			if (currentDepth(node)==maxDepth) continue;

			summary.numExpansions++;
			if (summary.numExpansions == limit) break;

			visited.add(node);

			queue = expand(node,queue);

			summary.maxQueueSize = Math.max(summary.maxQueueSize, queue.size());
		}


		summary.queueSize = queue.size();
		summary.finalState = node.getPuzzle();
		summary.isSolution = node.getPuzzle().equals(Puzzle.goal);

		summary.path = node.getPath();
		summary.pathLength = node.getPathCost();

		return summary;
	}

	public static Summary AStar(Puzzle puzzle, Heuristic heuristic, boolean detectDouble, int maxDepth, int limit) {
		Summary summary = new Summary();
		summary.startState = puzzle;
		summary.algorithm = "A*";
		summary.heuristic = heuristic;
		summary.detectDouble = detectDouble;
		summary.maxDepthPermitted=maxDepth;
		summary.path ="";

		PriorityQueue<PuzzleNode> queue = getPriorityQueue(heuristic,Algorithm.AStar);

		LinkedList<PuzzleNode> visited = new LinkedList<>();

		queue.add(new PuzzleNode(null, puzzle,""));

		summary.maxDepthReached = 0;
		PuzzleNode node = null;

		while (queue.peek()!=null){
			node = queue.poll();
			if ((node.getPuzzle().equals(Puzzle.goal))) break;

			while (alreadyVisited(node,visited) && detectDouble) node = queue.poll();

			summary.maxDepthReached = Math.max(currentDepth(node), summary.maxDepthReached);
			if (currentDepth(node)==maxDepth) continue;

			summary.numExpansions++;
			if (summary.numExpansions == limit) break;

			visited.add(node);

			queue = expand(node,queue);

			summary.maxQueueSize = Math.max(summary.maxQueueSize, queue.size());
		}


		summary.queueSize = queue.size();
		summary.finalState = node.getPuzzle();
		summary.isSolution = node.getPuzzle().equals(Puzzle.goal);

		summary.path = node.getPath();
		summary.pathLength = node.getPathCost();

		return summary;
	}

	public static PriorityQueue<PuzzleNode> expand(PuzzleNode node, PriorityQueue<PuzzleNode> queue){
		if (node.getPuzzle().canMoveLeft()) queue.add(new PuzzleNode(node,node.getPuzzle().moveLeft(),"L"));
		if (node.getPuzzle().canMoveRight()) queue.add(new PuzzleNode(node,node.getPuzzle().moveRight(),"R"));
		if (node.getPuzzle().canMoveUp()) queue.add(new PuzzleNode(node,node.getPuzzle().moveUp(),"U"));
		if (node.getPuzzle().canMoveDown()) queue.add(new PuzzleNode(node,node.getPuzzle().moveDown(),"D"));
		return queue;
	}

	public static boolean alreadyVisited(PuzzleNode node, LinkedList<PuzzleNode> visited){
		for (PuzzleNode visit:
			 visited) {
			if (visit.equals(node)) return true;
		}
		return false;
	}

	public static int currentDepth(PuzzleNode node){
		int depth = 0;
		while (node != null){
			depth++;
			node = node.getParent();
		}
		return depth;
	}

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
