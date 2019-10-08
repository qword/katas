package karatechop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class KarateChopTest {

    private KarateChop underTest;

    @BeforeEach
    void setUp() {
        underTest = new KarateChop();
    }

    @Test
    void shouldWorkWithSmallLists() {
        assertEquals(-1, underTest.find(3, new int[] {}));
        assertEquals(-1, underTest.find(3, new int[] {1}));
        assertEquals(0, underTest.find(1, new int[] {1}));

        assertEquals(0, underTest.find(1, new int[] {1, 3, 5}));
        assertEquals(1, underTest.find(3, new int[] {1, 3, 5}));
        assertEquals(2, underTest.find(5, new int[] {1, 3, 5}));
        assertEquals(-1, underTest.find(0, new int[] {1, 3, 5}));
        assertEquals(-1, underTest.find(2, new int[] {1, 3, 5}));
        assertEquals(-1, underTest.find(4, new int[] {1, 3, 5}));
        assertEquals(-1, underTest.find(6, new int[] {1, 3, 5}));

        assertEquals(0, underTest.find(1, new int[] {1, 3, 5, 7}));
        assertEquals(1, underTest.find(3, new int[] {1, 3, 5, 7}));
        assertEquals(2, underTest.find(5, new int[] {1, 3, 5, 7}));
        assertEquals(3, underTest.find(7, new int[] {1, 3, 5, 7}));
        assertEquals(-1, underTest.find(0, new int[] {1, 3, 5, 7}));
        assertEquals(-1, underTest.find(2, new int[] {1, 3, 5, 7}));
        assertEquals(-1, underTest.find(4, new int[] {1, 3, 5, 7}));
        assertEquals(-1, underTest.find(6, new int[] {1, 3, 5, 7}));
        assertEquals(-1, underTest.find(8, new int[] {1, 3, 5, 7}));

        assertEquals(0, underTest.find(1, new int[] {1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 31, 41, 51}));
        assertEquals(6, underTest.find(13, new int[] {1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 31, 41, 51}));
        assertEquals(7, underTest.find(15, new int[] {1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 31, 41, 51}));
        assertEquals(8, underTest.find(17, new int[] {1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 31, 41, 51}));
        assertEquals(13, underTest.find(51, new int[] {1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 31, 41, 51}));
    }

    @Test
    void shouldWorkWithBigList() {
        long begin = System.currentTimeMillis();
        final int size = 100000000;
        int[] array = new int[size];

        for (int i = 0; i < size; i++)
            array[i] = i;

        System.out.println("It took " + (System.currentTimeMillis() - begin) + "ms to prepare the array");

        begin = System.currentTimeMillis();
        int x = new Random().nextInt(size);
        assertEquals(x, underTest.find(x, array));
        System.out.println("It took " + (System.currentTimeMillis() - begin) + "ms to find x=" + x);

    }
}
