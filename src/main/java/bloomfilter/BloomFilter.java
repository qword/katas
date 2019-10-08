package bloomfilter;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Stream;

/**
 * http://codekata.com/kata/kata05-bloom-filters/
 * Implement a bloom filter
 * Ingredients:
 * 1. a bitmap
 * 2. a hash function
 * 3. a reduce function between hash and bitmap
 * Once everything is ready, read the words.txt file and check how many false positives the program
 * produces (words should be unique in the file)
 */
abstract class BloomFilter {
  private static final String INPUT_PATH = "/bloomfilter/words.txt";

  byte[] bitmap;

  abstract byte[] hash(final String input);
  abstract void addToBitmap(final String input);
  abstract boolean isMember(final String input);

  final int completeRun() {
    final InputStream inputStream = BloomFilter.class.getResourceAsStream(INPUT_PATH);
    final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

    try(Stream<String> stream = reader.lines()) {
      return stream
          .mapToInt(line -> {
            boolean falsePositive = isMember(line);
            addToBitmap(line);
            return falsePositive ? 1 : 0;
          })
          .sum();
    }
  }
}
