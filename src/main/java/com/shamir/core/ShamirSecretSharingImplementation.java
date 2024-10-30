package src.main.java.com.shamir.core;

import com.google.gson.JsonObject;
import com.shamir.model.Point;
import com.shamir.model.ShamirKey;
import com.shamir.exception.InsufficientPointsException;
import com.shamir.util.JsonUtil;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShamirSecretSharingImplementation implements SecretSharing {
    private static final Logger logger = LogManager.getLogger(ShamirSecretSharingImplementation.class);

    @Override
    public BigInteger findSecret(String jsonInput) {
        logger.info("Starting secret calculation from input");
        
        JsonObject jsonObject = JsonUtil.parseJson(jsonInput);
        ShamirKey keys = JsonUtil.extractKeys(jsonObject);
        List<Point> points = JsonUtil.extractPoints(jsonObject);

        if (points.size() < keys.getK()) {
            throw new InsufficientPointsException(
                "Need at least " + keys.getK() + " points, but only " + points.size() + " provided"
            );
        }

        return calculateSecret(points, keys.getK());
    }

    private BigInteger calculateSecret(List<Point> points, int k) {
        Collections.sort(points);
        List<Point> usedPoints = points.subList(0, k);
        BigInteger result = BigInteger.ZERO;

        for (int i = 0; i < k; i++) {
            Point current = usedPoints.get(i);
            BigInteger term = current.getY();
            BigInteger numerator = BigInteger.ONE;
            BigInteger denominator = BigInteger.ONE;

            for (int j = 0; j < k; j++) {
                if (i != j) {
                    Point other = usedPoints.get(j);
                    numerator = numerator.multiply(BigInteger.valueOf(-other.getX()));
                    denominator = denominator.multiply(
                        BigInteger.valueOf(current.getX() - other.getX())
                    );
                }
            }

            term = term.multiply(numerator).divide(denominator);
            result = result.add(term);
        }

        return result;
    }
}
