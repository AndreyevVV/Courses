package com.epam.rd.autotasks.figures;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

class Quadrilateral extends Figure {
    private final Point a, b, c, d;

    @Override
    public Point centroid() {
        Point gAcentroid = new Triangle(b,c,d).centroid();
        Point gBcentroid = new Triangle(a,c,d).centroid();
        Point gCcentroid = new Triangle(a,b,d).centroid();
        Point gDcentroid = new Triangle(a,b,c).centroid();
        return (new Segment(gAcentroid, gCcentroid)).intersection(new Segment(gBcentroid, gDcentroid));
    }

    @Override
    public boolean isTheSame(Figure figure) {

        if (figure instanceof Quadrilateral) {

            Point[] verticesFigure = new Point[]{((Quadrilateral) figure).a,
                                            ((Quadrilateral) figure).b,
                                            ((Quadrilateral) figure).c,
                                            ((Quadrilateral) figure).d};

            Point[] verticesThis = new Point[]{this.a, this.b, this.c, this.d};

            double[] xyCoordFigure = {0, 0};
            double[] xyCoordThis = {0, 0};

            for (int i = 0; i < verticesThis.length; i++) {
                xyCoordFigure[0] = Math.abs((float)(verticesFigure[i].getX() + xyCoordFigure[0]));
                xyCoordThis[0] = Math.abs((float)(verticesThis[i].getX() + xyCoordThis[0]));

                xyCoordFigure[1] = Math.abs((float)(verticesFigure[i].getY() + xyCoordFigure[1]));
                xyCoordThis[1] = Math.abs((float)(verticesThis[i].getY() + xyCoordThis[1]));
            }
            double deltaVertices = xyCoordFigure[0] - xyCoordThis[0] + xyCoordFigure[1] - xyCoordThis[1];

            double deltaCentroids = Math.abs((float)(((Quadrilateral) figure).centroid().getX() - this.centroid().getX() +
                    ((Quadrilateral) figure).centroid().getY() - this.centroid().getY()));

            if (deltaVertices == 0 && deltaCentroids == 0) {
                return true;
            } else return false;
        } else {
            return false;
        }
    }

    private final Segment firstSegment
                        ,secondSegment
                        ,thirdSegment
                        ,fourthSegment;

    public Quadrilateral(Point a, Point b, Point c, Point d) {

        Segment firstSegment = new Segment(a, b);
        Segment secondSegment = new Segment(b, c);
        Segment thirdSegment = new Segment(c, d);
        Segment fourthSegment = new Segment(d, a);

        boolean isntDegenerativeCondition = Math.abs(a.getX()*b.getY()+b.getX()*c.getY()+c.getX()*d.getY()+d.getX()*a.getY()-
                                    b.getX()*a.getY()-c.getX()*b.getY()-d.getX()*c.getY()-a.getX()*d.getY())/2 != 0;

        boolean pointsExistenceCondition = firstSegment.intersection(secondSegment) != null &&
                secondSegment.intersection(thirdSegment) != null &&
                thirdSegment.intersection(fourthSegment) != null &&
                fourthSegment.intersection(firstSegment) != null;

//        4 vectors, (AB, BC, CD, DA). The works ABxBC, BCxCD, CDxDA, DAxAB must be codirected.
//        (A.X - B.X) * (C.Y - B.Y) > (A.Y - B.Y) * (C.X - B.X)
//        for all vectors
        float dbDiagonalAreas = (float)((new Triangle(a, b, d).area())+(new Triangle(b, c, d).area()));
        float acDiagonalAreas = (float)((new Triangle(a, b, c).area())+(new Triangle(d, c, a).area()));
        boolean isConvexCondition = Math.abs(dbDiagonalAreas - acDiagonalAreas) == 0 ? true:false;

        if (isntDegenerativeCondition && pointsExistenceCondition && isConvexCondition) {
            this.a = a;
            this.b = b;
            this.d = d;
            this.c = c;

            this.firstSegment = firstSegment;
            this.secondSegment = secondSegment;
            this.thirdSegment = thirdSegment;
            this.fourthSegment = fourthSegment;
        } else {
            throw new IllegalArgumentException();
        }
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