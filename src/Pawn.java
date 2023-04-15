import java.awt.Color;
import java.util.ArrayList;

public class Pawn extends ChessPiece {
    Pawn(int x, int y, ChessColor color) {
        super(x, y, color);
        ArrayList<Polygon> polygons = new ArrayList<>();

        // TODO: There's gotta be a DRY way of setting colors
        Color outline;
        Color fill;

        if (color.equals(ChessColor.BLACK)) {
            outline = Color.white;
            fill = Color.black;
        } else {
            outline = Color.black;
            fill = Color.white;
        }

        // I generate the points using https://github.com/ColdMacaroni/draw-poly/ :^)
        polygons.add(
                new Polygon(
                        new double[]{0.188, 0.862, 0.662, 0.82, 0.818, 0.69, 0.314, 0.204, 0.21, 0.368},
                        new double[]{0.772, 0.778, 0.382, 0.262, 0.098, 0.022, 0.02, 0.134, 0.268, 0.364},
                        10,
                        outline,
                        fill,
                        getX() * getSize(),
                        getY() * getSize(),
                        getSize())
        );

        polygons.add(
                new Polygon(
                        new double[]{0.05, 0.93, 0.928, 0.048},
                        new double[]{0.96, 0.96, 0.84, 0.842},
                        4,
                        outline,
                        fill,
                        getX() * getSize(),
                        getY() * getSize(),
                        getSize())
        );

        setPolygons(polygons);
    }
}
