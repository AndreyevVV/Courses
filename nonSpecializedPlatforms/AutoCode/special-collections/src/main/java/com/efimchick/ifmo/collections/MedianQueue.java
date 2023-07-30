package com.efimchick.ifmo.collections;

import java.util.*;
import java.util.stream.IntStream;

import static java.util.Arrays.stream;

class MedianQueue<E> extends ArrayDeque<E> {

    private int median;

    @Override
    public boolean offer(E e) {

        return super.offer(e);/*+++*/
    }

    @Override
    public E poll() {
        Object[] elements = (Object[]) super.toArray();
        super.clear();
        Arrays.sort(elements);
        Integer result = searchMedian(elements);
        List<E> list = new ArrayList(Arrays.asList(elements));
        list.remove(median);
        list.forEach(super::offer);
        return (E) result;
    }

    @Override
    public E peek() {
        Object[] elements = (Object[]) super.toArray();
        Integer head = searchMedian(elements);
        return (E) head;
    }

    @Override
    public int size() {
        return super.size();/*+++*/
    }

    @Override
    public Iterator<E> iterator() { /*+++*/
        return super.iterator();
    }
    static final int inc(int i, int modulus) {
        if (++i >= modulus) i = 0;
        return i;
    }

    static final <E> E elementAt(Object[] es, int i) {
        return (E) es[i];
    }

    public Integer searchMedian(Object[] a) {
        Arrays.sort(a);
        int length = a.length;
        int result = 0, mid = 0, midNext = 0;
        if ((length % 2) != 0) {
            median = (length / 2);
            result = (int) a[median];
        } else {
            mid = (length / 2)-1;
            midNext = mid + 1;
            median = (Math.min(mid, midNext));
            result = (int) a[median];
        }
        return result;
    }

}
