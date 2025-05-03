package maps;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Example input maps
        Map<String, Integer> map1 = new HashMap<>();
        map1.put("A", 5);
        map1.put("B", 10);
        map1.put("F", 3);

        Map<String, Integer> map2 = new HashMap<>();
        map2.put("B", 7);
        map2.put("C", 8);
        map2.put("D", 12);

        // Calling the function
        Map<String, Integer> result = Maps.computeValueDifferences(map1, map2);

        // Output the result
        System.out.println("Output: " + result);
    }
}
