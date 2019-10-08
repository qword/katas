package karatechop;

import lombok.Data;

@Data
public class Item implements Comparable<Item>{
    private final int value;

    @Override
    public int compareTo(final Item o) {
        if (o == null) throw new NullPointerException();
        return this.value - o.getValue();
    }
}
