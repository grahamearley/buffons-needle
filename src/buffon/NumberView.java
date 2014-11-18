package buffon;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Created by grahamearley on 11/12/14.
 *
 */
public class NumberView extends Group {
    private Model theModel;

    @FXML private double width;
    @FXML private double height;
    private String font;

    final double DEFAULT_WIDTH = 200.0;
    final double DEFAULT_HEIGHT = 200.0;
    final String DEFAULT_FONT_STRING = "Avenir";

    public NumberView() {
        this.width = DEFAULT_WIDTH;
        this.height = DEFAULT_HEIGHT;
        this.font = DEFAULT_FONT_STRING;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getWidth() {
        return this.width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getHeight() {
        return this.height;
    }

    public void setModel(Model model) {
        this.theModel = model;
    }

    public void writeInformation(double boardWidth, double boardHeight) {
        this.getChildren().clear();

        double distanceFromTop = 0; // keeps track of where text already is

        int h1Size = 20;
        int h2Size = 15;
        int padding = 3;

        Integer needles = theModel.getNeedles().size();
        Integer intersections = theModel.getIntersectionsCountWithinWindow(boardWidth, boardHeight);
        Double pi = this.estimatePiWithinWindow(boardWidth, boardHeight);

        // Top header:
        Text mainHeader = new Text(0, distanceFromTop, "Buffon's Needle Approximation");
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

    private double estimatePiWithinWindow(double width, double height) {
        int intersections = theModel.getIntersectionsCountWithinWindow(width, height);
        // Can't divided by zero:
        if (intersections == 0) {
            return 0;
        }

        int totalNeedles = theModel.getNeedles().size();
        double pi = (2.0 * totalNeedles) / intersections;

        return pi;
    }
}
