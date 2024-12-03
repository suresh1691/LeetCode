// We can use two Approaches one is BFS and DFS 

// In BFS We need to construct the Graph first and then construct the indegrees .. add it to the queue if indegree value is 0. and then iterate queue and explore neighbhores to find if cycle is present

public static boolean canFinish(int numCourses, int[][] prerequisites) {
        // Build graph and in-degree array
        List<List<Integer>> graph = new ArrayList<>();
        int[] inDegree = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] prereq : prerequisites) {
            int course = prereq[0];
            int prerequisite = prereq[1];
            graph.get(prerequisite).add(course);
            inDegree[course]++;
        }

        // Add all courses with in-degree 0 to the queue
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        int count = 0; // Count of courses that can be completed

        // Process the queue
        while (!queue.isEmpty()) {
            int course = queue.poll();
            count++;

            for (int neighbor : graph.get(course)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        return count == numCourses; // If all courses are processed, return true
    }

// in DFS  We need to construct the Graph first and have visited list to check wether cycle is present or not.

public static boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] prereq : prerequisites) {
            graph.get(prereq[1]).add(prereq[0]);
        }

        int[] visited = new int[numCourses]; // 0 = not visited, 1 = visiting, 2 = visited

        for (int i = 0; i < numCourses; i++) {
            if (hasCycle(graph, visited, i)) {
                return false; // Cycle detected
            }
        }

        return true;
    }

    private static boolean hasCycle(List<List<Integer>> graph, int[] visited, int course) {
        if (visited[course] == 1) {
            return true; // Cycle detected
        }

        if (visited[course] == 2) {
            return false; // Already processed
        }

        visited[course] = 1; // Mark as visiting

        for (int neighbor : graph.get(course)) {
            if (hasCycle(graph, visited, neighbor)) {
                return true;
            }
        }

        visited[course] = 2; // Mark as fully visited
        return false;
    }
