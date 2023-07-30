package com.epam.rd.autotasks;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.*;


import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class QuadraticEquationTwoRootsCasesTesting {
    protected QuadraticEquation quadraticEquation = new QuadraticEquation();

    private double a, b, c;
    private String expected;

    public QuadraticEquationTwoRootsCasesTesting(double a, double b, double c, String expected){
        this.a = a;
        this.b = b;
        this.c = c;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> input() {
        return Arrays.asList(new Object[][]{
                {1.0,0.0,-1.0,"1.0 -1.0"},
                {-1.0,0.0,1.0, "1.0 -1.0"},
                {1.0,-2.0,0.0, "0.0 2.0"},
                {1.0,2.0,0.0, "0.0 -2.0"}
        });
    }

    @Test
    public void testTWORootsCases() {
        String splitedExpected = expected.split(" ")[1] + " " + expected.split(" ")[0];
        assertTrue(expected.equals(quadraticEquation.solve(a,b,c)) ||
                            splitedExpected.equals(quadraticEquation.solve(a,b,c)));
    }
}