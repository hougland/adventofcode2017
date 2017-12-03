package day3;

public class Coordinates {
    private int x;
    private int y;

    public Coordinates(final int x, final int y) {
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
        if ((obj == null) || (getClass() != obj.getClass())) {
            return false;
        }

        Coordinates coords = (Coordinates) obj;
        return x == coords.getX() && y == coords.getY();
    }

    @Override
    public int hashCode() {
        return x + y;
    }
}
