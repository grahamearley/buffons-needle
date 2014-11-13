package sample;

import javafx.scene.shape.Line;

/**
 * Graham Earley, Carleton College, CS257
 *
 * The Needle class contains information that represents each 1-unit-long needle in
 * the simulation. Needles consist of a position and an angle, and a corresponding
 * node object can be generated from this.
 */

public class Needle {
    public double x;
    public double y;
    public double angle; // degrees from vertical (pointing straight up)
    // The x,y values will be the coordinates for the first point of the line.
    //  The second line-point will be calculated from the angle.
    //  For example, if the angle is 0, the bottom point will be the (x,y) coords and the
    //   top point will be generated from the angle.

    public Needle(double x, double y, double angle) {
        this.x = x;
        this.y = y;
        this.angle = angle;
    }

    /**
     * Generate a JavaFX Line object based on the needle's
     * initial X and Y coordinates and its angle.
     *
     * @return the JavaFX Line node to be displayed in the scene.
     */
    public Line getNeedleNode() {
        // stub
        return null;
    }

}
