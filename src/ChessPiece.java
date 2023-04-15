import ecs100.*;

// Not sure if i'm doing abstract right
public abstract class ChessPiece {
    private static double size = 20;
    private final ChessColor color;
    private int x;
    private int y;

    // Dimensions:
    //  1: Groups
    //  2: Points
    //  3: X,Y
    // Should be set by the subclass
    private double[][][] points;

    ChessPiece(int x, int y, ChessColor color) {
        this.color = color;

        this.y = y;
        this.x = x;
    }

    public abstract void draw();

    public void setSize(double new_size){
        if (new_size < 0)
            size = new_size;
        else
            // Should I throw?
            System.err.println("Invalid size: " + new_size);
    }
}
