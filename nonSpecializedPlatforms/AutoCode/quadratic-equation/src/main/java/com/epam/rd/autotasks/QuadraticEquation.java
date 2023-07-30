package com.epam.rd.autotasks;


import java.util.Scanner;

public class QuadraticEquation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double c = scanner.nextDouble();
        findDiscriminant(a,b,c);
    }

    public static void findDiscriminant(double a, double b, double c) {
        double d = Math.pow(b, 2) - 4 * a * c;
        if (d > 0) {
            double root1 = (-b - Math.sqrt(d)) / (2 * a);
            double root2 = (-b + Math.sqrt(d)) / (2 * a);
            System.out.println(root1 + " " + root2);
        } else if (d == 0) {
            double x;
            x = -b / (2 * a);
            System.out.println(x);
        } else if (d < 0) {
            System.out.println("no roots");
        }
    }
}