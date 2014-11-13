package sample;

import javafx.scene.shape.Line;
import org.junit.Assert;

public class NeedleTest {

    @org.junit.Test
    public void testConstructor() throws Exception {
        Needle test = new Needle(5.0,11.0,265.0);
        Assert.assertEquals(5, test.x);
        Assert.assertEquals(11, test.y);
        Assert.assertEquals(265, test.angle);
    }

    @org.junit.Test
    public void testGetNeedleNode() throws Exception {
        Needle test = new Needle(5.0,11.0,265.0);

        // Creating the expected needle Line object:
        double lineLength = 10.0;
        double angle = 265.0;
        double startX = 5.0;
        double startY = 11.0;
        double endX = startX + (lineLength * Math.cos(angle));
        double endY = startY + (lineLength * Math.sin(angle));
        Line needleNode = new Line(startX, startY, endX, endY);

        Assert.assertEquals(needleNode, test.getNeedleNode());
    }
}