package com.epam.rd.autotasks.figures;

import java.util.Objects;

class Circle extends Figure {
    private final double radius;
    private final Point centre;

    public Circle(Point centre, double radius) {
        if (centre == null || radius <= 0) {
            throw new IllegalArgumentException();
        } else {
            this.radius = radius;
            this.centre = centre;
        }
    }

    @Override
    public Point centroid() {
        return centre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Circle circle = (Circle) o;
        return Double.compare(circle.radius, radius) == 0 && Objects.equals(centre, circle.centre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(radius, centre);
    }

    @Override
    public boolean isTheSame(Figure figure) {
        if (figure instanceof Circle) {
            double deltaCentres = Math.abs((float)(((Circle) figure).centre.getX() - this.centre.getX() +
                                           ((Circle) figure).centre.getY() - this.centre.getY()));
            boolean sameRadius = (Math.abs((((Circle) figure).radius) - this.radius)) < 0.000001;
            if (deltaCentres == 0 && sameRadius) {
                return true;
            } else return false;
        } else {
            return false;
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
