package com.epam.rd.autotasks.figures;

class Circle extends Figure {
    private final double radius;
    private final Point centre;

    public Circle(Point centre, double radius) {
        if (centre.equals(null)) {
            throw new RuntimeException();
        } else {
            this.radius = radius;
            this.centre = centre;
        }
    }

    @Override
    public double area() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public String pointsToString() {
        return new String ("("+centre.getX()+","+centre.getY()+")");
    }

    @Override
    public Point leftmostPoint() {
        return new Point(centre.getX() - radius, centre.getY());
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "[" + pointsToString() + radius + "]";
    }
}
