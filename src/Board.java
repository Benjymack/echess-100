import ecs100.UI;
import java.util.HashMap;
import java.awt.*;
import java.util.Optional;
public class Board {
    static Color darkSquareColor = new Color(119, 149, 86);
    static Color lightSquareColor = new Color(235, 236, 208);
    public Square[][] board;
    static int numRows = 8;
    static double boardWidth = 400;
    public Board() {
        this.board = new Square[numRows][numRows];
        for (int y = 0; y<numRows; y++) {
            for (int x = 0; x<numRows; x++) {
                Color squareColor = (y % 2 == x % 2) ? lightSquareColor : darkSquareColor;
                this.board[y][x] = new Square(Optional.empty(), squareColor, x, y, boardWidth/numRows);
            }
        }
        boardFromFEN("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");
    }

    public void boardFromFEN(String FEN) {
//        TODO! Check if this works
        int rank_num = 0;
        int file_num = 0;
        for (String rank : FEN.split("/")) {
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
    public ChessPiece charToChessPiece(char character, int x, int y) {
        ChessColor pieceColor = (Character.isUpperCase(character)) ? ChessColor.WHITE : ChessColor.BLACK;
        HashMap<Character, ChessPiece> characterChessPieceHashMap = new HashMap<Character, ChessPiece>();
        characterChessPieceHashMap.put('p', new Pawn(x, y, pieceColor));
        characterChessPieceHashMap.put('r', new Rook(x, y, pieceColor));
        characterChessPieceHashMap.put('n', new Knight(x, y, pieceColor));
        characterChessPieceHashMap.put('b', new Bishop(x, y, pieceColor));
        characterChessPieceHashMap.put('q', new Queen(x, y, pieceColor));
        characterChessPieceHashMap.put('k', new King(x, y, pieceColor));

        return characterChessPieceHashMap.get(Character.toLowerCase(character));
    }
    public void draw() {
        for (int y = 0; y<numRows; y++) {
            for (int x = 0; x<numRows; x++) {
                this.board[y][x].draw();
            }
        }
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

    public void draw() {
        UI.setColor(color);
        UI.fillRect(x*squareSize, y*squareSize, squareSize, squareSize);
        piece.ifPresent(ChessPiece::draw);
    }
}
