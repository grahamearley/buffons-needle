package buffon;

import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.TextField;

import java.util.Random;

public class Controller {
    private Model theModel;
    public Group viewGroup;
    public Random randomGenerator;
    public final double NEEDLE_LENGTH = 10.0;
    public TextField needleNumberInput;


    public Controller() {
    }

    public void initialize() {
        this.theModel = new Model();
        theModel.setSlatDistance(NEEDLE_LENGTH);

        double leftEdge = 0;
        final double PADDING = 0.0;

        for (Node node : this.viewGroup.getChildren()) {
            BoardView board = (BoardView)node;
            board.setModel(this.theModel);
            board.setLayoutX(leftEdge);
            leftEdge += board.getWidth() + PADDING;
            board.drawBorder();
            board.drawNeedles();
            board.drawSlats(NEEDLE_LENGTH); // the slats are NEEDLE_LENGTH units apart.
        }
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

        return new Needle(randomXpercent, randomYpercent, randomAnglePercent, length);
    }

    // doesn't do anything yet: #TODO
    public void tossNeedles(ActionEvent actionEvent) {
        // http://stackoverflow.com/questions/25252558/javafx-how-to-make-enter-key-submit-textarea

        for (int i = 0; i < 1000; i++) {
            Needle randomNeedle = getRandomNeedle();
            theModel.addNeedle(randomNeedle);
            }

        for (Node node : this.viewGroup.getChildren()) {
            BoardView board = (BoardView)node;
            board.drawNeedles();
            System.out.println("Pi ~~ " + board.estimatePi());
        }
    }
}
