package com.efimchick.ifmo.collections;

import java.util.Comparator;
import java.util.TreeSet;

class SortedByAbsoluteValueIntegerSet<E> extends TreeSet<E> {
TreeSet
    SortedByAbsoluteValueIntegerSet(){
        super(Comparator.comparingInt(o -> Math.abs((int) o)));
    }
}
