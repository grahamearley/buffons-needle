package sample;

import org.junit.Assert;
import org.junit.Test;

public class ModelTest {

    @Test
    public void testGetIntersectionsCount() throws Exception {
        Model model = new Model();

        Needle intersectNeedle = new Needle(4.0,0.0,0.0,10.0);
        Needle nonIntersectNeedle = new Needle(3.0,1.0,90.0,10.0);

        model.addSlatXValue(5);
        model.addNeedle(intersectNeedle);
        model.addNeedle(nonIntersectNeedle);

        int intersectionCount = model.getIntersectionsCount();

        Assert.assertEquals(1, intersectionCount);
    }

}