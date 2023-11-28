package agh.ics.oop.model;

        import agh.ics.oop.model.util.MapVisualizer;

        import java.util.*;

public class GrassField extends AbstractWorldMap {
    private final int numGrassPatches;
    private final int fieldSize;
    private final Set<Vector2d> grassPatches;

    public GrassField(int numGrassPatches) {
        this.numGrassPatches = numGrassPatches;
        this.fieldSize = (int) Math.sqrt(numGrassPatches * 10);
        this.grassPatches = generateGrassPatches();
    }

    private Set<Vector2d> generateGrassPatches() {
        Set<Vector2d> patches = new HashSet<>();
        Random random = new Random();
        while (patches.size() < numGrassPatches) {
            int x = random.nextInt(fieldSize);
            int y = random.nextInt(fieldSize);
            patches.add(new Vector2d(x, y));
        }
        return patches;
    }
    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        if (super.isOccupied(position)) {
            return true;
        }
        return grassPatches.contains(position);
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        WorldElement element = super.objectAt(position);
        if (element != null) {
            return element;
        } else if (grassPatches.contains(position)) {
            return new Grass(position);
        }
        return null;
    }

    @Override
    public String toString() {
        MapVisualizer mapVisualizer = new MapVisualizer(this);
        Vector2d lowerLeft = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
        Vector2d upperRight = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);

        for (WorldElement element : getElements()) {
            Vector2d position = element.getPosition();
            lowerLeft = lowerLeft.lowerLeft(position);
            upperRight = upperRight.upperRight(position);
        }

        return mapVisualizer.draw(lowerLeft, upperRight);
    }

    @Override
    public Collection<WorldElement> getElements() {
        List<WorldElement> allElements = new ArrayList<>(super.getElements());
        for (Vector2d position : grassPatches) {
            allElements.add(new Grass(position));
        }
        return allElements;
    }
}