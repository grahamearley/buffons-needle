package buffon;

import javafx.scene.shape.Line;
import java.util.ArrayList;

/**
 * Graham Earley, Carleton College, CS257
 *
 * This is the Model for this MVC program. It stores the information from
 * the simulation environment (i.e. the board dimensions, the needles on
 * the board, and the positions of the board's slats).
 *
 * It also approximates pi and determines how many intersections there are between slats and needles.
 */
public class Model {
    public ArrayList<Needle> tossedNeedles;
    public int numberOfSlats;
    public double boardWidth;
    public double boardHeight;

    public Model(int numberOfSlats, double boardWidth, double boardHeight) {
        this.numberOfSlats = numberOfSlats;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.tossedNeedles = new ArrayList<Needle>();
    }

    /**
     * Determine the x values of each slat within the board view's Rectangle.
     * Slats are evenly spaced on the board.
     *
     * @return an array containing the x values for each slat Line.
     */
    public double[] getSlatXValues() {
        double distanceBetweenSlats = this.calculateDistanceBetweenSlats();
        double[] slatXValues = new double[this.numberOfSlats];

        for (int i = 0; i < this.numberOfSlats; i++) {
            slatXValues[i] = i * distanceBetweenSlats;
        }

        return slatXValues;
    }

    /**
     * Count intersections by looking at all the needle Lines' x values
     * and checking how many cross a slat in the board.
     *
     * @return the number of intersections between needles and slats.
     */
    public int getIntersectionsCount() {
        int intersections = 0;
        double[] slatXValues = this.getSlatXValues();

        for (Needle needle : this.tossedNeedles) {
            for (double slat : slatXValues) {
                if (this.isIntersection(needle, slat)) {
                    intersections++;
                }
            }
        }
        return intersections;
    }

    /**
     * Approximate pi using the information in the simulation.
     * For more about the formula, see http://en.wikipedia.org/wiki/Buffon's_needle
     *
     * @return an approximation of pi from the simulation.
     */
    public double approximatePi() {
        int intersections = this.getIntersectionsCount();

        // Can't divide by zero:
        if (intersections == 0) {
            return 0;
        }

        int totalNeedles = this.getNeedles().size();
        double pi = (2.0 * totalNeedles) / intersections;

        return pi;
    }

    /**
     * Calculate the distance between slats, which are evenly spaced on
     * the board.
     *
     * This calculation is separated for readability.
     *
     * @return the distance between each slat.
     */
    public double calculateDistanceBetweenSlats() {
        // The denominator is (this.numberOfSlats - 1) so that the final slat
        // goes on the edge of the board:
        return this.boardWidth / (this.numberOfSlats - 1);
    }

    /**
     * Check if a needle Line intersects with a slat by comparing
     * x values.
     *
     * This calculation is separated for readability.
     *
     * @param needle The needle to check
     * @param slatXValue The slat to check
     * @return a boolean for whether the needle and slat intersect (true) or not (false)
     */
    public boolean isIntersection(Needle needle, double slatXValue) {
        Line needleLine = needle.getNeedleNode(this.boardWidth, this.boardHeight);

        // Only the beginning coordinate is checked for being directly on the slat (<= rather than <) so that no
        //  intersection is counted twice (this protects against counting two intersections when a horizontal needle
        //  starts on one slat and ends directly on the next). The final expression in this logic statement covers
        //  the case where a vertical needle is directly on top of a slat.
        return (needleLine.getStartX() <= slatXValue && needleLine.getEndX() > slatXValue)
            || (needleLine.getEndX() <= slatXValue && needleLine.getStartX() > slatXValue)
            || (needleLine.getStartX() == slatXValue && needleLine.getEndX() == slatXValue);
    }

    public void addNeedle(Needle needle) {
        this.tossedNeedles.add(needle);
    }

    public ArrayList<Needle> getNeedles() {
        return this.tossedNeedles;
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
