package sample;

import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;

public class ModelTest {

    @Test
    public void testGetIntersectionsCount() throws Exception {
        Model model = new Model();

        Needle intersectNeedle = new Needle(4.0,0.0,90.0);
        Needle nonIntersectNeedle = new Needle(3.0,1.0,0.0);

        model.addSlatXValue(5);
        model.addNeedle(intersectNeedle);
        model.addNeedle(nonIntersectNeedle);

        int intersectionCount = model.getIntersectionsCount();

        Assert.assertEquals(1, intersectionCount);
    }

    @Test
    public void testAddNeedle() throws Exception {
        Model model = new Model();
        Needle needle = new Needle(4.0,9.0,100.0);

        ArrayList<Needle> needleList = new ArrayList<Needle>();
        needleList.add(needle);
        model.addNeedle(needle);

        Assert.assertEquals(needleList, model.tossedNeedles);
    }

    @Test
    public void testAddSlatXValue() throws Exception {
        Model model = new Model();
        model.addSlatXValue(3);

        ArrayList<Integer> slatList = new ArrayList<Integer>();
        slatList.add(3);

        Assert.assertEquals(slatList, model.slatXValues);
    }
}