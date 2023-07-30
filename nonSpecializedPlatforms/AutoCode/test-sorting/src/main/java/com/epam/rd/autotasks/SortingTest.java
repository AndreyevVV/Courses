package com.epam.rd.autotasks;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SortingTest {

    Sorting sorting = new Sorting();

    int[] singleElementArrayCase = new int[] {1};
    int[] emptyCase = new int[0];
    int[] testArray = new int[] {1, 2, 3, 4, 5, 6, 7};
    int[] otherCases = new int[] {1, 9, 4, 5, 5, 10};

    private void testArraysSorting (int[] a){
        int[] sorted = a.clone();
        sorting.sort(sorted);
        Arrays.sort(a);
        assertArrayEquals(a, sorted);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testNullCase(){
        sorting.sort(null);
    }

    @Test
    public void testEmptyCase(){
        testArraysSorting(emptyCase);
    }

    @Test
    public void testSingleElementArrayCase() {
        testArraysSorting(singleElementArrayCase);
    }

    @Test
    public void testSortedArraysCase() {
        testArraysSorting(testArray);
    }

    @Test
    public void testOtherCases() {
        testArraysSorting(otherCases);
    }
}