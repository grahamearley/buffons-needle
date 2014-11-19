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
//    TODO: Add Board dimensions?
    public double boardWidth;
    public double boardHeight;

    public Model() {
        this.tossedNeedles = new ArrayList<Needle>();
    }

    /**
     * Determine the actual x values of each slat within a given window
     * (only the window width is needed to determine the x values).
     *
     * @return an array containing the x values for each slat Line.
     */
    public double[] getSlatXValuesWithinWindow() {
        int numberOfSlats = (int)this.boardWidth / (int)this.slatDistance;

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
    public int getIntersectionsCount() {
        int intersections = 0;

        double[] slatXValues = this.getSlatXValuesWithinWindow();

        for (Needle needle : tossedNeedles) {
            Line needleLine = needle.getNeedleNode(this.boardWidth, this.boardHeight);
            for (double slat : slatXValues) {
                if ((needleLine.getStartX() <= slat && needleLine.getEndX() >= slat)
                        || needleLine.getEndX() <= slat && needleLine.getStartX() >= slat) {
                    intersections++;
                }
            }
        }
        return intersections;
    }

    public double approximatePi() {
        int intersections = this.getIntersectionsCount();

        // Can't divided by zero:
        if (intersections == 0) {
            return 0;
        }

        int totalNeedles = this.getNeedles().size();
        double pi = (2.0 * totalNeedles) / intersections;

        return pi;
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

    public void setBoardWidth(double width) {
        this.boardWidth = width;
    }

    public double getBoardWidth() {
        return this.boardWidth;
    }

    public void setBoardHeight(double height) {
        this.boardHeight = height;
    }

    public double getBoardHeight() {
        return this.boardHeight;
    }
}
