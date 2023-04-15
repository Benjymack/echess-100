import java.awt.*;
import java.util.ArrayList;

public class Knight extends ChessPiece {
    Knight(int x, int y, ChessColor color) {
        super(x, y, color);
        ArrayList<Polygon> polygons = new ArrayList<>();

        Color outline;
        Color fill;

        if (color.equals(ChessColor.BLACK)) {
            outline = Color.white;
            fill = Color.black;
        } else {
            outline = Color.black;
            fill = Color.white;
        }

        polygons.add(
                new Polygon(
                        new double[]{0.12, 0.13, 0.85, 0.882},
                        new double[]{0.852, 0.948, 0.948, 0.856},
                        4,
                        outline,
                        fill,
                        getX() * getSize(),
                        getY() * getSize(),
                        getSize())
        );

        polygons.add(
                new Polygon(
                        new double[]{0.278, 0.756, 0.694, 0.762, 0.736, 0.768, 0.67, 0.444, 0.172, 0.148, 0.21, 0.316, 0.384, 0.508},
                        new double[]{0.794, 0.792, 0.416, 0.32, 0.158, 0.082, 0.13, 0.18, 0.318, 0.412, 0.496, 0.486, 0.404, 0.404},
                        14,
                        outline,
                        fill,
                        getX() * getSize(),
                        getY() * getSize(),
                        getSize())
        );

        polygons.add(
                new Polygon(
                        new double[]{0.516, 0.56, 0.616, 0.566},
                        new double[]{0.292, 0.312, 0.288, 0.26},
                        4,
                        fill, // This the eye, so we're flipping them for contrast
                        outline,
                        getX() * getSize(),
                        getY() * getSize(),
                        getSize())
        );

        setPolygons(polygons);
    }
}
