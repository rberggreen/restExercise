package hello

import spock.lang.Specification

class TestDataStructure2Test extends Specification {

    def "populating fields using Lombok-generated constructor"() {
        setup:
        def str = "asdf"
        def i = 1

        when:
        TestDataStructure2 s = new TestDataStructure2(str, i)

        then:
        s.getField1() == str
        s.getField2() == i
    }

    def "TestDataStructure2 should accept (String, int)"() {
        when:
        TestDataStructure2 s = new TestDataStructure2(str, i)

        then:
        noExceptionThrown()

        s.getField1() == str
        s.getField2() == i

        where:
        str | i
        "a" | 1
        "b" | 0
        "c" | -1
    }
}
