package buffon;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

/**
 * Graham Earley, Carleton College, CS257
 *
 * This class handles the view of the "board" onto which the
 * needles are thrown. It draws the background, the slats, and
 * the needles.
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
    final Color DEFAULT_BACKGROUND_COLOR = Color.web("#E4F1FE");

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
        this.theModel.setBoardWidth(this.width);
        this.theModel.setBoardHeight(this.height);
    }


    public void drawSlats() {
        double[] slatXValues = theModel.getSlatXValues();
        for (double slatXValue : slatXValues) {
            Line slat = new Line(slatXValue, 0, slatXValue, this.height);
            this.getChildren().add(slat);
        }
    }

    // TODO: Make this thing stop shifting around on screen! LayoutBounds? Pad it by 1 NEEDLE_LENGTH unit?
    public void drawBorder() {
        Rectangle border = new Rectangle(0.0, 0.0, this.getWidth(), this.getHeight());
        border.setFill(this.getBackgroundColor());
        border.setLayoutX(0);
        border.setLayoutY(0);
        border.setStroke(this.getBorderColor());
        border.setStrokeWidth(1.0);
        this.getChildren().add(border);
    }

    public void drawNeedle(Needle needle) {
        Line needleLine = needle.getNeedleNode(this.width, this.height);
        this.getChildren().add(needleLine);
    }

}
