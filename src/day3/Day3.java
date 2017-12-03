package day3;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Day3 {

    public static void main(String[] args) throws IOException {
        final int input = 347991;

        final Coordinates coordinates = getCoordinates(input);

        int distance = Math.abs(coordinates.getX()) + Math.abs(coordinates.getY());

        System.out.println(distance);
    }

    public static Coordinates getCoordinates(final int num) {
        int x = 0;
        int y = 0;

        final Set<Coordinates> visited = new HashSet<>();
        visited.add(new Coordinates(x, y));

        String currentDirection = "right";

        for (int i = 2; i <= num; i++) {
            if ("right".equals(currentDirection)) {
                x++;

                if (!visited.contains(new Coordinates(x, y + 1))) {
                    currentDirection = "up";
                }

            } else if ("up".equals(currentDirection)) {
                y++;

                if (!visited.contains(new Coordinates(x - 1, y))) {
                    currentDirection = "left";
                }

            } else if ("left".equals(currentDirection)) {
                x--;

                if (!visited.contains(new Coordinates(x, y - 1))) {
                    currentDirection = "down";
                }

            } else if ("down".equals(currentDirection)) {
                y--;

                if (!visited.contains(new Coordinates(x + 1, y))) {
                    currentDirection = "right";
                }
            }

            visited.add(new Coordinates(x, y));
        }

        return new Coordinates(x, y);
    }

    public static class Coordinates {
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
}
