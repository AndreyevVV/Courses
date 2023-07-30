package com.epam.rd.autotasks.triangle;

import java.awt.*;
import static java.lang.Math.sqrt;
import static java.lang.StrictMath.pow;

class Segment {
    private Point start;
    private Point end;

    public Segment(Point start, Point end) {
        if (start.getX() != end.getX() || start.getY() != end.getY()) {
            this.start = start;
            this.end = end;
        } else if (start.equals(end)) {
            throw new RuntimeException();
        } else if (start.getX() == end.getX() && start.getY() == end.getY()) {
            throw new RuntimeException();
        }
    }

    double length() {
        return sqrt ((pow(end.getX() - start.getX(), 2)) + (pow(end.getY()- start.getY(), 2)));
    }

    Point middle() {
        return new Point((start.getX() + end.getX())/2 , (start.getY() + end.getY())/2);
    }

    Point linesIntersection(Segment another) {

        double segmentDeltaX = (start.getX() - end.getX());
        double segmentDeltaY = (start.getY() - end.getY());
        double anotherSegDeltaX = (another.start.getX() - another.end.getX());
        double anotherSegDeltaY = (another.start.getY() - another.end.getY());

        double denominator = segmentDeltaX * anotherSegDeltaY - segmentDeltaY * anotherSegDeltaX;

        double firstCommonMember = (start.getX() * end.getY() - start.getY() * end.getX()) ;

        double secondCommonMember = (another.start.getX() * another.end.getY()
                - another.start.getY() * another.end.getX());

        double coordinateX = (firstCommonMember * anotherSegDeltaX - segmentDeltaX * secondCommonMember);

        double coordinateY = (firstCommonMember * anotherSegDeltaY - segmentDeltaY * secondCommonMember);

        double pX = coordinateX / denominator;
        double pY = coordinateY / denominator;

        if (denominator == 0) {
            return null;
        } else {
            return new Point(pX, pY);
        }

    }

    double scholarMult (Segment AB, Point interPoint) {

        Point mA = new Point(AB.start.getX() - interPoint.getX(), AB.start.getY() - interPoint.getY());
        Point mB = new Point(AB.end.getX() - interPoint.getX(), AB.end.getY() - interPoint.getY());

        return mA.getX() * mB.getX() + mA.getY() * mB.getY();
    }

    Point intersection(Segment another) {
        Point intersectionPoint = linesIntersection(another);

        if (intersectionPoint != null) {

            double sol1 = scholarMult(new Segment(start, end), intersectionPoint);
            double sol2 = scholarMult(another, intersectionPoint);

            if (sol1 <= 0 && sol2 <= 0) {
                return intersectionPoint;
            }
        }
        return null;
    }
}

