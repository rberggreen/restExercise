package hello

import io.restassured.RestAssured
import org.hamcrest.Matchers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.test.context.ContextConfiguration
import spock.lang.*

@ContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloControllerSpockTest extends Specification {

    @LocalServerPort
    int serverPort

    @Autowired
    TestModel model

    def setupSpec() {
        RestAssured.baseURI = "http://localhost"
    }

    def setup() {
        RestAssured.port = serverPort
//        RestAssured.port = 8080
    }

    def "simpleTest test"() {
        expect:
        RestAssured.get("/simpleTest").then().body(equals("Test"))
        RestAssured.post("/simpleTest").then().body(equals("Test"))
    }

    def "testGet test"() {
        expect:
        RestAssured.get("/testGet").then().body(equals("Test get"))
    }

    def "testGetWithParams test"() {
        expect:
        RestAssured
            .given().queryParam("test", s)
            .when().get("/testGetWithParams")
            .then().statusCode(200).body(equals("Test: " + s))

        where:
        s   | i
        "a" | 1
        "a" | 0
        "a" | -1
    }

    def "testPost test"() {
        expect:
        RestAssured
            .given().body(s)
            .when().post("/testPost")
            .then().statusCode(200).body(equals("Received body: " + s))

        where:
        s << ["asdf", "aasdf \n asdf"]
    }

    def "testPost empty body test"() {
        expect:
        RestAssured
                .given().body("")
                .when().post("/testPost")
                .then().statusCode(400)
    }

    def "testJsonGet test"() {
        expect:
        RestAssured
            .given().queryParam("field1", s).queryParam("field2", i)
            .when().get("/testJsonGet")
            .then().statusCode(200).body("field1", Matchers.equalTo(s)).body("field2", Matchers.equalTo(i))

        where:
        s | i
        "a" | 1
        "" | 0
        "ç" | -1
    }

    def "testJsonPostOne test"() {
        expect:
        RestAssured
            .given().queryParam("field1", "a").queryParam("field2", 1)
            .when().post("/testJsonPostOne")
            .then().statusCode(200).body(equals("OK"))

        cleanup:
        model.clearObjects()
    }

    def "testJsonGetAll test"() {
        setup:
        RestAssured
            .given().queryParam("field1", s).queryParam("field2", i)
            .post("/testJsonPostOne")

        expect:
        RestAssured
            .when().get("/testJsonGetAll")
            .then().statusCode(200).body("[0].field1", Matchers.equalTo(s)).body("[0].field2", Matchers.equalTo(i))

        where:
        s = "a"
        i = 1

    }


}
