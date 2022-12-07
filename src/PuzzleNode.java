public class PuzzleNode {
    private final PuzzleNode parent;
    private final Puzzle puzzle;
    private final String action;
    private int pathCost = 0;
    private String path = "";

    public PuzzleNode(PuzzleNode parent, Puzzle puzzle, String action){
        this.parent = parent;
        if (parent != null){
            this.pathCost = parent.pathCost+1;
            this.path = parent.getPath() + action;
        }
        this.action = action;
        this.puzzle = puzzle;
    }

    public int getPathCost() {
        return pathCost;
    }

    public Puzzle getPuzzle() {
        return puzzle;
    }

    public PuzzleNode getParent() {
        return parent;
    }

    public boolean equals(PuzzleNode node){
        if (node == null) return false;
        return getPuzzle().equals(node.getPuzzle());
    }

    public String getPath(){
        return path;
    }
}

