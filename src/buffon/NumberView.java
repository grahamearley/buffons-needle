package buffon;

import javafx.scene.Group;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Graham Earley, Carleton College, CS257
 *
 * This class provides a view of information calculated from the
 * simulation: it tells the user how many needles have been thrown,
 * how many intersect, and what the approximation of pi is.
 */
public class NumberView extends Group {
    private Model theModel;
    private final String fontString = "Avenir";

    // Headers:
    private Text mainHeader;
    private Text intersectionHeader;
    private Text totalNeedlesHeader;
    private Text piApproxHeader;

    // Calculation display text:
    private Text intersectionCount;
    private Text needleCount;
    private Text piApproximation;

    public NumberView() {
        // Ensure the text objects are always instantiated for this view:
        this.initializeTextDisplay();
    }

    /**
     * Create the Text objects and position them properly. Calculation result
     * displays are initialized to zero.
     *
     * Subheader texts are followed by the calculations/results.
     */
    public void initializeTextDisplay() {
        // This method is separate from the constructor so that the view
        //  can be reset without reconstruction.

        int h1Size = 20;
        int h2Size = 15;
        int padding = 3;

        this.getChildren().clear(); // ensure no text overlap

        double distanceFromTop = 0; // keeps track of where text already is

        // Top header:
        this.mainHeader = new Text(0, distanceFromTop, "Buffon's Needle Simulation");
        this.mainHeader.setFont(Font.font(this.fontString, FontWeight.EXTRA_BOLD, h1Size));
        distanceFromTop += padding + this.mainHeader.getLayoutBounds().getHeight();
        this.getChildren().add(this.mainHeader);

        // Intersection information:
        this.intersectionHeader = new Text(0, distanceFromTop, "Intersections: ");
        this.intersectionHeader.setFont(Font.font(this.fontString, FontWeight.BOLD, h2Size));
        this.getChildren().add(this.intersectionHeader);

        this.intersectionCount = new Text(this.intersectionHeader.getLayoutBounds().getWidth() + padding, distanceFromTop, "0");
        this.intersectionCount.setFont(Font.font(this.fontString, FontWeight.LIGHT, h2Size));
        distanceFromTop += padding + this.intersectionCount.getLayoutBounds().getHeight();
        this.getChildren().add(this.intersectionCount);

        // Total needle count information:
        this.totalNeedlesHeader = new Text(0, distanceFromTop, "Total Needles: ");
        this.totalNeedlesHeader.setFont(Font.font(this.fontString, FontWeight.BOLD, h2Size));
        this.getChildren().add(this.totalNeedlesHeader);

        this.needleCount = new Text(this.totalNeedlesHeader.getLayoutBounds().getWidth() + padding, distanceFromTop, "0");
        this.needleCount.setFont(Font.font(this.fontString, FontWeight.LIGHT, h2Size));
        distanceFromTop += padding + this.needleCount.getLayoutBounds().getHeight();
        this.getChildren().add(this.needleCount);

        // Pi approximation information:
        this.piApproxHeader = new Text(0, distanceFromTop, "Pi Approximation: ");
        this.piApproxHeader.setFont(Font.font(this.fontString, FontWeight.BOLD, h2Size));
        this.getChildren().add(this.piApproxHeader);

        this.piApproximation = new Text(this.piApproxHeader.getLayoutBounds().getWidth() + padding, distanceFromTop, "0");
        this.piApproximation.setFont(Font.font(this.fontString, FontWeight.LIGHT, h2Size));
        this.getChildren().add(this.piApproximation);
    }

    /**
     * Update the Text objects to display the current calculations.
     */
    public void updateCalculationInformation() {
        // Calculations to display:
        Integer needles = this.theModel.getNeedles().size();
        Integer intersections = this.theModel.getIntersectionsCount();
        Double pi = this.theModel.approximatePi();

        this.needleCount.setText(needles.toString());
        this.intersectionCount.setText(intersections.toString());
        this.piApproximation.setText(pi.toString());
    }

    public void setModel(Model model) {
        this.theModel = model;
    }
}
