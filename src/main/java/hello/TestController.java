package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {
    @Autowired
    TestModel model;

    @RequestMapping("simpleTest")
    public String simpleTest() {
        return "Test";
    }

    @RequestMapping(value = "testGet", method = RequestMethod.GET)
    public String getTest() {
        return "Test get";
    }

    @RequestMapping(value= "testGetWithParams", method = RequestMethod.GET)
    public String getTestWithParams(@RequestParam(value="test") String test) {
        return "Test: " + test;
    }

    @RequestMapping(value = "testPost", method = RequestMethod.POST)
    public String postTest(@RequestBody String body) {
        return "Received body: " + body;
    }

    @RequestMapping(value = "testJsonGet", method = RequestMethod.GET)
    public TestDataStructure2 testJsonGet(@RequestParam(value="field1") String field1,
                                          @RequestParam(value="field2") int field2) {
        return new TestDataStructure2(field1, field2);
    }

    @RequestMapping(value = "testJsonPostOne", method = RequestMethod.POST)
    public String testJsonPost(@RequestParam(value="field1") String field1,
                                             @RequestParam(value="field2") int field2) {
        model.addObject(new TestDataStructure2(field1, field2));
        return "OK";
    }

    @RequestMapping(value = "testJsonGetAll", method = RequestMethod.GET)
    public TestDataStructure2[] testJsonGetAll() {
        return model.getObjects();
    }


}
