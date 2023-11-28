package agh.ics.oop.model;

import java.util.Objects;

public class Grass implements WorldElement{
    private final Vector2d position;

    public Grass(Vector2d position){
        this.position = position;
    }

    public Vector2d getPosition() {
        return this.position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grass grass = (Grass) o;
        return Objects.equals(position, grass.position);
    }

    public boolean isAt(Vector2d position) {
        return position.equals(this.position);
    }

    @Override
    public String toString() {
        return "*";
    }
}
