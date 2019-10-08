package bloomfilter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BloomFilterNaiveTest {

  private BloomFilterNaive bloomFilterNaive;

  @BeforeEach
  void setUp() {
    bloomFilterNaive = new BloomFilterNaive();
  }

  @Test
  void shouldRecognizeASingleWord() {
    final String input = "input";

    assertFalse(bloomFilterNaive.isMember(input));

    bloomFilterNaive.addToBitmap(input);

    assertTrue(bloomFilterNaive.isMember(input));
  }

  @Test
  void shouldRecognizeTwoWords() {
    final String input1 = "input";
    final String input2 = "another input";

    assertFalse(bloomFilterNaive.isMember(input1));
    assertFalse(bloomFilterNaive.isMember(input2));

    bloomFilterNaive.addToBitmap(input1);

    assertTrue(bloomFilterNaive.isMember(input1));
    assertFalse(bloomFilterNaive.isMember(input2));

    bloomFilterNaive.addToBitmap(input2);

    assertTrue(bloomFilterNaive.isMember(input1));
    assertTrue(bloomFilterNaive.isMember(input2));
  }
}
