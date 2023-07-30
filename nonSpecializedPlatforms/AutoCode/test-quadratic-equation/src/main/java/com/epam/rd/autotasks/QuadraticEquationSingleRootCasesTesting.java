package com.epam.rd.autotasks;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class QuadraticEquationSingleRootCasesTesting {
    protected QuadraticEquation quadraticEquation = new QuadraticEquation();

    private double a, b, c;
    private double expected;

    public QuadraticEquationSingleRootCasesTesting(double a, double b, double c, double expected){
        this.a = a;
        this.b = b;
        this.c = c;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> input() {
        return Arrays.asList(new Object[][]{
                {1.0,4.0,4.0, -2.0},
                {2.0,0.0,0.0, -0.0},
                {-1.0,0.0,0.0, 0.0},
                {1.0,0.0,0.0, -0.0},

        });
    }

    @Test
    public void testSingleRootsCases() {
        assertEquals(Double.toString(expected), quadraticEquation.solve(a,b,c));
    }
}