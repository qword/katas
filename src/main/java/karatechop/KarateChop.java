package karatechop;

import static java.lang.Math.floorDiv;

/**
 * http://codekata.com/kata/kata02-karate-chop/
 * In short: binary search
 *
 * Code should be small enough not ot require TDD, still some tests will be written to check code behaviour. Plan is:
 * Run 1: simple list with integers, iterative version
 * Run 2: try with generics
 * Run 3: generics and recursive
 */
class KarateChop {

    /**
     * First try, it works but the checks on left and right limits are really sloppy
     */
    @Deprecated()
    int find1(int value, int[] array) {
        if (array.length == 0) return -1;

        int step = 0;
        int low = 0;
        int high = array.length -1;
        int index = -1;

        while (true) {
            System.out.println("Step " + ++step);
            int newIndex = low + floorDiv(high - low, 2);

            if (newIndex == index){
                 if (array.length > index + 1 && array[index + 1] == value) return index + 1; // edge case
                else return -1;
            }

            index = newIndex;
            if (array[index] == value) return index;
            if (low == high) return -1;

            if (value > array[index]){
                low = index;
                System.out.println("\tnew low " + low);
            }
            else {
                high = index;
                System.out.println("\tnew high " + high);
            }
        }
    }

    /**
     * Refactor from previous version based on a quick google search :)
     */
    @Deprecated
    int find2(int value, int[] array) {
        int step = 0;
        int low = 0;
        int high = array.length - 1;

        while (high >= low) {
            step++;
            int index = low + floorDiv(high - low, 2);

            if (array[index] == value) {
                System.out.println("Finished (found) in " + step + "step(s)");
                return index;
            }

            if (value > array[index]){
                low = index + 1;
            } else {
                high = index - 1;
            }
        }

        System.out.println("Finished (NOT found) in " + step + "step(s)");
        return -1;
    }

    /**
     * Second attempt from scratch, now with a recursive approach
     */
    int find(int value, int[] array) {
        return findRecursive(value, array, 0, array.length - 1);
    }

    private int findRecursive(final int value, final int[] array, final int low, final int high) {
        if (high < low) return -1;

        int index = low + floorDiv(high - low, 2);
        if (array[index] == value) return index;

        if (value > array[index]) return findRecursive(value, array, index + 1, high);
        else return findRecursive(value, array, low, index - 1);
    }
}
