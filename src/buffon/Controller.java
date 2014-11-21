package buffon;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.awt.*;
import java.net.URI;
import java.util.Random;

/**
 * Graham Earley, Carleton College, CS257
 *
 * This is the Controller for this MVC program. It is in charge
 * of creating and updating the board environment (views) and
 * generating random needles to send to the model and views.
 */
public class Controller {
    private Model theModel;
    public BoardView boardView;
    public NumberView numberView;

    public TextField needleNumberInput;
    public Label inputFeedbackLabel;
    public Button wikipediaButton;
    public ToggleButton helpButton;

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
        double boardWidth = this.boardView.getWidth();
        double boardHeight = this.boardView.getHeight();
        this.theModel = new Model(NUMBER_OF_SLATS, boardWidth, boardHeight);

        // Set up the board view:
        this.boardView.setModel(this.theModel);
        this.boardView.drawBoard();

        // Set up the number view:
        this.numberView.setModel(this.theModel);
        this.numberView.writeInformation();
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
            numberOfNeedlesToAdd = Integer.parseInt(this.needleNumberInput.getText());
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

        // Ensure the information toggle is deselected:
        this.deselectInformationToggle();
    }

    /**
     * A private method that generates a randomly-positioned
     * needle with a random color.
     *
     * @return A random Needle object.
     */
    private Needle getRandomNeedle() {
        Random randomGenerator = new Random();

        double randomXpercent = randomGenerator.nextDouble();
        double randomYpercent = randomGenerator.nextDouble();
        double randomAnglePercent = randomGenerator.nextDouble();

        // Needle lengths are equal to the distance between slats:
        double length = this.theModel.calculateDistanceBetweenSlats();

        Needle randomNeedle = new Needle(randomXpercent, randomYpercent, randomAnglePercent, length);
        randomNeedle.setColor(Color.web("#AEA8D3"));

        // Intersecting needles have a different color:
        for (double slat : this.theModel.getSlatXValues()) {
            if (theModel.isIntersection(randomNeedle, slat)) {
                randomNeedle.setColor(Color.web("#663399"));
            }
        }

        return randomNeedle;
    }

    /**
     * Delete all nodes from the board view, and then
     * clear the model and start over by re-initializing.
     *
     * @param actionEvent The button-click event.
     */
    public void clearNeedles(ActionEvent actionEvent) {
        this.boardView.getChildren().clear();
        this.initialize();
        this.inputFeedbackLabel.setText("Cleared the board!");

        // Ensure the information toggle is deselected:
        this.deselectInformationToggle();
    }

    /**
     * Toggle the explanation of the color-coding system and the
     * link to the Buffon's Needle Wikipedia page.
     *
     * @param actionEvent The button-click event.
     */
    public void toggleInformation(ActionEvent actionEvent) {
        if (this.helpButton.isSelected()) {
            this.inputFeedbackLabel.setText("Dark purple needles intersect with a slat. Light purple needles do not.");

            // Show the Wikipedia link:
            this.wikipediaButton.setVisible(true);
        } else {
            this.inputFeedbackLabel.setText("");

            // Don't show the Wikipedia link:
            this.wikipediaButton.setVisible(false);
        }
    }

    /**
     * Open the Buffon's Needle Wikipedia page in the web browser
     * to give the user more information.
     *
     * @param actionEvent The button-click event.
     */
    public void sendToWikipedia(ActionEvent actionEvent) {
        try {
            Desktop.getDesktop().browse(URI.create("http://en.wikipedia.org/wiki/Buffon%27s_needle"));
        } catch (Exception e) {
            // Let the user know if there was an error with the link:
            this.inputFeedbackLabel.setText("Sorry, there was an error with that link!");
        }
    }

    /**
     * Manually deselect the information ToggleButton and hide the Wikipedia link.
     *
     * This is used when other buttons are pressed; pressing other buttons turns the help text off.
     */
    private void deselectInformationToggle() {
        this.wikipediaButton.setVisible(false);
        this.helpButton.setSelected(false);
    }
}
