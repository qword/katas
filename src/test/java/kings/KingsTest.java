package kings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import kings.Kings.King;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class KingsTest {

  private Kings underTest = new Kings();

  @Test
  void shouldSortDifferentNames() {
    final List<String> unordered = List.of("Charlos L", "William VII", "George VI", "Mary IV", "Charles II", "Edward I");
    final List<String> ordered = List.of("Charles II", "Charlos L", "Edward I", "George VI", "Mary IV", "William VII");

    final List<String> actual = underTest.sortKings(unordered);

    Assertions.assertArrayEquals(ordered.toArray(), actual.toArray());
  }

  @Test
  void shouldConvertRomanNumeralsToIntegers() {
    assertEquals(1, new King("King I").getNumeral());
    assertEquals(2, new King("King II").getNumeral());
    assertEquals(3, new King("King III").getNumeral());
    assertEquals(4, new King("King IV").getNumeral());
    assertEquals(5, new King("King V").getNumeral());
    assertEquals(6, new King("King VI").getNumeral());
    assertEquals(7, new King("King VII").getNumeral());
    assertEquals(8, new King("King VIII").getNumeral());
    assertEquals(9, new King("King IX").getNumeral());
    assertEquals(10, new King("King X").getNumeral());
    assertEquals(11, new King("King XI").getNumeral());
  }

  @Test
  void shouldSortSameNameDifferentNumber() {
    final List<String> unordered = List.of("Edward IX", "Edward VII", "Edward VI", "Edward IV", "Edward II", "Edward VIII", "Edward I");
    final List<String> ordered = List.of("Edward I", "Edward II", "Edward IV", "Edward VI", "Edward VII", "Edward VIII", "Edward IX");

    final List<String> actual = underTest.sortKings(unordered);

    Assertions.assertArrayEquals(ordered.toArray(), actual.toArray());
  }

}
