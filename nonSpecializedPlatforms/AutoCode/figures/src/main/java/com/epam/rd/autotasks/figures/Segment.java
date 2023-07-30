//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.epam.rd.autotasks.figures;

class Segment {
    private Point start;
    private Point end;

    public Segment(Point start, Point end) {
        if (start.getX() == end.getX() && start.getY() == end.getY()) {
            if (start.equals(end)) {
                throw new RuntimeException();
            }

            if (start.getX() == end.getX() && start.getY() == end.getY()) {
                throw new RuntimeException();
            }
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

    double scholarMult(Segment AB, Point interPoint) {
        Point mA = new Point(AB.start.getX() - interPoint.getX(), AB.start.getY() - interPoint.getY());
        Point mB = new Point(AB.end.getX() - interPoint.getX(), AB.end.getY() - interPoint.getY());
        return mA.getX() * mB.getX() + mA.getY() * mB.getY();
    }

    boolean sameDirectionIsTrue (Segment another) {
        Point commonPoint = this.intersection(another);
        return (this.start.getX() - commonPoint.getX())*(another.end.getY() - commonPoint.getY())
                <= (this.start.getY() - commonPoint.getY())*(another.end.getX() - commonPoint.getX());
    }

    Point intersection(Segment another) {
        Point intersectionPoint = this.linesIntersection(another);
        if (intersectionPoint != null) {
            double sol1 = this.scholarMult(new Segment(this.start, this.end), intersectionPoint);
            double sol2 = this.scholarMult(another, intersectionPoint);
            if (sol1 <= 0.0D && sol2 <= 0.0D) {
                return intersectionPoint;
            }
        }
        return null;
    }
}
