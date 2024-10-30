package src.main.java.com.shamir.model;

import java.math.BigInteger;

public class Point implements Comparable<Point> {
    private final int x;
    private final BigInteger y;

    public Point(int x, BigInteger y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public BigInteger getY() { return y; }

    @Override
    public int compareTo(Point other) {
        return Integer.compare(this.x, other.x);
    }
}
