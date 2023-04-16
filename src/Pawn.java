import java.awt.Color;
import java.util.ArrayList;

public class Pawn extends ChessPiece {
    public Pawn(int x, int y, ChessColor color) {
        super(x, y, color);
        this.asciiCharacter = (this.color == ChessColor.BLACK) ? '♟' : '♙';
        ArrayList<Polygon> polygons = new ArrayList<>();


        // I generate the points using https://github.com/ColdMacaroni/draw-poly/ :^)
        polygons.add(
                new Polygon(
                        new double[]{0.256, 0.772, 0.62, 0.722, 0.726, 0.658, 0.382, 0.33, 0.332, 0.44},
                        new double[]{0.752, 0.756, 0.466, 0.4, 0.306, 0.228, 0.224, 0.296, 0.378, 0.444},
                        10,
                        outline,
                        fill,
                        getX() * getSize(),
                        getY() * getSize(),
                        getSize())
        );

        polygons.add(
                new Polygon(
                        new double[]{0.134, 0.128, 0.872, 0.874},
                        new double[]{0.8, 0.928, 0.926, 0.804},
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
