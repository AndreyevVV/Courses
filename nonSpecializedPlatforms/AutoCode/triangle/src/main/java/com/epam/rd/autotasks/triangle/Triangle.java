package com.epam.rd.autotasks.triangle;

class Triangle {

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

        boolean pointsExistenceCondition = firstSegment.intersection(secondSegment) != null &&
                                            secondSegment.intersection(thirdSegment) != null &&
                                            thirdSegment.intersection(firstSegment) != null;

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

    public double area() {
        double semiPerimeter = (firstSegment.length() + secondSegment.length() + thirdSegment.length())/2;
        return Math.sqrt(semiPerimeter * (semiPerimeter- firstSegment.length())
                                                        *(semiPerimeter- secondSegment.length())
                                                        *(semiPerimeter- thirdSegment.length()));

    }

    public Point centroid(){
        double xCoordinateOfMass = (a.getX() + b.getX() + c.getX()) / 3;
        double yCoordinateOfMass = (a.getY() + b.getY() + c.getY()) / 3;

        return new Point(xCoordinateOfMass, yCoordinateOfMass);
    }

    public void getA() {
        System.out.println("Точка А: (" + a.getX() + "," + a.getY() + ")");
    }

    public void getB() {
        System.out.println("Точка B: (" + b.getX() + "," + b.getY() + ")");
    }

    public void getC() {
        System.out.println("Точка C: (" + c.getX() + "," + c.getY() + ")");
    }
}
