package sample;

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
    public ArrayList<Integer> slatXValues;

    public Model() {
        this.tossedNeedles = new ArrayList<Needle>();
        this.slatXValues = new ArrayList<Integer>();
    }

    /**
     * Look at all the needles and count how many cross a slat in the board.
     *
     * @return the number of intersections between needles and slats.
     */
    public int getIntersectionsCount() {
        // stub
        return 0;
    }

    public void addNeedle(Needle needle) {
        this.tossedNeedles.add(needle);
    }

    public void addSlatXValue(Integer x) {
        this.slatXValues.add(x);
    }

}
