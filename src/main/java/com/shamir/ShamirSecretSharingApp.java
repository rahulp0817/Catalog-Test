import com.shamir.util.JsonUtil; // Ensure this import is present

public class ShamirSecretSharingApp {
    private static final Logger logger = LogManager.getLogger(ShamirSecretSharingApp.class);

    public static void main(String[] args) {
        try {
            String testCase = """
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

            SecretSharing secretSharing = SecretSharingFactory.createShamirImplementation();
            
            // Parse the JSON string to a JsonObject
            JsonObject jsonObject = JsonUtil.parseJson(testCase);
            BigInteger secret = secretSharing.findSecret(jsonObject);
            
            logger.info("Calculation completed successfully");
            System.out.println("The secret (constant term) is: " + secret);
        } catch (Exception e) {
            logger.error("Error during secret calculation", e);
            System.err.println("Error: " + e.getMessage());
        }
    }
}
