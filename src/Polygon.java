import ecs100.UI;

import java.awt.*;
import java.util.ArrayList;

/** A class for managing polygons for UI poly methods*/
public class Polygon {
    private double[] pointsX;
    private double[] pointsY;

    private final int numPoints;

    private final double[] normalPointsX;
    private final double[] normalPointsY;

    Color outline;
    Color fill;

    Polygon(double[] normalPointsX, double[]normalPointsY, int numPoints, Color outline, Color fill,
            double left, double top, double size) {
        this.normalPointsX = normalPointsX;
        this.normalPointsY = normalPointsY;

        this.numPoints = numPoints;

        this.outline = outline;
        this.fill = fill;

        updatePoints(left, top, size);
    }

    /**
     * Computers the normalised points into points that can be drawn at any given position.
     * This method should be called whenever the poly is meant to be drawn at a different position.
     * The size parameter is because,,, it's normalised 0 <= x <= 1.
     */
    public void updatePoints(double left, double top, double size) {
        double[] newPointsX = new double[numPoints];
        double[] newPointsY = new double[numPoints];

        // Do the math!
        for (int i = 0; i < numPoints; i++) {
            newPointsX[i] = left + normalPointsX[i] * size;
            newPointsY[i] = top + normalPointsY[i] * size;
        }

        this.pointsX = newPointsX;
        this.pointsY = newPointsY;
    }

    public void draw() {
        // Fill first so the outline is nice and clear
        UI.setColor(fill);
        UI.fillPolygon(pointsX, pointsY, numPoints);

        // Draw the outline
        UI.setColor(outline);
        UI.drawPolygon(pointsX, pointsY, numPoints);
    }

}
