package src.main.java.com.shamir.core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigInteger;

public class ShamirSecretSharingImplementationTest {
    
    @Test
    public void testFindSecret() {
        String testInput = """
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
                },
                "3": {
                    "base": "10",
                    "value": "12"
                },
                "6": {
                    "base": "4",
                    "value": "213"
                }
            }
            """;
            
        SecretSharing implementation = new ShamirSecretSharingImplementation();
        BigInteger secret = implementation.findSecret(testInput);
        assertNotNull(secret);
    }
}
