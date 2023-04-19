import ecs100.UI;
import java.util.HashMap;
import java.awt.*;
import java.util.Optional;

public class Board {
    static Color darkSquareColor = new Color(119, 149, 86);
    static Color lightSquareColor = new Color(235, 236, 208);
    public Square[][] board;
    private String boardState;

    private static final int NUM_ROWS = 8;
    private static final double BOARD_WIDTH = 400;
    private static final double SQUARE_SIZE = BOARD_WIDTH / NUM_ROWS;

    public Optional<ChessPiece> selectedPiece = Optional.empty();

    /**
     * Creates a new board, populated with alternating colour squares.
     * Additionally, sets the size of ChessPiece to its SQUARE_SIZE
     */
    public Board() {
        this.board = new Square[NUM_ROWS][NUM_ROWS];
        ChessPiece.setSize(SQUARE_SIZE);

        for (int y = 0; y< NUM_ROWS; y++) {
            for (int x = 0; x< NUM_ROWS; x++) {
                Color squareColor = (y % 2 == x % 2) ? lightSquareColor : darkSquareColor;
                this.board[y][x] = new Square(Optional.empty(), squareColor, x, y, SQUARE_SIZE);
            }
        }
        setFEN("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");
    }

    /**
     * Populates the board's squares with pieces according to Forsyth–Edwards Notation.
     * More info in <a href="https://en.wikipedia.org/wiki/Forsyth%E2%80%93Edwards_Notation">Wikipedia</a>.
     */
    public void boardFromFEN() {
        int rank_num = 0;
        int file_num = 0;

        for (String rank : boardState.split("/")) {
            file_num = 0;
            for (Character info : rank.toCharArray()) {
                if (Character.isDigit(info)) {
                    file_num += Character.digit(info, 10);
                    continue;
                }
                board[rank_num][file_num].piece = Optional.of(charToChessPiece(info, file_num, rank_num));
                file_num++;
            }
            rank_num++;
        }
    }

    /**
     *
     * @param character One of  p, r, n, b, q, or k. May be uppercase or lowercase.
     * @param x Rank of the piece
     * @param y File of the piece
     * @return The ChessPiece as specified by the notation
     * @throws IllegalStateException Throws if an invalid char is given
     */
    public ChessPiece charToChessPiece(char character, int x, int y) throws IllegalStateException {
        // Pick colour based on case
        ChessColor pieceColor = (Character.isUpperCase(character)) ? ChessColor.WHITE : ChessColor.BLACK;

        // Create and return ChessPiece
        return switch (Character.toLowerCase(character)) {
            case 'p' -> new Pawn(x, y, pieceColor);
            case 'r' -> new Rook(x, y, pieceColor);
            case 'n' -> new Knight(x, y, pieceColor);
            case 'b' -> new Bishop(x, y, pieceColor);
            case 'q' -> new Queen(x, y, pieceColor);
            case 'k' -> new King(x, y, pieceColor);
            default -> throw new IllegalStateException(character + ": is not a valid chess piece");
        };

    }

    /** Draw the board to the screen Square by Square.
     */
    public void draw() {
        for (int y = 0; y< NUM_ROWS; y++) {
            for (int x = 0; x< NUM_ROWS; x++) {
                // We pass the current selected piece so it's highlighted
                this.board[y][x].draw();
            }
        }
    }

    /**
     * Returns the piece that the user clicks on.
     * @param mouseX The x coordinate of the mouse.
     * @param mouseY The y coordinate of the mouse.
     * @return An optional which contains the piece if the square the user clicked on had one
     */
    public Optional<ChessPiece> getChessPieceFromMouseSquare(double mouseX, double mouseY) {
        // Converts the mouse coordinate into a chess board coordinate.
        int x = (int) Math.floor(mouseX / SQUARE_SIZE);
        int y = (int) Math.floor(mouseY / SQUARE_SIZE);

        return getChessPiece(x, y);
    }

    public Optional<ChessPiece> getChessPiece(int x, int y) {
        // Return piece at that position, or empty Optional if outside board.
        if (x < NUM_ROWS && y < NUM_ROWS) {
            return board[y][x].piece;
        }
        return Optional.empty();
    }


    public boolean inCheck(ChessPiece king) {
        // TODO! should this be moved to a method of the board class?
        if (straightCheck(king)) return true;
        if (diagonalCheck(king)) return true;
        if (knightCheck(king)) return true;
        return false;
    }
    boolean straightCheck(ChessPiece king) {
        return
            // Look Up
            lookFromKingY(-1, king) ||
            // Look Down
            lookFromKingY(1, king) ||
            // Look Left
            lookFromKingX(-1, king) ||
            // Look Right
            lookFromKingX(1, king);
    }

    boolean lookFromKingX(int step, ChessPiece king) {
        for (int lookX = king.getX(); lookX > 0 && lookX < Board.NUM_ROWS; lookX += step) {
            Optional<ChessPiece> piece = getChessPiece(lookX, king.getY());
            // Ignore blank squares
            if (piece.isEmpty()) continue;

            ChessPiece chessPiece = piece.get();

            // Your own pieces block checks
            if (chessPiece.color == king.color) return false;

            // If the piece is a rook or queen then the king is in check
            return (chessPiece instanceof Rook || chessPiece instanceof Queen);
        }
        return false;
    }
    boolean lookFromKingY(int step, ChessPiece king) {
        for (int lookY = king.getY(); lookY > 0 && lookY < Board.NUM_ROWS; lookY += step) {
            Optional<ChessPiece> piece = getChessPiece(king.getX(), lookY);
            // Ignore blank squares
            if (piece.isEmpty()) continue;

            ChessPiece chessPiece = piece.get();

            // Your own pieces block checks
            if (chessPiece.color == king.color) return false;

            // If the piece is a rook or queen then the king is in check
            return (chessPiece instanceof Rook || chessPiece instanceof Queen);
        }
        return false;
    }

    boolean diagonalCheck(ChessPiece king) {
        for (int yStep : new int[]{-1, 1}) {
            for (int xStep : new int[]{-1, 1}) {
                // If the king is checked on any of the diagonals it is in check
                if (bishopQueenOnDiagonals(king, yStep, yStep)) return true;
            }
        }
        return false;
    }
    boolean bishopQueenOnDiagonals(ChessPiece king, int xStep, int yStep) {
        for (int lookX = king.getX(), lookY = king.getY();  lookY > 0 && lookY < Board.NUM_ROWS; lookX += xStep, lookY += yStep) {
            Optional<ChessPiece> piece = getChessPiece(lookX, lookY);
            // Ignore blank squares
            if (piece.isEmpty()) continue;

            ChessPiece chessPiece = piece.get();

            // Your own pieces block checks
            if (chessPiece.color == king.color) return false;

            // If the piece is a rook or queen then the king is in check
            return (chessPiece instanceof Bishop || chessPiece instanceof Queen);
        }
        return false;
    }
    boolean knightCheck(ChessPiece king) {
        // NOTE: this is {x,y} form
        int[][] positionsKnightAttack = new int[][] {
                // Down
                {1, 2},
                {-1, 2},
                // Right
                {2, 1},
                {2, -1},
                // Up
                {1, -2},
                {-1, -2},
                // Left
                {-2, 1},
                {-2, -1},
        };
        for (int[] indiciesOfAttack : positionsKnightAttack) {
            Optional<ChessPiece> piece = getChessPiece(king.getX() + indiciesOfAttack[0], king.getY() + indiciesOfAttack[1]);
            if (piece.isEmpty()) continue;
            ChessPiece chessPiece = piece.get();

            // The king is in check if it can be attacked by a knight of the opposite colour
            if (chessPiece instanceof Knight && chessPiece.color != king.color) return true;
        }
        return false;
    }

    public void setFEN(String fen) {
        boardState = fen;
        boardFromFEN();
    }

    public String getFEN() {
        return boardState;
    }

}

class Square {
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
}
