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

    public void writeHeaders() {
        double distanceFromTop = 0;
        int h1Size = 20;
        int h2Size = 15;
        int padding = 3;

        Text mainHeader = new Text(0, distanceFromTop, "Buffon's Needle Approximation");
        mainHeader.setFont(Font.font(this.font, FontWeight.EXTRA_BOLD, h1Size));
        distanceFromTop += h1Size + padding;
        this.getChildren().add(mainHeader);

        Text intersectionHeader = new Text(0, distanceFromTop, "Intersections: ");
        intersectionHeader.setFont(Font.font(this.font, FontWeight.BOLD, h2Size));
        distanceFromTop += h2Size + padding;
        this.getChildren().add(intersectionHeader);

        Text totalNeedlesHeader = new Text(0, distanceFromTop, "Total Needles: ");
        totalNeedlesHeader.setFont(Font.font(this.font, FontWeight.BOLD, h2Size));
        distanceFromTop += h2Size + padding;
        this.getChildren().add(totalNeedlesHeader);

        Text piApproxHeader = new Text(0, distanceFromTop, "Pi Approximation: ");
        piApproxHeader.setFont(Font.font(this.font, FontWeight.BOLD, h2Size));
        distanceFromTop += h2Size + padding;
        this.getChildren().add(piApproxHeader);
    }

    public void displayInformation(double boardWidth, double boardHeight) {
        double pi = this.estimatePiWithinWindow(boardWidth, boardHeight);
        int intersections = theModel.getIntersectionsCountWithinWindow(boardWidth, boardHeight);
        int needleCount = theModel.getNeedles().size();

        String printString = String.format("Pi approximation: %s\n" +
                                            "Intersections: %s\n" +
                                            "Total Needles: %s",
                                             pi, intersections, needleCount);

        Text piReader = new Text();
        piReader.setWrappingWidth(200);
        piReader.setText(printString);

//        this.getChildren().clear();
//        this.getChildren().add(piReader);
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
