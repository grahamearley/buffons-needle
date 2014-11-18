package buffon;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

/**
 * Created by grahamearley on 11/12/14.
 */
public class BoardView extends Group {
    private Model theModel;

    @FXML private double width;
    @FXML private double height;
    @FXML private Color borderColor;
    @FXML private Color backgroundColor;

    final double DEFAULT_WIDTH = 175.0;
    final double DEFAULT_HEIGHT = 200.0;
    final Color DEFAULT_BORDER_COLOR = Color.BLACK;
    final Color DEFAULT_BACKGROUND_COLOR = Color.SANDYBROWN;

    public BoardView() {
        this.width = DEFAULT_WIDTH;
        this.height = DEFAULT_HEIGHT;
        this.borderColor = DEFAULT_BORDER_COLOR;
        this.backgroundColor = DEFAULT_BACKGROUND_COLOR;
    }

    // Getters and setters used by the @FXML tag:
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

    public Color getBorderColor() {
        return this.borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public Color getBackgroundColor() {
        return this.backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setModel(Model model) {
        this.theModel = model;
    }


    public void drawSlats() {
        double[] slatXValues = theModel.getSlatXValuesWithinWindow(this.width);
        for (double slatXValue : slatXValues) {
            Line slat = new Line(slatXValue, 0, slatXValue, this.height);
            this.getChildren().add(slat);
        }
    }

    public void drawBorder() {
        Rectangle border = new Rectangle(0.0, 0.0, this.getWidth(), this.getHeight());
        border.setFill(this.getBackgroundColor());
        border.setLayoutX(0);
        border.setLayoutY(0);
        border.setStroke(this.getBorderColor());
        border.setStrokeWidth(2.0);
        this.getChildren().add(border);
    }

    public void drawNeedle(Needle needle) {
        Line needleLine = needle.getNeedleNode(this.width, this.height);
        this.getChildren().add(needleLine);
    }

    public double estimatePi() {
        int intersections = theModel.getIntersectionsCountWithinWindow(this.width, this.height);

        // Prevent against divisions by zero:
        if (intersections == 0) {
            return 0;
        }

        int totalNeedles = theModel.getNeedles().size();
        double pi = (2.0 * totalNeedles) / intersections;

        return pi;
    }

}
