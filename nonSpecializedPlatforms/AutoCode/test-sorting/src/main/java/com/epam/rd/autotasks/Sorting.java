package com.epam.rd.autotasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sorting {
    public void sort(int[] array){
        if (array == null) throw new IllegalArgumentException();
        else Arrays.sort(array);
    }
}
