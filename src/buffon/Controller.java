package buffon;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.Random;

// todo: javadoc this stuff up

public class Controller {
    private Model theModel;
    public BoardView boardView;
    public NumberView numberView; //needs work
    public Random randomGenerator;
    public final double NEEDLE_LENGTH = 10.0;
    public TextField needleNumberInput;
    public Label inputErrorLabel;


    public Controller() {
    }

    public void initialize() {
        this.theModel = new Model();
        theModel.setSlatDistance(NEEDLE_LENGTH);

        boardView.setModel(this.theModel);
        boardView.setLayoutX(0);
        boardView.drawBorder();
        boardView.drawSlats();

        this.numberView.setModel(this.theModel);
        this.numberView.writeInformation(); // TODO remove hardcoded values
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
            this.inputErrorLabel.setText(String.format("Added %d needles.", n));
        } catch(Exception e) {
            n = 0;

            // Notify users of the error:
            this.inputErrorLabel.setText("Please enter a valid integer!");
        }


        for (int i = 0; i < n; i++) {
            Needle randomNeedle = getRandomNeedle();
            theModel.addNeedle(randomNeedle);
            boardView.drawNeedle(randomNeedle);
            }

        // TODO: FIX THIS VIEW! Don't hardcode those dimensions in there! (put them in model?)
        this.numberView.writeInformation();
    }
}
