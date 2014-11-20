package buffon.Tests;

import buffon.Needle;
import javafx.scene.shape.Line;
import org.junit.Assert;
import org.junit.Test;

public class NeedleTest {
    private final double delta = 0.00001; // the acceptable error when comparing doubles.

    @Test
    public void testGetNeedleNode() throws Exception {
        Needle test = new Needle(0.5, 0.6, 0.25, 10.0);
        double width = 100;
        double height = 100;

        // Calculate the expected needle Line object parameters:
        double lineLength = 10.0;
        double angle = 0.25 * Math.PI*2;
        double startX = 0.5 * width;
        double startY = 0.6 * height;
        double endX = startX + (lineLength * Math.cos(angle));
        double endY = startY + (lineLength * Math.sin(angle));

        Line needleNode = test.getNeedleNode(width,height);

        Assert.assertEquals(startX, needleNode.getStartX(), this.delta);
        Assert.assertEquals(startY, needleNode.getStartY(), this.delta);
        Assert.assertEquals(endX, needleNode.getEndX(), this.delta);
        Assert.assertEquals(endY, needleNode.getEndY(), this.delta);
    }
}