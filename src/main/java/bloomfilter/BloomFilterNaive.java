package bloomfilter;

import static java.util.logging.Level.INFO;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.logging.Logger;


/**
 * Naive implementation is just a byte array the same size of the result of a MD5 digest.
 * Each input is mapped to its MD5 digest and simply added (bitwise OR) to the byte array.
 */
public class BloomFilterNaive extends BloomFilter {

  private static final Logger LOGGER =  Logger.getLogger(BloomFilterNaive.class.getName());

  /** based on the output size of MD5 */
  private static final int BYTE_COUNT = 16;

  public static void main(String[] args) {
    final BloomFilter bloomFilter = new BloomFilterNaive();
    final int falsePositives = bloomFilter.completeRun();

    LOGGER.log(INFO, "Complete run has been performed, number of false positives: {0}", falsePositives);
  }

  BloomFilterNaive() {
    bitmap = new byte[BYTE_COUNT];
  }

  @Override
  byte[] hash(final String input) {
    try {
      final MessageDigest md = MessageDigest.getInstance("MD5");
      md.update(input.getBytes());
      return md.digest();
    } catch (NoSuchAlgorithmException e) {
      // I *really* hope this never happens :)
      return new byte[16];
    }
  }

  @Override
  void addToBitmap(String input) {
    byte[] hashed = hash(input);
    bitmap = or(hashed, bitmap);
  }

  @Override
  boolean isMember(String input) {
    byte[] hashed = hash(input);
    byte[] and = and(hashed, bitmap);
    return Arrays.equals(and, hashed);
  }

  private byte[] and(byte[] a, byte[] b) {
    byte[] and = new byte[16];
    for (int i = 0; i < 16; i++) {
      and[i] = (byte)(a[i] & b[i]);
    }
    return and;
  }

  private byte[] or(byte[] a, byte[] b) {
    byte[] and = new byte[16];
    for (int i = 0; i < 16; i++) {
      and[i] = (byte)(a[i] | b[i]);
    }
    return and;
  }
}
