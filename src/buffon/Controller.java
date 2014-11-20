package buffon;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.util.Random;

/**
 * Graham Earley, Carleton College, CS257
 *
 * This is the Controller for this MVC program. It is
 * in charge of creating the board environment and generating
 * random needles to send to the model and views.
 */
public class Controller {
    private Model theModel;
    public BoardView boardView;
    public NumberView numberView;
    public TextField needleNumberInput;
    public Label inputFeedbackLabel;
    public Random randomGenerator;

    public final int NUMBER_OF_SLATS = 20;

    public Controller() {
    }

    /**
     * Initialize the environment by creating the model
     * and putting the views on display.
     *
     * This method is automatically called by JavaFX.
     */
    public void initialize() {
        this.theModel = new Model();
        theModel.setNumberOfSlats(NUMBER_OF_SLATS);

        // Set up the board view:
        boardView.setModel(this.theModel);
        boardView.drawBoard();

        // Set up the number view:
        this.numberView.setModel(this.theModel);
        this.numberView.writeInformation();
    }

    /**
     * A private method that generates a randomly-positioned
     * needle with a random color.
     *
     * @return A random Needle object.
     */
    private Needle getRandomNeedle() {
        randomGenerator = new Random();
        double randomXpercent = randomGenerator.nextDouble();
        double randomYpercent = randomGenerator.nextDouble();
        double randomAnglePercent = randomGenerator.nextDouble();

        // Needle lengths are equal to the distance between slats:
        double length = theModel.calculateSlatDistance();

        // Generate a random RGB color:
        int r = randomGenerator.nextInt(256);
        int g = randomGenerator.nextInt(256);
        int b = randomGenerator.nextInt(256);

        Needle randomNeedle = new Needle(randomXpercent, randomYpercent, randomAnglePercent, length);
        randomNeedle.setColor(Color.rgb(r, g, b));

        return randomNeedle;
    }

    /**
     * "Toss" the input number of needles onto the board, and
     * update thew views accordingly. The needles are randomly generated,
     * and if the user entered an invalid integer value, an error is displayed.
     *
     * @param actionEvent The button-click or enter-key event.
     */
    public void tossNeedles(ActionEvent actionEvent) {
        int numberOfNeedlesToAdd;

        // Read the input:
        try {
            numberOfNeedlesToAdd = Integer.parseInt(needleNumberInput.getText());
            this.inputFeedbackLabel.setText(String.format("Added %d needles.", numberOfNeedlesToAdd));

        } catch(Exception e) {
            numberOfNeedlesToAdd = 0;

            // Notify users of the error and set the box to 0:
            this.inputFeedbackLabel.setText("Please enter a valid integer!");
            this.needleNumberInput.setText("0");
        }

        for (int i = 0; i < numberOfNeedlesToAdd; i++) {
            Needle randomNeedle = getRandomNeedle();
            this.theModel.addNeedle(randomNeedle);
            this.boardView.drawNeedle(randomNeedle);
            }

        this.numberView.writeInformation();
    }

    /**
     * Removes all objects from the board view, and then
     * clears the model and starts over by re-initializing.
     *
     * @param actionEvent The button-click event.
     */
    public void clearNeedles(ActionEvent actionEvent) {
        this.boardView.getChildren().clear();
        this.initialize();
        this.inputFeedbackLabel.setText("Cleared the board!");
    }
}
