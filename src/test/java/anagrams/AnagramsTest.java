package anagrams;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

class AnagramsTest {

  @Test
  void shouldRecognizeAnagrams() {
    final Anagrams anagrams = new Anagrams(List.of("papa", "aapp", "ppaa", "appa", "aleppo", "pape", "aleppe", "pepa", "pereppe", "paap"));
    final List<String> expectedAnagrams = List.of("aapp", "appa", "paap", "papa", "ppaa");

    final List<String> actualAnagrams = anagrams.findAnagrams("apap");
    assertTrue(areUnorderedListsEqual(expectedAnagrams, actualAnagrams));
  }

  @Test
  void shouldRecognizeAnagramsInABIGFile() {

    final InputStream inputStream = AnagramsTest.class.getResourceAsStream("/anagrams/wordlist.txt");
    final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

    final List<String> words;
    try(Stream<String> stream = reader.lines()) { // buffered reader will close the underlying input stream as well
      words = stream.collect(Collectors.toList());
    }

    final Anagrams anagrams = new Anagrams(words);


    final Set<String> foundAnagrams = words.stream()
        .filter(word -> anagrams.findAnagrams(word).size() > 1)
        .collect(Collectors.toSet());

    assertEquals(48162, foundAnagrams.size());

    final Set<String> uniqueSets = foundAnagrams.stream()
        .map(Anagrams::getSortedWord)
        .collect(Collectors.toSet());

    assertEquals(20683, uniqueSets.size());
  }

  private boolean areUnorderedListsEqual(final List<String> a, final List<String> b) {
    return (a.size() == b.size() && a.containsAll(b) && b.containsAll(a));
  }
}
