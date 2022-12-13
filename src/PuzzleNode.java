public class PuzzleNode {
    private PuzzleNode parent;
    private final Puzzle puzzle;
    private String action;

    // Initialisierung
    public PuzzleNode(PuzzleNode parent, Puzzle puzzle, String action){
        this.parent = parent;
        this.action = action;
        this.puzzle = puzzle;
    }

    // Gibt die Kosten von der Wurzel bis zum derzeitigen Knoten aus
    public int getPathCost() {
        PuzzleNode node = this;
        int cost = 0;
        while (node != null){
            cost++;
            node = node.getParent();
        }
        return cost;
    }

    public String getAction() {
        return action;
    }

    public Puzzle getPuzzle() {
        return puzzle;
    }

    public PuzzleNode getParent() {
        return parent;
    }

    // Gibt den Pfad von der Wurzel bis zum derzeitigen Knoten aus
    public String getPath(){
        PuzzleNode node = this;
        StringBuilder path = new StringBuilder();
        while (node != null){
            path.insert(0, node.getAction());
            node = node.getParent();
        }
        return path.toString();
    }

    public void setParent(PuzzleNode parent) {
        this.parent = parent;
    }

    public void setAction(String action) {
        this.action = action;
    }
}

