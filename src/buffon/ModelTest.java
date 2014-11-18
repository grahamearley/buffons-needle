package buffon;

import org.junit.Assert;
import org.junit.Test;

public class ModelTest {

    @Test
    public void testGetIntersectionsCount() throws Exception {
        Model model = new Model();

        Needle intersectNeedle = new Needle(4.0,0.0,0.0,10.0);
        Needle nonIntersectNeedle = new Needle(3.0,1.0,90.0,10.0);

        model.setSlatDistance(5);
        model.addNeedle(intersectNeedle);
        model.addNeedle(nonIntersectNeedle);

        // THIS HAS CHANGED A BIT
        int intersectionCount = model.getIntersectionsCountWithinWindow(0,0);

        Assert.assertEquals(1, intersectionCount);
    }

}