import java.util.ArrayList;

public class Bishop extends ChessPiece {
    Bishop(int x, int y, ChessColor color) {
        super(x, y, color);
        this.asciiCharacter = (this.color == ChessColor.BLACK) ? '♝' : '♗';
        ArrayList<Polygon> polygons = new ArrayList<>();


        polygons.add(
                new Polygon(
                        new double[]{0.308, 0.67, 0.578, 0.636, 0.552, 0.572, 0.42, 0.468, 0.392, 0.456, 0.436, 0.372, 0.344, 0.42},
                        new double[]{0.752, 0.752, 0.464, 0.346, 0.068, 0.032, 0.036, 0.068, 0.206, 0.344, 0.364, 0.246, 0.356, 0.46},
                        14,
                        outline,
                        fill,
                        getX() * getSize(),
                        getY() * getSize(),
                        getSize())
        );

        polygons.add(
                new Polygon(
                        new double[]{0.114, 0.108, 0.86, 0.848},
                        new double[]{0.836, 0.928, 0.928, 0.836},
                        4,
                        outline,
                        fill,
                        getX() * getSize(),
                        getY() * getSize(),
                        getSize())
        );

        setPolygons(polygons);
    }

    /*Really terrible bishop moving rules. Probably very ineffecient. lmao*/
    @Override
    public boolean moveOk(Square[][] square, int oldX, int oldY, int newX, int newY) {
        boolean moveOk = false;
        for (int i_X = oldX, i_Y = oldY; i_X >=0 && i_X <= 8 && i_Y >= 0 && i_Y <= 8; ++i_X, ++i_Y){
            if(i_Y == newY && i_X == newX){
                moveOk = true;
                return moveOk;
            }
        }
        for (int i_X = oldX, i_Y = oldY; i_X >=0 && i_X <= 8 && i_Y >= 0 && i_Y <= 8; --i_X, --i_Y){
            if(i_Y == newY && i_X == newX){
                moveOk = true;
                return moveOk;
            }
        }
        for (int i_X = oldX, i_Y = oldY; i_X >=0 && i_X <= 8 && i_Y >= 0 && i_Y <= 8; ++i_X, --i_Y){
            if(i_Y == newY && i_X == newX){
                moveOk = true;
                return moveOk;
            }
        }
        for (int i_X = oldX, i_Y = oldY; i_X >=0 && i_X <= 8 && i_Y >= 0 && i_Y <= 8; --i_X, ++i_Y){
            if(i_Y == newY && i_X == newX){
                moveOk = true;
                return moveOk;
            }
        }
        return moveOk;
    }
    @Override
    public char getFenRepresentation() {
        return (this.color == ChessColor.BLACK) ? 'b' : 'B';
    }

    @Override
    public void drawHighlightedSquares(Square[][] square, int pieceX, int pieceY) {

    }

}
