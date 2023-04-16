import java.awt.*;
import java.util.ArrayList;

public class ChessPiece {
    private static double size = 20;
    protected final ChessColor color;
    private int x;
    private int y;

    // These may be used as outlines or fills depending on ChessColor
    private static final Color DARK_COLOR = Color.black;
    private static final Color LIGHT_COLOR = Color.white;
    private static final Color HIGHLIGHT_COLOR = Color.decode("#ffc90e");

    protected Color outline;
    protected final Color fill;
    private boolean highlighted = false;

    public char asciiCharacter;
    // Should be set by the subclass
    private ArrayList<Polygon> polygons;

    ChessPiece(int x, int y, ChessColor color) {
        this.color = color;

        this.y = y;
        this.x = x;

        // Set outline and fill depending on piece colour
        if (color.equals(ChessColor.BLACK)) {
            outline = LIGHT_COLOR;
            fill = DARK_COLOR;
        } else {
            outline = DARK_COLOR;
            fill = LIGHT_COLOR;
        }
    }

    /**
     * Draws each of the piece's polygons.
     * It will complain if polygons is null, but won't throw.
     */
    public void draw() {
        if (polygons == null) {
            System.err.printf("`polygons` is null on %s %s\n", getColor().name().toLowerCase(), getClass().getName());
            return;
        }
        for (Polygon poly : polygons) {
            poly.draw();
        }
    }

    public static double getSize() {
        return size;
    }

    public ChessColor getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * Updates `this.points` using x, y, size and norm_points.
     * Assumes that size is the size of each coordinate.
     * */
    public void updatePoints() {
        for (Polygon polygon: polygons) {
            polygon.updatePoints(x * size, y * size, size);
        }
    }

    /**
     * Changes the outline of polygons to `HIGHLIGHT_COLOR`
     * @param highlighted Whether to highlight or not
     */
    public void setHighlighted(boolean highlighted) {
        this.highlighted = highlighted;

        // Change the polygons to use HIGHLIGHT_COLOR
        for (Polygon polygon: polygons) {
            polygon.outline = (highlighted) ? HIGHLIGHT_COLOR : outline;
        }
    }

    public ArrayList<Polygon> getPolygons() {
        return polygons;
    }

    public void setPolygons(ArrayList<Polygon> polygons) {
        this.polygons = polygons;
    }

    public boolean getHighlighted() {
        return highlighted;
    }

    /**
     * Sets piece size, not allowing values below 0.
     * It will complain if new_size < 0, but won't throw.
     * @param new_size The new size
     */
    public static void setSize(double new_size){
        if (new_size < 0)
            // Should I throw?
            System.err.println("Invalid size: " + new_size);

        else
         size = new_size;
    }
}

