package hello;

import java.util.ArrayList;

public class TestModel {
    private ArrayList<TestDataStructure2> objects;

    public TestModel() {
        objects = new ArrayList<TestDataStructure2>();
    }

    public void addObject(TestDataStructure2 object) {
        objects.add(object);
    }

    public TestDataStructure2[] getObjects() {
        return objects.toArray(new TestDataStructure2[objects.size()]);
    }

    public void clearObjects() {
        objects.clear();
    }
}
