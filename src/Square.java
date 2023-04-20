import ecs100.UI;

import java.awt.*;
import java.util.Optional;

public class Square {
    public Optional<ChessPiece> piece;
    public Color color;
    int x;
    int y;
    double squareSize;

    public Square(Optional<ChessPiece> piece, Color color, int x, int y, double squareSize) {
        this.piece = piece;
        this.color = color;
        this.x = x;
        this.y = y;
        this.squareSize = squareSize;
    }

    /**
     * Draws this square. Additionally, draws piece if present.
     */
    public void draw() {
        // Draw square
        UI.setColor(color);
        UI.fillRect(x * squareSize, y * squareSize, squareSize, squareSize);

        // Draw piece, highlighted if it matches arg
        piece.ifPresent(ChessPiece::draw);
    }

    public void drawHighlight() {
        UI.setColor(Board.selectedSquareColor);

        UI.fillRect(x * squareSize, y * squareSize, squareSize, squareSize);
        piece.ifPresent(ChessPiece::draw);
    }
}
