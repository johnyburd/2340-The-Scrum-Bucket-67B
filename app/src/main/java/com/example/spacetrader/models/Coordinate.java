package com.example.spacetrader.models;

public class Coordinate {
    private int x;
    private int y;

    private final int MAX_X = 100;
    private final int MAX_Y = 100;

    public Coordinate (int x, int y) {
        this.x = x;
        this.y = y;
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
}
