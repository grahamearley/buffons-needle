package buffon;

import javafx.scene.shape.Line;

/**
 * Graham Earley, Carleton College, CS257
 *
 * The Needle class contains information that represents each needle in
 * relation to the window. The Needle's values are percentages that are
 * generated uniformly on 0 to 1; using the window size, these values can
 * be used to generate the Line object to be displayed.
 */

public class Needle {
    public double xPercent;
    public double yPercent;
    public double anglePercent; // degrees CCW from horizontal (pointing right)
    public double length;
    // These values are percentages so that the View can determine where to put
    //  the Needle's Line object within the window. It keeps things separate.

    public Needle(double xPercent, double yPercent, double anglePercent, double length) {
        this.xPercent = xPercent;
        this.yPercent = yPercent;
        this.anglePercent = anglePercent;
        this.length = length;
    }

    /**
     * Use the passed-in window height and width to determine
     * the needle's position, then assemble the Line object that
     * represents the needle.
     *
     * @return the JavaFX Line node to be displayed in the scene.
     */
    public Line getNeedleNode(double windowWidth, double windowHeight) {
        double angle = this.anglePercent * Math.PI*2;
        double startX = windowWidth * this.xPercent;
        double startY = windowHeight * this.yPercent;
        double endX = startX + this.length * Math.cos(angle);
        double endY = startY + this.length * Math.sin(angle);

        Line needleLine = new Line(startX, startY, endX, endY);

        return needleLine;
    }

}
