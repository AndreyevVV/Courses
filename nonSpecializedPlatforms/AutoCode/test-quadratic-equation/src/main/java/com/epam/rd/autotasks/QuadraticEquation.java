package com.epam.rd.autotasks;

public class QuadraticEquation {
    public String solve(double a, double b, double c) {
        if ( a == 0 ) throw new IllegalArgumentException();
        double d = Math.pow(b, 2) - 4 * a * c;
        if (d > 0) {
            double root1 = (-b - Math.sqrt(d)) / (2 * a);
            double root2 = (-b + Math.sqrt(d)) / (2 * a);
            return root1 + " " + root2;
        }
        if (d == 0) {
            Double x = -b / (2 * a);
            return x.toString();
        }
        return "no roots";
    }
}
