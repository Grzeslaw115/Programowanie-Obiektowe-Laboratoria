package agh.ics.oop.model;

public class RectangularMap extends AbstractWorldMap {
    private final int width;
    private final int height;

    public RectangularMap(int width, int height){
        this.width = width;
        this.height = height;
    }

    public static boolean isBetween(int value, int minValue, int maxValue) {
        return value >= minValue && value <= maxValue;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return isBetween(position.getX(), 0, width - 1) &&
                isBetween(position.getY(), 0, height - 1) &&
                !isOccupied(position);
    }

    @Override
    public Boundary getCurrentBounds() {
        Vector2d lowerLeft = new Vector2d(0, 0);
        Vector2d upperRight = new Vector2d(width - 1, height - 1);
        return new Boundary(lowerLeft, upperRight);
    }
}