package com.example.spacetrader.entity;

public class Coordinate {
    private int x;
    private int y;

    private final int MAX_X = 100;
    private final int MAX_Y = 100;

    public Coordinate (int x, int y) {
        if (x < 0 || x > MAX_X || y < 0 || y > MAX_Y) {
            throw new IllegalArgumentException("given coordinates are out of bounds");
        }
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        // checking if both the object references are
        // referring to the same object.
        if(this == obj)
            return true;

        // it checks if the argument is of the
        // type Geek by comparing the classes
        // of the passed argument and this object.
        // if(!(obj instanceof Geek)) return false; ---> avoid.
        if(obj == null || obj.getClass()!= this.getClass())
            return false;

        // type casting of the argument.
        Coordinate coordinate = (Coordinate) obj;

        // comparing the state of argument with
        // the state of 'this' Object.
        return (coordinate.x == this.x && coordinate.y == this.y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
