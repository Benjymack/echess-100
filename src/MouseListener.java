import ecs100.UI;

import java.util.Optional;

public class MouseListener {
    private Optional<ChessPiece> selectedPiece;
    private Board currentBoard;

    public void mouseInit(Board currentBoard){
        this.currentBoard = currentBoard;
    }

    public void mousePosition(String action, double x, double y){
        if (action.equals("pressed")){
            this.selectedPiece = currentBoard.getChessPieceFromMouseSquare(x, y); // finds the piece at the current point (might not work idk)
            currentBoard.selectedPiece = this.selectedPiece;
            System.out.println("current piece is " + selectedPiece);
        }
    }
}
