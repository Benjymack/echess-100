import java.awt.*;
import java.util.ArrayList;

public class ChessPiece {
    private static double size = 20;
    protected final ChessColor color;
    private int x;
    private int y;
    protected Color outline;
    protected final Color fill;

    public char asciiCharacter;
    // Should be set by the subclass
    private ArrayList<Polygon> polygons;

    ChessPiece(int x, int y, ChessColor color) {
        this.color = color;

        this.y = y;
        this.x = x;

        // Set outline and fill depending on piece colour
        if (color.equals(ChessColor.BLACK)) {
            outline = Color.white;
            fill = Color.black;
        } else {
            outline = Color.black;
            fill = Color.white;
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

    /**
     * Draws the polygon with the given outline colour.
     * WET code, TODO!
     * @param outlineColor Replaces outline colour
     */
    public void draw(Color outlineColor) {
//        TODO! This isn't very dry...
        if (polygons == null) {
            System.err.printf("`polygons` is null on %s %s\n", getColor().name().toLowerCase(), getClass().getName());
            return;
        }
        for (Polygon poly : polygons) {
            poly.draw(outlineColor);
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

    public ArrayList<Polygon> getPolygons() {
        return polygons;
    }

    public void setPolygons(ArrayList<Polygon> polygons) {
        this.polygons = polygons;
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

