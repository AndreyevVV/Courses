//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.epam.rd.autotasks.figures;

import org.junit.platform.commons.util.StringUtils;

import java.util.Objects;

class Segment {
    private Point start;
    private Point end;

    public Segment(Point start, Point end) {
        if (start == null || end == null){
            throw new IllegalArgumentException();
        }
        if (start.equals(end)) {
            throw new IllegalArgumentException();
        } else {
            this.start = start;
            this.end = end;
        }

    }

    double length() {
        return Math.sqrt(StrictMath.pow(this.end.getX() - this.start.getX(), 2.0D) + StrictMath.pow(this.end.getY() - this.start.getY(), 2.0D));
    }

    Point middle() {
        return new Point((this.start.getX() + this.end.getX()) / 2.0D, (this.start.getY() + this.end.getY()) / 2.0D);
    }

    Point linesIntersection(Segment another) {
        double segmentDeltaX = this.start.getX() - this.end.getX();
        double segmentDeltaY = this.start.getY() - this.end.getY();
        double anotherSegDeltaX = another.start.getX() - another.end.getX();
        double anotherSegDeltaY = another.start.getY() - another.end.getY();
        double denominator = segmentDeltaX * anotherSegDeltaY - segmentDeltaY * anotherSegDeltaX;
        double firstCommonMember = this.start.getX() * this.end.getY() - this.start.getY() * this.end.getX();
        double secondCommonMember = another.start.getX() * another.end.getY() - another.start.getY() * another.end.getX();
        double coordinateX = firstCommonMember * anotherSegDeltaX - segmentDeltaX * secondCommonMember;
        double coordinateY = firstCommonMember * anotherSegDeltaY - segmentDeltaY * secondCommonMember;
        double pY = coordinateY / denominator;
        double pX = coordinateX / denominator;
        return denominator == 0.0D ? null : new Point(pX, pY);
    }

    double divisionPointSegmentsMult(Segment AB, Point interPoint) {
        Point mA = new Point(AB.start.getX() - interPoint.getX(), AB.start.getY() - interPoint.getY());
        Point mB = new Point(AB.end.getX() - interPoint.getX(), AB.end.getY() - interPoint.getY());
        return mA.getX() * mB.getX() + mA.getY() * mB.getY();
    }

    double scholarMult (Segment another) {
        Point aVector = new Point(this.end.getX()-this.start.getX(), this.end.getY()-this.start.getY());
        Point bVector = new Point(another.end.getX()-another.start.getX(), another.end.getY()-another.start.getY());
        return aVector.getX()*bVector.getX()+aVector.getY()*bVector.getY();
    }
//        4 vectors, (AB, BC, CD, DA). The works ABxBC, BCxCD, CDxDA, DAxAB must be codirected.
//        (A.X - B.X) * (C.Y - B.Y) > (A.Y - B.Y) * (C.X - B.X)
//        (B.X - C.X) * (D.Y - C.Y) > (B.Y - C.Y) * (D.X - C.X)
//        (C.X - D.X) * (A.Y - D.Y) > (C.Y - D.Y) * (A.X - D.X)
//        (D.X - A.X) * (B.Y - A.Y) > (D.Y - A.Y) * (B.X - A.X)
    boolean sameDirectionIsTrue (Segment second) {
        Point a = this.start;
        Point b = this.end.equals(second.start) ? this.end : null;
        Point c = second.end;

        return (a.getX() - b.getX()) * (c.getY() - b.getY())
                <= (a.getY() - b.getY()) * (c.getX() - b.getX());

    }

    Point intersection(Segment another) {
        Point intersectionPoint = this.linesIntersection(another);
        if (intersectionPoint != null) {
            double sol1 = this.divisionPointSegmentsMult(new Segment(this.start, this.end), intersectionPoint);
            double sol2 = this.divisionPointSegmentsMult(another, intersectionPoint);
            if (sol1 <= 0.0D && sol2 <= 0.0D) {
                return intersectionPoint;
            }
        }
        return null;
    }
}
