package maps;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static maps.Maps.computeValueDifferences;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.HashMap;
import java.util.Map;

public class MapsTest {

    private Map<String, Integer> map1;
    private Map<String, Integer> map2;

    @BeforeEach
    public void setup() {
        map1 = new HashMap<>();
        map2 = new HashMap<>();
    }

    @Test
    public void testCommonKeysMoreThanOne() {
        map1.put("A", 5);
        map1.put("B", 10);
        map1.put("C", 3);

        map2.put("B", 7);
        map2.put("C", 8);
        map2.put("D", 12);

        Map<String, Integer> result = computeValueDifferences(map1, map2);
        Map<String, Integer> expected = new HashMap<>();
        expected.put("B", 3);
        expected.put("C", 5);

        assertEquals(expected, result);
    }


    @Test
    public void testMap1EmptyNoCommonKeys () {
        map1.clear();

        map2.put("B", 7);
        map2.put("C", 8);

        Map<String, Integer> result = computeValueDifferences(map1, map2);
        Map<String, Integer> expected = new HashMap<>();

        assertEquals(expected, result);
    }


    @Test
    public void testMap2EmptyNoCommonKeys() {
        map1.put("A", 5);
        map1.put("B", 10);
        map1.put("C", 3);

        map2.clear();

        Map<String, Integer> result = computeValueDifferences(map1, map2);
        Map<String, Integer> expected = new HashMap<>();

        assertEquals(expected, result);
    }



    @Test
    public void testNoCommonKeys() {
        map1.put("A", 5);
        map1.put("B", 10);

        map2.put("C", 3);
        map2.put("D", 8);

        Map<String, Integer> result = computeValueDifferences(map1, map2);
        Map<String, Integer> expected = new HashMap<>();

        assertEquals(expected, result);
    }


    @Test
    public void testOneCommonKey() {
        map1.put("A", 5);
        map1.put("B", 10);

        map2.put("B", 7);
        map2.put("C", 8);

        Map<String, Integer> result = computeValueDifferences(map1, map2);
        Map<String, Integer> expected = new HashMap<>();
        expected.put("B", 3);

        assertEquals(expected, result);
    }

}
