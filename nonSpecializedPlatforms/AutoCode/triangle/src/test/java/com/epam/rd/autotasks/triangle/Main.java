package com.epam.rd.autotasks.triangle;


public class Main {
    public static void main(String[] args) {
/*
        {
            double length = new Segment(new Point(0, 0), new Point(3, 4)).length();
            System.out.println(length);
        }

        {
            Segment first = new Segment(new Point(0, 0), new Point(4, 4));
            Segment second = new Segment(new Point(2, 0), new Point(0, 2));
            Point intersection = first.intersection(second);

            System.out.println(intersection.getX());
            System.out.println(intersection.getY());
        }

        {
            Segment first = new Segment(new Point(0, 0), new Point(4, 0));
            Segment second = new Segment(new Point(2, 1), new Point(1, 2));
            Point intersection = first.intersection(second);

            System.out.println(intersection == null);
        }*/
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        {
            double area = new Triangle(new Point(0, 0), new Point(3, 0), new Point(0, 4)).area();
            System.out.println("Площадь треугольника ABC: " + area);
        }
        {
            Triangle abc = new Triangle(new Point(0, 0), new Point(3, 0), new Point(0, 3));
            Point centroid = abc.centroid();

            System.out.println("X координата центроида: " + centroid.getX());
            System.out.println("Y координата центроида: " + centroid.getY());

            abc.getA();
            abc.getB();
            abc.getC();

        }
    }
}
