import java.util.*;

public class LinkedInConnections {

    public static List<String> shortestPath(Map<String, List<String>> graph, String source, String target) {
        if (!graph.containsKey(source) || !graph.containsKey(target)) {
            return null; // Either source or target does not exist
        }

        Queue<String> queue = new LinkedList<>();
        Map<String, String> parent = new HashMap<>();
        Set<String> visited = new HashSet<>();

        queue.offer(source);
        visited.add(source);

        while (!queue.isEmpty()) {
            String current = queue.poll();

            // If we reach the target, reconstruct the path
            if (current.equals(target)) {
                return reconstructPath(parent, source, target);
            }

            // Explore neighbors
            for (String neighbor : graph.getOrDefault(current, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    queue.offer(neighbor);
                    visited.add(neighbor);
                    parent.put(neighbor, current); // Track the path
                }
            }
        }

        return null; // No path found
    }

    private static List<String> reconstructPath(Map<String, String> parent, String source, String target) {
        List<String> path = new ArrayList<>();
        for (String at = target; at != null; at = parent.get(at)) {
            path.add(at);
        }
        Collections.reverse(path); // Reverse to get the correct order
        return path;
    }

    public static void main(String[] args) {
        // Example LinkedIn graph
        Map<String, List<String>> graph = new HashMap<>();
        graph.put("Alice", Arrays.asList("Bob", "Claire"));
        graph.put("Bob", Arrays.asList("Alice", "Claire", "Dan"));
        graph.put("Claire", Arrays.asList("Alice", "Bob", "Eve"));
        graph.put("Dan", Arrays.asList("Bob", "Eve"));
        graph.put("Eve", Arrays.asList("Claire", "Dan"));

        System.out.println("Shortest Path from Alice to Eve: " + shortestPath(graph, "Alice", "Eve"));
        // Output: Shortest Path from Alice to Eve: [Alice, Claire, Eve]

        System.out.println("Shortest Path from Bob to Eve: " + shortestPath(graph, "Bob", "Eve"));
        // Output: Shortest Path from Bob to Eve: [Bob, Claire, Eve]
    }
}
