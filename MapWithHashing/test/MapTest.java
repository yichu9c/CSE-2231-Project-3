import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;
import components.map.Map.Pair;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author Shyam Sai Bethina and Yihone Chu
 *
 */
public abstract class MapTest {

    /**
     * Invokes the appropriate {@code Map} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new map
     * @ensures constructorTest = {}
     */
    protected abstract Map<String, String> constructorTest();

    /**
     * Invokes the appropriate {@code Map} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new map
     * @ensures constructorRef = {}
     */
    protected abstract Map<String, String> constructorRef();

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsTest = [pairs in args]
     */
    private Map<String, String> createFromArgsTest(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorTest();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsRef = [pairs in args]
     */
    private Map<String, String> createFromArgsRef(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorRef();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     * Test no argument constructor.
     */
    @Test
    public void testConstuctor() {
        Map<String, String> tested = this.constructorTest();
        Map<String, String> expected = this.constructorRef();

        assertEquals(expected, tested);
    }

    /**
     * Test add method with an edge case.
     */
    @Test
    public void testAdd1() {
        Map<String, String> tested = this.constructorTest();
        Map<String, String> expected = this.constructorRef();

        this.constructorTest().add("", "");

        this.constructorRef().add("", "");

        assertEquals(expected, tested);
    }

    /**
     * Test add method with a routine case.
     */
    @Test
    public void testAdd2() {
        Map<String, String> tested = this.constructorTest();
        Map<String, String> expected = this.constructorRef();

        this.constructorTest().add("hello", "there");

        this.constructorRef().add("hello", "there");

        assertEquals(expected, tested);
    }

    /**
     * Test add method with a challenging case.
     */
    @Test
    public void testAdd3() {
        Map<String, String> tested = this.constructorTest();
        Map<String, String> expected = this.constructorRef();

        this.constructorTest().add("", "there");

        this.constructorRef().add("", "there");

        assertEquals(expected, tested);
    }

    /**
     * Test add method with a routine case.
     */
    @Test
    public void testAdd4() {
        Map<String, String> tested = this.constructorTest();
        Map<String, String> expected = this.constructorRef();

        this.constructorTest().add("hello", "there");
        this.constructorTest().add("my", "name");

        this.constructorRef().add("hello", "there");
        this.constructorRef().add("my", "name");

        assertEquals(expected, tested);
    }

    /**
     * Test remove method with a routine case.
     */
    @Test
    public void testRemove() {
        Map<String, String> tested = this.constructorTest();
        Map<String, String> expected = this.constructorRef();

        tested.add("hello", "there");
        tested.add("my", "name");

        Pair<String, String> testReturn = tested.remove("my");

        expected.add("hello", "there");
        expected.add("my", "name");
        Pair<String, String> expectedReturn = expected.remove("my");

        assertEquals(expected, tested);
        assertEquals(expectedReturn, testReturn);
    }

    /**
     * Test remove method with a challenging case.
     */
    @Test
    public void testRemove2() {
        Map<String, String> tested = this.constructorTest();
        Map<String, String> expected = this.constructorRef();

        tested.add("", "there");
        tested.add("my", "name");

        Pair<String, String> testReturnOne = tested.remove("");
        Pair<String, String> testReturnTwo = tested.remove("my");

        expected.add("", "there");
        expected.add("my", "name");

        Pair<String, String> expectedReturnOne = expected.remove("");
        Pair<String, String> expectedReturnTwo = expected.remove("my");

        assertEquals(expected, tested);
        assertEquals(expectedReturnOne, testReturnOne);
        assertEquals(expectedReturnTwo, testReturnTwo);

    }

    /**
     * Test remove method with a edge case.
     */
    @Test
    public void testRemove3() {
        Map<String, String> tested = this.constructorTest();
        Map<String, String> expected = this.constructorRef();

        tested.add("", "");

        Pair<String, String> testReturn = tested.remove("");

        expected.add("", "");

        Pair<String, String> expectedReturn = expected.remove("");

        assertEquals(expected, tested);
        assertEquals(expectedReturn, testReturn);
    }

    /**
     * Test remove any with a edge case.
     */
    @Test
    public void testRemoveAny() {
        Map<String, String> tested = this.constructorTest();
        tested.add("hello", "there");

        Map<String, String> expected = this.constructorRef();
        expected.add("hello", "there");

        Pair<String, String> testedReturn = tested.removeAny();

        Pair<String, String> expectedReturn = expected
                .remove(testedReturn.key());

        assertEquals(expected, tested);
        assertEquals(expectedReturn, testedReturn);
    }

    /**
     * Test remove any with a routine case.
     */
    @Test
    public void testRemoveAny2() {
        Map<String, String> tested = this.constructorTest();
        tested.add("hello", "there");
        tested.add("my", "name");

        Map<String, String> expected = this.constructorRef();
        expected.add("hello", "there");
        expected.add("my", "name");

        Pair<String, String> testedReturn = tested.removeAny();

        Pair<String, String> expectedReturn = expected
                .remove(testedReturn.key());

        assertEquals(expected, tested);
        assertEquals(expectedReturn, testedReturn);

    }

    /**
     * Test remove any with a challenging case.
     */
    @Test
    public void testRemoveAny3() {
        Map<String, String> tested = this.constructorTest();
        tested.add("hello", "there");
        tested.add("my", "name");
        tested.add("here", "there");

        Map<String, String> expected = this.constructorRef();
        expected.add("hello", "there");
        expected.add("my", "name");
        expected.add("here", "there");

        Pair<String, String> testedReturn = tested.removeAny();

        Pair<String, String> expectedReturn = expected
                .remove(testedReturn.key());

        assertEquals(expected, tested);
        assertEquals(expectedReturn, testedReturn);

    }

    /**
     * Test value with a edge case.
     */
    @Test
    public void testValue() {
        Map<String, String> tested = this.constructorTest();
        tested.add("", "HELLO");
        String testedValue = tested.value("");

        Map<String, String> expected = this.constructorRef();
        expected.add("", "HELLO");
        String expectedValue = expected.value("");

        assertEquals(testedValue, expectedValue);
    }

    /**
     * Test value with a routine case.
     */
    @Test
    public void testValue2() {
        Map<String, String> tested = this.constructorTest();
        tested.add("", "HELLO");
        tested.add("my", "name");

        String testedValue = tested.value("");

        Map<String, String> expected = this.constructorRef();
        expected.add("", "HELLO");
        expected.add("my", "name");

        String expectedValue = expected.value("");

        assertEquals(testedValue, expectedValue);
    }

    /**
     * Test value with a challenging case.
     */
    @Test
    public void testValue3() {
        Map<String, String> tested = this.constructorTest();
        tested.add("", "");

        String testedValue = tested.value("");

        Map<String, String> expected = this.constructorRef();
        expected.add("", "");

        String expectedValue = expected.value("");

        assertEquals(testedValue, expectedValue);
    }

    /**
     * Test hasKey with a edge case.
     */
    @Test
    public void testHasKey() {
        Map<String, String> tested = this.constructorTest();
        tested.add("", "there");

        Map<String, String> expected = this.constructorRef();
        expected.add("", "there");

        assertEquals(expected.hasKey(""), tested.hasKey(""));
    }

    /**
     * Test hasKey with a challenging case.
     */
    @Test
    public void testHasKey2() {
        Map<String, String> tested = this.constructorTest();
        tested.add("hello", "there");

        Map<String, String> expected = this.constructorRef();
        expected.add("hello", "there");

        assertEquals(expected.hasKey(""), tested.hasKey(""));
    }

    /**
     * Test hasKey with a routine case.
     */
    @Test
    public void testHasKey3() {
        Map<String, String> tested = this.constructorTest();
        tested.add("hello", "there");
        tested.add("my", "name");

        Map<String, String> expected = this.constructorRef();
        expected.add("hello", "there");
        expected.add("my", "name");

        assertEquals(expected.hasKey("my"), tested.hasKey("my"));
    }

    /**
     * Test size with a edge case.
     */
    @Test
    public void testSize() {
        Map<String, String> tested = this.constructorTest();

        Map<String, String> expected = this.constructorRef();

        assertEquals(expected.size(), tested.size());
    }

    /**
     * Test size with a routine case.
     */
    @Test
    public void testSize2() {
        Map<String, String> tested = this.constructorTest();
        tested.add("hello", "there");
        tested.add("my", "name");

        Map<String, String> expected = this.constructorRef();
        expected.add("hello", "there");
        expected.add("my", "name");

        assertEquals(expected.size(), tested.size());
    }

    /**
     * Test size with a challenging case.
     */
    @Test
    public void testSize3() {
        Map<String, String> tested = this.constructorTest();
        tested.add("", "");

        Map<String, String> expected = this.constructorRef();
        expected.add("", "");

        assertEquals(expected.size(), tested.size());
    }

    // TODO - add test cases for constructor, add, remove, removeAny, value,
    // hasKey, and size

}
