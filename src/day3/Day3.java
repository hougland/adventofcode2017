package day3;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

        final Map<Coordinates, Integer> visited = new HashMap<>();

        final Coordinates initialCoords = new Coordinates(x, y);
        visited.put(initialCoords, 1);

        String currentDirection = "right";

        for (int i = 2; i <= num; i++) {
            if ("right".equals(currentDirection)) {
                x++;

                if (!visited.containsKey(new Coordinates(x, y + 1))) {
                    currentDirection = "up";
                }

            } else if ("up".equals(currentDirection)) {
                y++;

                if (!visited.containsKey(new Coordinates(x - 1, y))) {
                    currentDirection = "left";
                }

            } else if ("left".equals(currentDirection)) {
                x--;

                if (!visited.containsKey(new Coordinates(x, y - 1))) {
                    currentDirection = "down";
                }

            } else if ("down".equals(currentDirection)) {
                y--;

                if (!visited.containsKey(new Coordinates(x + 1, y))) {
                    currentDirection = "right";
                }
            }

            final Coordinates currentCoords = new Coordinates(x, y);
            final int value = getValue(currentCoords, visited);
            visited.put(currentCoords, value);

            if (value > num) {
                System.out.println(value);
            }
        }

        return new Coordinates(x, y);
    }

    public static int getValue(final Coordinates coords, final Map<Coordinates, Integer> visited) {
        int value = 0;

        final Coordinates right = new Coordinates(coords.getX() + 1, coords.getY());
        if (visited.containsKey(right)) {
            value += visited.get(right);
        }

        final Coordinates rightUp = new Coordinates(coords.getX() + 1, coords.getY() + 1);
        if (visited.containsKey(rightUp)) {
            value += visited.get(rightUp);
        }

        final Coordinates up = new Coordinates(coords.getX(), coords.getY() + 1);
        if (visited.containsKey(up)) {
            value += visited.get(up);
        }

        final Coordinates leftUp = new Coordinates(coords.getX() - 1, coords.getY() + 1);
        if (visited.containsKey(leftUp)) {
            value += visited.get(leftUp);
        }

        final Coordinates left = new Coordinates(coords.getX() - 1, coords.getY());
        if (visited.containsKey(left)) {
            value += visited.get(left);
        }

        final Coordinates leftDown = new Coordinates(coords.getX() - 1, coords.getY() - 1);
        if (visited.containsKey(leftDown)) {
            value += visited.get(leftDown);
        }

        final Coordinates down = new Coordinates(coords.getX(), coords.getY() - 1);
        if (visited.containsKey(down)) {
            value += visited.get(down);
        }

        final Coordinates downRight = new Coordinates(coords.getX() + 1, coords.getY() - 1);
        if (visited.containsKey(downRight)) {
            value += visited.get(downRight);
        }

        return value;
    }
}
