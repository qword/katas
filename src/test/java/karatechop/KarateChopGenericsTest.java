package karatechop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KarateChopGenericsTest {
    private KarateChopGenerics<Item> underTest = new KarateChopGenerics<>();

    @Test
    void shouldWorkOnSmallLists() {
        var item0 = new Item(0);
        var item1 = new Item(1);
        var item2 = new Item(2);
        var item3 = new Item(3);
        var item4 = new Item(4);
        var item5 = new Item(5);
        var item6 = new Item(6);
        var item7 = new Item(7);

        assertEquals(-1, underTest.find(item3, new Item[] {}));

        assertEquals(-1, underTest.find(item3, new Item[] {item1}));
        assertEquals(0, underTest.find(item1, new Item[] {item1}));

        assertEquals(0, underTest.find(item1, new Item[] {item1, item3, item5}));
        assertEquals(1, underTest.find(item3, new Item[] {item1, item3, item5}));
        assertEquals(2, underTest.find(item5, new Item[] {item1, item3, item5}));
        assertEquals(-1, underTest.find(item0, new Item[] {item1, item3, item5}));
        assertEquals(-1, underTest.find(item2, new Item[] {item1, item3, item5}));
        assertEquals(-1, underTest.find(item4, new Item[] {item1, item3, item5}));
        assertEquals(-1, underTest.find(item6, new Item[] {item1, item3, item5}));

        assertEquals(0, underTest.find(item1, new Item[] {item1, item3, item5, item7}));
        assertEquals(1, underTest.find(item3, new Item[] {item1, item3, item5, item7}));
        assertEquals(2, underTest.find(item5, new Item[] {item1, item3, item5, item7}));
        assertEquals(3, underTest.find(item7, new Item[] {item1, item3, item5, item7}));
        assertEquals(-1, underTest.find(item0, new Item[] {item1, item3, item5, item7}));
        assertEquals(-1, underTest.find(item2, new Item[] {item1, item3, item5, item7}));
        assertEquals(-1, underTest.find(item4, new Item[] {item1, item3, item5, item7}));
        assertEquals(-1, underTest.find(item6, new Item[] {item1, item3, item5, item7}));
    }

}
