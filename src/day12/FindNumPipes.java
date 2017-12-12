package day12;

import java.util.*;

public class FindNumPipes {
    private Map<Integer, Set<Integer>> map;

    public FindNumPipes(
            Map<Integer, Set<Integer>> map
    ) {
        this.map = map;
    }

    public Set<Integer> getConnections(
            int integer
    ) {
        final Set<Integer> visited = new HashSet<>();

        final Stack<Integer> stack = new Stack<>();
        stack.add(integer);

        while (!stack.empty()) {
            integer = stack.peek();
            stack.pop();

            if (visited.contains(integer)) {
                continue;
            } else {
                visited.add(integer);
            }

            Set<Integer> connections = map.get(integer);
            connections.forEach(stack::push);
        }

        return visited;
    }
}
