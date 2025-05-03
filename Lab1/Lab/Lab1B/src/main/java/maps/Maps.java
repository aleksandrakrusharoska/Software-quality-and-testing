package maps;

import java.util.HashMap;
import java.util.Map;

public class Maps {
    public static Map<String, Integer> computeValueDifferences(Map<String, Integer> map1, Map<String, Integer> map2) {
        Map<String, Integer> result = new HashMap<>();

        for (String key : map1.keySet()) {
            if (map2.containsKey(key)) {
                result.put(key, Math.abs(map1.get(key) - map2.get(key)));
            }
        }

        return result;
    }
}
