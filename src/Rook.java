import java.awt.*;
import java.util.ArrayList;

public class Rook extends ChessPiece {
    Rook(int x, int y, ChessColor color) {
        super(x, y, color);
        ArrayList<Polygon> polygons = new ArrayList<>();

        polygons.add(
                new Polygon(
                        new double[]{0.116, 0.866, 0.866, 0.122},
                        new double[]{0.888, 0.89, 0.73, 0.724},
                        4,
                        outline,
                        fill,
                        getX() * getSize(),
                        getY() * getSize(),
                        getSize())
        );

        polygons.add(
                new Polygon(
                        new double[]{0.222, 0.758, 0.702, 0.86, 0.894, 0.728, 0.71, 0.644, 0.658, 0.566, 0.566, 0.462, 0.468, 0.37, 0.37, 0.31, 0.302, 0.12, 0.164, 0.304},
                        new double[]{0.654, 0.654, 0.292, 0.29, 0.086, 0.086, 0.172, 0.168, 0.08, 0.078, 0.164, 0.162, 0.084, 0.09, 0.158, 0.162, 0.078, 0.072, 0.284, 0.284},
                        20,
                        outline,
                        fill,
                        getX() * getSize(),
                        getY() * getSize(),
                        getSize())
        );

        setPolygons(polygons);
    }
}
