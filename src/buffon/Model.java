package buffon;

import javafx.scene.shape.Line;

import java.util.ArrayList;

/**
 * Graham Earley, Carleton College, CS257
 *
 * This is the Model for this MVC program. It stores each needle that has been "tossed"
 * onto the "board" and the x-values of the board's slats.
 * It also determines how many intersections there are between slats and needles.
 */
public class Model {
    public ArrayList<Needle> tossedNeedles;
    public double slatDistance;

    public Model() {
        this.tossedNeedles = new ArrayList<Needle>();
    }

    /**
     * Determine the actual x values of each slat within a given window
     * (only the window width is needed to determine the x values).
     *
     * @return an array containing the x values for each slat Line.
     */
    public double[] getSlatXValuesWithinWindow(double width) {
        int numberOfSlats = (int)width / (int)this.slatDistance;

        double[] slatXValues = new double[numberOfSlats];
        for (int i = 0; i < numberOfSlats; i++) {
            slatXValues[i] = i * this.slatDistance;
        }

        return slatXValues;
    }

    /**
     * Look at all the needles and count how many cross a slat in the board.
     *
     * @return the number of intersections between needles and slats.
     */
    public int getIntersectionsCountWithinWindow(double width, double height) {
        int intersections = 0;

        double[] slatXValues = this.getSlatXValuesWithinWindow(width);

        for (Needle needle : tossedNeedles) {
            Line needleLine = needle.getNeedleNode(width, height);
            for (double slat : slatXValues) {
                if ((needleLine.getStartX() <= slat && needleLine.getEndX() >= slat)
                        || needleLine.getEndX() <= slat && needleLine.getStartX() >= slat) {
                    intersections++;
                }
            }
        }
        return intersections;
    }

    public void addNeedle(Needle needle) {
        this.tossedNeedles.add(needle);
    }

    public ArrayList<Needle> getNeedles() {
        return this.tossedNeedles;
    }

    public void setSlatDistance(double distance) {
        this.slatDistance = distance;
    }
}
