package com.epam.rd.autotasks;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class QuadraticEquationZeroACasesTesting {
    protected QuadraticEquation quadraticEquation = new QuadraticEquation();

    private double a, b, c;

    @Parameterized.Parameters
    public static Collection<Object[]> input() {
        return Arrays.asList(new Object[][]{
                {0.0,0.0,0.0},
                {0.0,0.0,1.0},
                {0.0,1.0,0.0},
                {0.0,2.0,5.0}
        });
    }

    public QuadraticEquationZeroACasesTesting(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Test(expected = IllegalArgumentException.class)
    public void test() {
        quadraticEquation.solve(a,b,c);
    }
}
