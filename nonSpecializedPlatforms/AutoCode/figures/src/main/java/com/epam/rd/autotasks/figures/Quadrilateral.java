package com.epam.rd.autotasks.figures;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

class Quadrilateral extends Figure {
    private final Point a, b, c, d;

    private final Segment firstSegment
                         ,secondSegment
                         ,thirdSegment
                         ,fourthSegment;

    public Quadrilateral(Point a, Point b, Point c, Point d) {
        Segment firstSegment = new Segment(a, b);
        Segment secondSegment = new Segment(b, c);
        Segment thirdSegment = new Segment(c, d);
        Segment fourthSegment = new Segment(d, a);

        boolean pointsExistenceCondition = firstSegment.intersection(secondSegment) != null &&
                secondSegment.intersection(thirdSegment) != null &&
                thirdSegment.intersection(fourthSegment) != null &&
                fourthSegment.intersection(firstSegment) != null;

        if (pointsExistenceCondition) {
            this.a = a;
            this.b = b;
            this.d = d;
            this.c = c;

            this.firstSegment = firstSegment;
            this.secondSegment = secondSegment;
            this.thirdSegment = thirdSegment;
            this.fourthSegment = fourthSegment;
        } else {
            throw new RuntimeException();
        }
    }

    public boolean isConvex () {
        return firstSegment.sameDirectionIsTrue(secondSegment) &&
                secondSegment.sameDirectionIsTrue(thirdSegment) &&
                thirdSegment.sameDirectionIsTrue(fourthSegment) &&
                fourthSegment.sameDirectionIsTrue(firstSegment);
    }

    @Override
    public double area() {
        return Math.abs(a.getX()*b.getY()+b.getX()*c.getY()+c.getX()*d.getY()+d.getX()*a.getY()-
                        b.getX()*a.getY()-c.getX()*b.getY()-d.getX()*c.getY()-a.getX()*d.getY())/2;
    }

    @Override
    public String pointsToString() {
        return new String ("("+a.getX()+","+a.getY()+")"+
                "("+b.getX()+","+b.getY()+")"+
                "("+c.getX()+","+c.getY()+")"+
                "("+d.getX()+","+d.getY()+")");
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() +
                "[" + new String ("("+a.getX()+","+a.getY()+")"+
                "("+b.getX()+","+b.getY()+")"+
                "("+c.getX()+","+c.getY()+")"+
                "("+d.getX()+","+d.getY()+")")
                + "]";
    }

    @Override
    public Point leftmostPoint() {
        Point[] arrayPoints = new Point[]{a, b, c, d};
        Point leftMostPoint = arrayPoints[0];
        for (int i = 1; i < arrayPoints.length; i++) {
            leftMostPoint = arrayPoints[i].getX() < leftMostPoint.getX() ? arrayPoints[i]:leftMostPoint;
        }
        return leftMostPoint;
    }
}