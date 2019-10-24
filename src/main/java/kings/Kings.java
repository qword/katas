package kings;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Exercise is to sort Kings names by name then by roman numeral
 */
class Kings {

  static class King implements Comparable<King> {
    private String fullName;
    private String name;
    private int numeral;

    String getFullName() {
      return this.fullName;
    }

    String getName() {
      return this.name;
    }

    int getNumeral() {
      return this.numeral;
    }

    King(final String fullName) {
      this.fullName = fullName;

      final String[] tokens = fullName.split(" ");
      this.name = tokens[0];
      this.numeral = parseRomanNumeral(tokens[1]);
    }

    private int parseRomanNumeral(final String numeral) {
      int accumulator = 0;
      final char[] chars = numeral.toCharArray();
      for (int i = 0; i < chars.length; i++) {
        int value = getLiteralValue(chars[i]);
        if (i < chars.length - 1) {
          int nextValue = getLiteralValue(chars[i + 1]);
          if (nextValue > value) {
            value *= -1;
          }
        }
        accumulator += value;
      }

      return accumulator;
    }

    private int getLiteralValue(char c) {
      switch (c) {
        case 'I': return 1;
        case 'V': return 5;
        case 'X': return 10;
        case 'L': return 50;
        case 'C': return 100;
        default: return 0;
      }
    }

    @Override
    public int compareTo(final King o) {
      int namesCompare = name.compareTo(o.getName());
      if (namesCompare == 0) {
        return numeral - o.getNumeral();
      } else {
        return namesCompare;
      }
    }
  }

  List<String> sortKings(final List<String> input) {

    final List<King> kings = input.stream()
        .map(King::new)
        .collect(Collectors.toList());

    return kings.stream()
        .sorted()
        .map(King::getFullName)
        .collect(Collectors.toList());
  }
}
