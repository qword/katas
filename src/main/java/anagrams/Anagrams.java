package anagrams;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Input: list of words separated by new lines (could be stored in a file)
 * Output: same list, but on every line there are also the anagrams of the initial word; valid
 * anagrams are to be taken from the wordlist.txt file
 *
 * Searching all the dictionary for every word is plain crazy. I have to prepare the dictionary:
 * for every word, get its 'sorted' version: that is, the word generated sorting all its letters.
 * Put everything in a map in which the 'sorted' word is key and the value is the list of anagrams.
 */
class Anagrams {
  private final Map<String, List<String>> anagramsMap;

  Anagrams(final List<String> dictionary) {
    anagramsMap = dictionary.stream()
        .collect(Collectors.groupingBy(Anagrams::getSortedWord));
  }

  static String getSortedWord(final String input) {
    return input
        .codePoints()
        .sorted()
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();
  }

  List<String> findAnagrams(final String input) {
    return anagramsMap.get(getSortedWord(input));
  }
}
