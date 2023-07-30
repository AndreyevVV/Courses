package com.epam.rd.autotasks.spliterators;

import java.util.function.IntConsumer;

public class MySpliterator implements RectangularSpliterator {
    int[][] array;
    private int current;

    public MySpliterator(int[][] array) {
        this.array = array;
        this.current = 0;
    }

    @Override
    public RectangularSpliterator trySplit() {
        if (array.length == 1 & array[0].length == 1) return null;

        boolean isLong = array.length > array[0].length;
        int[][] newArr;
        int[][] newOldArr;
        if (isLong) {
            int mid = array.length / 2;
            newArr = new int[mid][array[0].length];
            newOldArr = new int[array.length - mid][array[0].length];
            for (int i = 0; i < mid; i++) {
                System.arraycopy(array[i], 0, newArr[i], 0, array[0].length);
            }
            for (int i = mid; i < array.length; i++) {
                System.arraycopy(array[i], 0, newOldArr[i - mid], 0, array[0].length);
            }

        } else {
            int mid = array[0].length / 2;
            newArr = new int[array.length][mid];
            newOldArr = new int[array.length][array[0].length - mid];
            for (int i = 0; i < array.length; i++) {
                System.arraycopy(array[i], 0, newArr[i], 0, mid);
            }

            for (int i = 0; i < array.length; i++) {
                if (array[0].length - mid >= 0)
                    System.arraycopy(array[i], mid, newOldArr[i], 0, array[0].length - mid);
            }
        }

        array = newOldArr;
        return new MySpliterator(newArr);

    }

    @Override
    public boolean tryAdvance(IntConsumer action) {
        if (action == null)
            throw new NullPointerException();
        if (current >= 0 && current < array.length*array[0].length) {

            int x;

            int i,j;
            i = current/array[0].length;
            j = current%array[0].length;
            x = array[i][j];
            action.accept(x);
            current++;
            return true;
        }
        return false;
    }

    @Override
    public long estimateSize() {
        return (long) array[0].length * array.length - current;
    }

    @Override
    public int characteristics() {
        return IMMUTABLE | ORDERED | SIZED | SUBSIZED | NONNULL;
    }
}
