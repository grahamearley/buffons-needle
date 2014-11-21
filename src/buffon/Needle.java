package buffon;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * Graham Earley, Carleton College, CS257
 *
 * The Needle class contains information that represents each needle in
 * relation to the window.
 *
 * The Needle's values are percentages that are generated uniformly
 * from 0 to 1; with the window size, these values can be used to
 * generate the Line object to be displayed and position it in a window.
 * The percentages keep the Needle modular.
 */

public class Needle {
    public double xPercent;
    public double yPercent;
    public double anglePercent; // degrees counter-clockwise from horizontal (pointing right)
    public double length;
    public Color color;

    public Needle(double xPercent, double yPercent, double anglePercent, double length) {
        this.xPercent = xPercent;
        this.yPercent = yPercent;
        this.anglePercent = anglePercent;
        this.length = length;
        this.color = Color.BLACK;
    }

    /**
     * Use the passed-in window height and width to determine
     * the needle's position, then assemble the Line object that
     * represents the needle.
     *
     * @param windowWidth The width of the window this needle Line will appear in
     * @param windowHeight The height of the window this needle Line will appear in
     * @return the JavaFX Line node to be displayed in the scene.
     */
    public Line getNeedleNode(double windowWidth, double windowHeight) {
        double angle = this.anglePercent * (2 * Math.PI);
        double startX = this.xPercent * windowWidth;
        double startY = this.yPercent * windowHeight;
        double endX = startX + this.length * Math.cos(angle);
        double endY = startY + this.length * Math.sin(angle);

        Line needleLine = new Line(startX, startY, endX, endY);
        needleLine.setStroke(this.color);

        return needleLine;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}
