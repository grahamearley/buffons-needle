package buffon;

import javafx.scene.Group;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Graham Earley, Carleton College, CS257
 *
 * This class handles the view of information calculated
 * from the simulation. It tells the user how many needles have been
 * thrown, how many intersect, and what the approximation of pi is.
 */
public class NumberView extends Group {
    private Model theModel;
    private String font;

    public NumberView() {
        this.font = "Avenir";
    }

    public void setModel(Model model) {
        this.theModel = model;
    }

    public void writeInformation() {
        this.getChildren().clear();

        double distanceFromTop = 0; // keeps track of where text already is

        int h1Size = 20;
        int h2Size = 15;
        int padding = 3;

        Integer needles = theModel.getNeedles().size();
        Integer intersections = theModel.getIntersectionsCount();
        Double pi = theModel.approximatePi();

        // Top header:
        Text mainHeader = new Text(0, distanceFromTop, "Buffon's Needle Simulation");
        mainHeader.setFont(Font.font(this.font, FontWeight.EXTRA_BOLD, h1Size));
        distanceFromTop += h1Size + padding;
        this.getChildren().add(mainHeader);

        // Intersection information:
        Text intersectionHeader = new Text(0, distanceFromTop, "Intersections: ");
        intersectionHeader.setFont(Font.font(this.font, FontWeight.BOLD, h2Size));
        this.getChildren().add(intersectionHeader);

        Text intersectionCount = new Text(intersectionHeader.getLayoutBounds().getWidth() + padding, distanceFromTop, intersections.toString());
        intersectionCount.setFont(Font.font(this.font, FontWeight.LIGHT, h2Size));
        distanceFromTop += h2Size + padding;
        this.getChildren().add(intersectionCount);

        // Total needle count information:
        Text totalNeedlesHeader = new Text(0, distanceFromTop, "Total Needles: ");
        totalNeedlesHeader.setFont(Font.font(this.font, FontWeight.BOLD, h2Size));
        this.getChildren().add(totalNeedlesHeader);

        Text needleCount = new Text(totalNeedlesHeader.getLayoutBounds().getWidth() + padding, distanceFromTop, needles.toString());
        needleCount.setFont(Font.font(this.font, FontWeight.LIGHT, h2Size));
        distanceFromTop += h2Size + padding;
        this.getChildren().add(needleCount);

        // Pi approximation information:
        Text piApproxHeader = new Text(0, distanceFromTop, "Pi Approximation: ");
        piApproxHeader.setFont(Font.font(this.font, FontWeight.BOLD, h2Size));
        this.getChildren().add(piApproxHeader);

        Text piApproximation = new Text(piApproxHeader.getLayoutBounds().getWidth() + padding, distanceFromTop, pi.toString());
        piApproximation.setFont(Font.font(this.font, FontWeight.LIGHT, h2Size));
        this.getChildren().add(piApproximation);
    }

}
