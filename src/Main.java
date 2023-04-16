import ecs100.*;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        // Magic Here
        System.out.println("Hello world!");
        Board board = new Board();
        MouseListener mouse = new MouseListener();
        mouse.mouseInit(board);
        UI.setMouseListener(mouse::mousePosition);

        final double FRAMES_PER_SECOND = 30;
        final long MILLIES_BETWEEN_FRAMES = Math.round(1000. / FRAMES_PER_SECOND);
        double START_TIME = System.currentTimeMillis();
        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(
                () -> gameTick(board),
                0,
                MILLIES_BETWEEN_FRAMES,
                TimeUnit.MILLISECONDS
        );
    }

    static void gameTick(Board board) {
        board.draw();
    }
}