import ecs100.UI;

import java.util.Optional;

public class MouseListener {
    private Optional<ChessPiece> selectedPiece;
    private Board currentBoard;

    public void mouseInit(Board currentBoard){
        this.currentBoard = currentBoard;
    }

    /**
     * Method to be passed to UI.setMouseListener
     * Sets `this.selectedPiece` and `currentBoard.selectedPiece` to the one under the mouse when clicked.
     * @param action Set by UI.setMouseListener
     * @param x Set by UI.setMouseListener
     * @param y Set by UI.setMouseListener
     */
    public void mousePosition(String action, double x, double y) {
        // Only get piece when clicked
        if (action.equals("pressed")){
            this.selectedPiece = currentBoard.getChessPieceFromMouseSquare(x, y);
            currentBoard.selectedPiece = this.selectedPiece;

            System.out.println("current piece is " + selectedPiece);
        }
    }
}
