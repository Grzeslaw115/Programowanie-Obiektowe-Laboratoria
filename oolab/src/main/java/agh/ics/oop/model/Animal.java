package agh.ics.oop.model;

public class Animal {
    private Vector2d position;
    private MapDirection orientation = MapDirection.NORTH;

    public Animal() {
        this.position = new Vector2d(2, 2);
    }

    public Animal(Vector2d position) {
        this.position = position;
    }

    boolean isAt(Vector2d position){
        return position.equals(this.position);
    }

    public static boolean isBetween(int value, int minValue, int maxValue) {
        return value >= minValue && value <= maxValue;
    }
    public void move(MoveDirection direction){
        switch (direction){
            case LEFT -> orientation = orientation.previous();
            case RIGHT -> orientation = orientation.next();

            case FORWARD -> {
                if (isBetween(position.add(orientation.toUnitVector()).getX(), 0, 4) && isBetween(position.add(orientation.toUnitVector()).getY(), 0, 4)) {
                    position = position.add(orientation.toUnitVector());
                }
            }
            case BACKWARD ->
            {
                if (isBetween(position.add(orientation.toUnitVector().opposite()).getX(), 0, 4) && isBetween(position.add(orientation.toUnitVector().opposite()).getY(), 0, 4)){
                    position = position.add(orientation.toUnitVector().opposite());
                }
            }
        }
    }

    @Override
    public String toString() {
        return  "pozycja = " + position.toString() +
                ", orientacja = " + orientation;
    }

}
