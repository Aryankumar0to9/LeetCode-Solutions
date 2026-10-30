class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        // Build adjacency list: u -> list of [v, w]
        List<int[]>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            adj[u].add(new int[]{v, w});
        }

        // Min-heap stores: [current_weight, current_node, consecutive_count]
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        
        // dist[u][count] stores the minimum weight to reach node u with a specific consecutive count
        int[][] dist = new int[n][k + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        // Start from node 0 with consecutive count 1
        pq.offer(new int[]{0, 0, 1});
        dist[0][1] = 0;

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int d = curr[0], u = curr[1], count = curr[2];

            if (d > dist[u][count]) continue;

            // Early exit if we reach the target node
            if (u == n - 1) {
                return d;
            }

            for (int[] neighbor : adj[u]) {
                int v = neighbor[0], w = neighbor[1];
                
                int nextCount = (labels.charAt(v) == labels.charAt(u)) ? count + 1 : 1;

                // Skip transitions exceeding k consecutive identical characters
                if (nextCount > k) continue;

                if (d + w < dist[v][nextCount]) {
                    dist[v][nextCount] = d + w;
                    pq.offer(new int[]{d + w, v, nextCount});
                }
            }
        }

        return -1;
    }
}