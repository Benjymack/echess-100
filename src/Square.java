import ecs100.UI;

import java.awt.*;
import java.util.Optional;

public class Square {
    public Optional<ChessPiece> piece;
    public Color color;
    int x;
    int y;
    double squareSize;
    public boolean moveableTo = false;
    double circle_radius;
    public Square(Optional<ChessPiece> piece, Color color, int x, int y, double squareSize) {
        this.piece = piece;
        this.color = color;
        this.x = x;
        this.y = y;
        this.squareSize = squareSize;
        this.circle_radius = squareSize / 6.;
    }

    /**
     * Draws this square. Additionally, draws piece if present.
     */
    public void draw() {
        // Draw square
        UI.setColor(color);

        double x_pos = x * squareSize;
        double y_pos = y * squareSize;
        UI.fillRect(x_pos, y_pos, squareSize, squareSize);
        piece.ifPresent(ChessPiece::draw);
        // Draw piece, highlighted if it matches arg
        piece.ifPresent(ChessPiece::draw);

        // draw a little circle if you can move to that square
        if (moveableTo) {
            UI.setColor(new Color(100, 100, 100, 100));
            UI.fillOval(
                    x_pos - circle_radius + squareSize / 2.,
                    y_pos - circle_radius + squareSize / 2.,
                    2 * circle_radius,
                    2 * circle_radius
            );
        }
    }
}
