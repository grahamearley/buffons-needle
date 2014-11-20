package buffon.Tests;

import buffon.Model;
import buffon.Needle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ModelTest {

    private double boardWidth;
    private double boardHeight;
    private int numberOfSlats;
    private double length;
    private Model theModel;

    private final double delta = 0.00001; // the acceptable error when comparing doubles.

    private Needle intersectNeedle;
    private Needle nonIntersectNeedle;
    private Needle twoSlatNeedle;
    private Needle verticalOnSlatNeedle;

    @Before
    public void initialize() {
        // Set up the board:
        this.boardHeight = 100;
        this.boardWidth = 100;
        this.numberOfSlats = 5;

        // Set up the model:
        this.theModel = new Model();
        this.theModel.setBoardWidth(this.boardWidth);
        this.theModel.setBoardHeight(this.boardHeight);
        this.theModel.setNumberOfSlats(this.numberOfSlats);

        this.length = theModel.calculateSlatDistance();


        // Set up needles for use throughout the tests:

        // Horizontal needle that crosses a slat:
        this.intersectNeedle = new Needle(0.4, 0.5, 0.0, this.length);

        // Vertical needle in between slats:
        this.nonIntersectNeedle = new Needle(0.4, 0.5, 0.25, this.length);

        // Horizontal needle that starts on one slat (the slat
        // running down the middle) and ends exactly on the next slat,
        // touching both:
        this.twoSlatNeedle = new Needle(0.5, 0.5, 0.0, this.length);

        // Vertical needle positioned exactly on a slat.
        this.verticalOnSlatNeedle = new Needle(0.5, 0.5, 0.25, this.length);
    }

    @Test
    public void testIntersectionWithNeedleOnTwoSlats() throws Exception {
        this.theModel.addNeedle(this.twoSlatNeedle);

        // This rare case should only count as one intersection:
        Assert.assertEquals(1, this.theModel.getIntersectionsCount());
    }

    @Test
    public void testIntersectionWithVerticalNeedleOnSlat() throws Exception {
        this.theModel.addNeedle(this.verticalOnSlatNeedle);

        // This rare case should count as one intersection:
        Assert.assertEquals(1, this.theModel.getIntersectionsCount());
    }

    @Test
     public void testIntersectionWithTwoNeedles() throws Exception {
        this.theModel.addNeedle(this.intersectNeedle);
        this.theModel.addNeedle(this.nonIntersectNeedle);

        int intersectionCount = this.theModel.getIntersectionsCount();
        Assert.assertEquals(1, intersectionCount, this.delta);
    }

    @Test
    public void testApproximatePi() throws Exception {
        this.theModel.addNeedle(this.intersectNeedle);
        this.theModel.addNeedle(this.nonIntersectNeedle);
        this.theModel.addNeedle(this.twoSlatNeedle);
        this.theModel.addNeedle(this.verticalOnSlatNeedle);

        // Check the model's calculation with what the approximation
        // should be, given this board with three intersections and four needles.
        double modelApprox = this.theModel.approximatePi();
        double expectedApprox = (2.0 * 4) / (3);

        Assert.assertEquals(expectedApprox, modelApprox, this.delta);
    }

}