package buffon;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.util.Random;

// todo: javadoc this stuff up

public class Controller {
    private Model theModel;
    public BoardView boardView;
    public NumberView numberView;
    public Random randomGenerator;
    public final double NEEDLE_LENGTH = 20.0;
    public TextField needleNumberInput;
    public Label inputFeedbackLabel;


    public Controller() {
    }

    public void initialize() {
        this.theModel = new Model();
        theModel.setSlatDistance(NEEDLE_LENGTH);

        // Set up the board view:
        boardView.setModel(this.theModel);
        boardView.drawBoard();
        boardView.drawSlats();

        // Set up the number view:
        this.numberView.setModel(this.theModel);
        this.numberView.writeInformation();
    }

    public void tossNeedles(int num) {
        for (int i = 0; i < num; i++) {
            Needle randomNeedle = this.getRandomNeedle();
            theModel.addNeedle(randomNeedle);
        }
    }

    public Needle getRandomNeedle() {
        randomGenerator = new Random();
        double randomXpercent = randomGenerator.nextDouble();
        double randomYpercent = randomGenerator.nextDouble();
        double randomAnglePercent = randomGenerator.nextDouble();
        double length = NEEDLE_LENGTH;

        // Generate a random RGB color:
        int r = randomGenerator.nextInt(256);
        int g = randomGenerator.nextInt(256);
        int b = randomGenerator.nextInt(256);

        Needle randomNeedle = new Needle(randomXpercent, randomYpercent, randomAnglePercent, length);
        randomNeedle.setColor(Color.rgb(r, g, b));

        return randomNeedle;
    }

    //TODO: Handle keyboard ENTER event
    public void tossNeedles(ActionEvent actionEvent) {
        // Read the input:
        int n = 0;
        try {
            n = Integer.parseInt(needleNumberInput.getText());
            this.inputFeedbackLabel.setText(String.format("Added %d needles.", n));
        } catch(Exception e) {
            n = 0;

            // Notify users of the error and set the box to 0:
            this.inputFeedbackLabel.setText("Please enter a valid integer!");
            this.needleNumberInput.setText("0");
        }


        for (int i = 0; i < n; i++) {
            Needle randomNeedle = getRandomNeedle();
            this.theModel.addNeedle(randomNeedle);
            this.boardView.drawNeedle(randomNeedle);
            }

        this.numberView.writeInformation();
    }

    public void clearNeedles(ActionEvent actionEvent) {
        this.boardView.getChildren().clear();
        this.initialize();
        this.inputFeedbackLabel.setText("Cleared the board!");
    }
}
