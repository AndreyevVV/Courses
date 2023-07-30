package com.epam.rd.autotasks.figures;

import java.io.PrintStream;

class Triangle extends Figure{
    private final Point a;
    private final Point b;
    private final Point c;

    private final Segment firstSegment;
    private final Segment secondSegment;
    private final Segment thirdSegment;

    public Triangle(Point a, Point b, Point c) {
        Segment firstSegment = new Segment(a, b);
        Segment secondSegment = new Segment(b, c);
        Segment thirdSegment = new Segment(c, a);
        boolean pointsExistenceCondition = firstSegment.intersection(secondSegment) != null
                && secondSegment.intersection(thirdSegment) != null
                && thirdSegment.intersection(firstSegment) != null;
        if (pointsExistenceCondition) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.firstSegment = firstSegment;
            this.secondSegment = secondSegment;
            this.thirdSegment = thirdSegment;
        } else {
            throw new RuntimeException();
        }
    }
    @Override
    public double area() {
        double semiPerimeter = (this.firstSegment.length() + this.secondSegment.length() + this.thirdSegment.length()) / 2.0D;
        return Math.sqrt(semiPerimeter * (semiPerimeter - this.firstSegment.length()) *
                (semiPerimeter - this.secondSegment.length()) *
                (semiPerimeter - this.thirdSegment.length()));
    }

    public Point centroid() {
        double xCoordinateOfMass = (this.a.getX() + this.b.getX() + this.c.getX()) / 3.0D;
        double yCoordinateOfMass = (this.a.getY() + this.b.getY() + this.c.getY()) / 3.0D;
        return new Point(xCoordinateOfMass, yCoordinateOfMass);
    }

    @Override
    public String pointsToString() {
        return new String ("("+a.getX()+","+a.getY()+")"+
                                    "("+b.getX()+","+b.getY()+")"+
                                    "("+c.getX()+","+c.getY()+")");
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public Point leftmostPoint() {
        Point leftMostPoint = a;
        return leftMostPoint = leftMostPoint.getX() < b.getX()  &&
                               leftMostPoint.getX() < c.getX() ? leftMostPoint :
                               b.getX() < c.getX() ? b : c;
    }
}
