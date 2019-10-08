package karatechop;

import static java.lang.Math.floorDiv;

class KarateChopGenerics<T extends Comparable<T>> {

    int find(T item, T[] array) {
        int low = 0;
        int high = array.length - 1;

        while (high >= low) {
            int index = low + floorDiv(high - low, 2);

            final T current = array[index];
            if (item.equals(current)) return index;

            if (item.compareTo(current) > 0) low = index + 1;
            else high = index - 1;
        }
        return -1;
    }
}
