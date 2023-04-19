import java.awt.*;
import java.util.ArrayList;

public class King extends ChessPiece {
    public boolean inCheck = false;
    private final Color CHECK_COLOR = Color.RED;
    King(int x, int y, ChessColor color) {
        super(x, y, color);
        ArrayList<Polygon> polygons = new ArrayList<>();
        this.asciiCharacter = (this.color == ChessColor.BLACK) ? '♚' : '♔';

        polygons.add(
                new Polygon(
                        new double[]{0.06, 0.052, 0.932, 0.926},
                        new double[]{0.852, 0.98, 0.972, 0.848},
                        4,
                        outline,
                        fill,
                        getX() * getSize(),
                        getY() * getSize(),
                        getSize())
        );

        polygons.add(
                new Polygon(
                        new double[]{0.238, 0.778, 0.904, 0.914, 0.822, 0.712, 0.694, 0.556, 0.556, 0.656, 0.656, 0.56, 0.564, 0.484, 0.492, 0.4, 0.412, 0.5, 0.5, 0.388, 0.346, 0.25, 0.14, 0.156},
                        new double[]{0.802, 0.802, 0.628, 0.516, 0.444, 0.498, 0.404, 0.412, 0.332, 0.324, 0.268, 0.27, 0.18, 0.186, 0.272, 0.282, 0.334, 0.328, 0.414, 0.412, 0.516, 0.452, 0.546, 0.67},
                        24,
                        outline,
                        fill,
                        getX() * getSize(),
                        getY() * getSize(),
                        getSize())
        );
        setPolygons(polygons);
    }

    public void draw() {
        if (polygons == null) {
            System.err.printf("`polygons` is null on %s %s\n", getColor().name().toLowerCase(), getClass().getName());
            return;
        }
        for (Polygon polygon: polygons) {
            polygon.outline = (inCheck && !getHighlighted()) ? CHECK_COLOR : polygon.outline;
        }
        for (Polygon poly : polygons) {
            poly.draw();
        }
    }

    @Override
    public boolean moveOk(Square[][] square, int oldX, int oldY, int newX, int newY) {
        int dx = Math.abs(newX - oldX);
        int dy = Math.abs(newY - oldY);
        if (dx <= 1 && dy <= 1) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public char getFenRepresentation() {
        return (this.color == ChessColor.BLACK) ? 'k' : 'K';
    }

    @Override
    public void drawHighlightedSquares(Square[][] square, int pieceX, int pieceY) {

    }
}
