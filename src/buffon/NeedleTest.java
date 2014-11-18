package buffon;

import javafx.scene.shape.Line;
import org.junit.Assert;

public class NeedleTest {

    @org.junit.Test
    public void testConstructor() throws Exception {
        Needle test = new Needle(0.5,0.11,0.98,10.0);
        Assert.assertEquals(0.5, test.xPercent);
        Assert.assertEquals(0.11, test.yPercent);
        Assert.assertEquals(0.98, test.anglePercent);
        Assert.assertEquals(10, test.length);
    }

    @org.junit.Test
    public void testGetNeedleNode() throws Exception {
        Needle test = new Needle(0.5,0.11,0.98,10.0);

        // Creating the expected needle Line object:
        double lineLength = 10.0;
        double angle = 0.98 * 360;
        double startX = 0.5 * 100;
        double startY = 0.11 * 100;
        double endX = startX + (lineLength * Math.cos(angle));
        double endY = startY + (lineLength * Math.sin(angle));
        Line needleNode = new Line(startX, startY, endX, endY);

        Assert.assertEquals(needleNode, test.getNeedleNode(100,100));
    }
}