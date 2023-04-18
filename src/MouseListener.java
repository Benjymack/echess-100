import ecs100.UI;

import java.util.Optional;

public class MouseListener {
    private Optional<ChessPiece> selectedPiece = Optional.empty();
    private Board currentBoard;
    private boolean Highlighted = false;

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
            if(selectedPiece.isPresent() && selectedPiece.get().getHighlighted() && currentBoard.getChessPieceFromMouseSquare(x, y).isEmpty()) {
                System.out.println("this would make it move");
            }
            else {
                // Unhighlight previously selected piece
                selectedPiece.ifPresent(chessPiece -> chessPiece.setHighlighted(false));
                selectedPiece = currentBoard.getChessPieceFromMouseSquare(x, y);
                // Highlight currently selected piece
                selectedPiece.ifPresent(chessPiece -> chessPiece.setHighlighted(true));
            }
        }
    }
}
