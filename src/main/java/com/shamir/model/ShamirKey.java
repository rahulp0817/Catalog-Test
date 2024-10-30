package src.main.java.com.shamir.model;

public class ShamirKey {
  private final int n;
  private final int k;

  public ShamirKey(int n, int k) {
      this.n = n;
      this.k = k;
  }

  public int getN() { return n; }
  public int getK() { return k; }
}
