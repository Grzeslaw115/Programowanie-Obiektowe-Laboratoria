package agh.ics.oop.model;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Vector2d {
    private final int x;
    private final int y;

     Vector2d(int x, int y){
        this.x = x;
        this.y = y;
    }

    boolean precedes(Vector2d other){
         return this.x <= other.getX() && this.y <= other.getY();
    }

    boolean follows(Vector2d other){
         return this.x >= other.getX() && this.y >= other.getY();
    }

     Vector2d add(Vector2d other){
        int new_x = this.getX() + other.getX();
        int new_y = this.getY() + other.getY();
        return new Vector2d(new_x, new_y);
    }

    Vector2d subtract(Vector2d other){
        int new_x = this.getX() - other.getX();
        int new_y = this.getY() - other.getY();
        return new Vector2d(new_x, new_y);
    }

    Vector2d upperRight(Vector2d other){
        int new_x = max(this.getX(), other.getX());
        int new_y = max(this.getY(), other.getY());
        return new Vector2d(new_x, new_y);
    }

    Vector2d lowerLeft(Vector2d other){
        int new_x = min(this.getX(), other.getX());
        int new_y = min(this.getY(), other.getY());
        return new Vector2d(new_x, new_y);
    }

    Vector2d opposite(){
         return new Vector2d(this.getX() * (-1), this.getY() * (-1));
    }

    public int getX() {
         return x;
    }

    public int getY() {
         return y;
    }

    @Override
    public String toString() {
         return "(%d, %d)".formatted(this.x, this.y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2d vector2d = (Vector2d) o;
        return getX() == vector2d.getX() && getY() == vector2d.getY();
    }
}