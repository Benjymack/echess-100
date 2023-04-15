import ecs100.*;
public class Main {
    public static void main(String[] args) {
        // Magic Here
        System.out.println("Hello world!");
        Board board = new Board();
        board.draw();
        MouseListener mouse = new MouseListener();
        mouse.mouseInit(board);
        UI.setMouseListener(mouse::mousePosition);
    }



}