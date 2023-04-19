import ecs100.*;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        // Magic Here
        System.out.println("Hello world!");

        // All the pieces and drawing logic goes here
        Board board = new Board();

        // For knowing when a piece is clicked
        MouseListener mouse = new MouseListener();
        mouse.mouseInit(board);
        UI.setMouseListener(mouse::mousePosition);

        // Set up for main game loop
        final double FRAMES_PER_SECOND = 30;
        final long MILLIES_BETWEEN_FRAMES = Math.round(1000. / FRAMES_PER_SECOND);

        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        executorService.scheduleAtFixedRate(
                () -> gameTick(board),
                0,
                MILLIES_BETWEEN_FRAMES,
                TimeUnit.MILLISECONDS
        );
    }

    /**
     * The main game loop
     * @param board The board used for the game
     */
    static void gameTick(Board board) {
        board.draw();
    }
}