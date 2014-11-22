package buffon;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

/**
 * Graham Earley, Carleton College, CS257
 *
 * This class provides a view of the "board" onto which the
 * needles are thrown: it draws the background, the slats, and
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
    final Color DEFAULT_BACKGROUND_COLOR = Color.WHITE;

    public BoardView() {
        this.width = DEFAULT_WIDTH;
        this.height = DEFAULT_HEIGHT;
        this.borderColor = DEFAULT_BORDER_COLOR;
        this.backgroundColor = DEFAULT_BACKGROUND_COLOR;
    }

    /**
     * Draw the entire board, consisting of the background and the slat lines.
     */
    public void drawBoard() {
        // This task is broken up into two smaller private methods:
        this.drawBackground();
        this.drawSlats();
    }

    /**
     * Draw a needle on the board.
     *
     * @param needle the Needle to be drawn
     */
    public void drawNeedle(Needle needle) {
        Line needleLine = needle.getNeedleNode(this.width, this.height);
        this.getChildren().add(needleLine);
    }

    /**
     * Draw horizontal slat Lines at the x values from the model.
     */
    private void drawSlats() {
        double[] slatXValues = this.theModel.getSlatXValues();
        for (double slatXValue : slatXValues) {
            Line slat = new Line(slatXValue, 0, slatXValue, this.height);
            this.getChildren().add(slat);
        }
    }

    /**
     * Draw the board background and a transparent padding rectangle to surround it.
     *
     *  The transparent padding rectangle will extend beyond the board in all directions
     *  by the needle/slat length. Without this, things shift around in the scene when
     *  needles fall on the edge of the board and extend the view's LayoutBounds.
     */
    private void drawBackground() {
        // Create the background of the board onto which the needles are thrown:
        Rectangle board = new Rectangle(0.0, 0.0, this.getWidth(), this.getHeight());
        board.setFill(this.getBackgroundColor());
        board.setStroke(this.getBorderColor());

        // Create an outer rectangle for padding:
        double paddingAmount = this.theModel.calculateDistanceBetweenSlats();
        Rectangle padding = new Rectangle(-paddingAmount, -paddingAmount, this.getWidth() + 2*paddingAmount, this.getHeight()+ 2*paddingAmount);
        padding.setFill(Color.TRANSPARENT);
        padding.setStrokeWidth(0);

        this.getChildren().add(padding);
        this.getChildren().add(board);
    }

    public void setModel(Model model) {
        this.theModel = model;
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

}
