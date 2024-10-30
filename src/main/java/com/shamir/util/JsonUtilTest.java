import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JsonUtilTest {

    @Test
    public void testExtractKeys() {
        String json = """
            {
                "keys": {
                    "n": 4,
                    "k": 3
                }
            }
            """;
        JsonObject jsonObject = JsonUtil.parseJson(json);
        ShamirKey keys = JsonUtil.extractKeys(jsonObject);
        
        assertEquals(4, keys.getN());
        assertEquals(3, keys.getK());
    }

    @Test
    public void testExtractPoints() {
        String json = """
            {
                "keys": {
                    "n": 4,
                    "k": 3
                },
                "1": {
                    "base": "10",
                    "value": "4"
                },
                "2": {
                    "base": "2",
                    "value": "111"
                }
            }
            """;
        JsonObject jsonObject = JsonUtil.parseJson(json);
        List<Point> points = JsonUtil.extractPoints(jsonObject);
        
        assertEquals(2, points.size());
        assertEquals(4, points.get(0).getY().intValue()); // Assuming getY() returns a BigInteger
        assertEquals(3, points.get(1).getY().intValue()); // Test value for base 2
    }
}
