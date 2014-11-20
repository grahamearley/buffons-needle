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
    final Color DEFAULT_BACKGROUND_COLOR = Color.WHITE;

    public BoardView() {
        this.width = DEFAULT_WIDTH;
        this.height = DEFAULT_HEIGHT;
        this.borderColor = DEFAULT_BORDER_COLOR;
        this.backgroundColor = DEFAULT_BACKGROUND_COLOR;
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

    public void setModel(Model model) {
        this.theModel = model;

        // Tell the model what the board's dimensions are (for calculations):
        this.theModel.setBoardWidth(this.width);
        this.theModel.setBoardHeight(this.height);
    }


    /**
     * Draw horizontal slat Lines at the x values from the model.
     */
    public void drawSlats() {
        double[] slatXValues = theModel.getSlatXValues();
        for (double slatXValue : slatXValues) {
            Line slat = new Line(slatXValue, 0, slatXValue, this.height);
            this.getChildren().add(slat);
        }
    }

    /**
     * Draw the board and a transparent padding rectangle to surround it.
     *
     *  The padding rectangle will extend beyond the board in all directions
     *  by the needle/slat length. This prevents things from shifting around in the scene
     *  when needles fall on the edge of the board and extend the view's LayoutBounds.
     */
    public void drawBoard() {
        // Create the board onto which the needles are thrown.
        Rectangle board = new Rectangle(0.0, 0.0, this.getWidth(), this.getHeight());
        board.setFill(this.getBackgroundColor());
        board.setStroke(this.getBorderColor());
        board.setStrokeWidth(1.0);

        // Create an outer rectangle for padding.
        double paddingAmount = this.theModel.getSlatDistance();
        Rectangle padding = new Rectangle(-paddingAmount, -paddingAmount, this.getWidth() + 2*paddingAmount, this.getHeight()+ 2*paddingAmount);
        padding.setFill(Color.TRANSPARENT);
        padding.setStrokeWidth(0);

        this.getChildren().add(padding);
        this.getChildren().add(board);
    }

    /**
     * Draw a needle on the board.
     *
     *  @param needle The needle to be drawn
     */
    public void drawNeedle(Needle needle) {
        Line needleLine = needle.getNeedleNode(this.width, this.height);
        this.getChildren().add(needleLine);
    }

}
