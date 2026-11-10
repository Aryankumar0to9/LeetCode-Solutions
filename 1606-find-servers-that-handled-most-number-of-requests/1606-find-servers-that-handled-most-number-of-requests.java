import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        // TreeSet to store currently available server IDs in sorted order
        TreeSet<Integer> availableServers = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            availableServers.add(i);
        }

        // Min-heap to store busy servers: stores int[] {end_time, server_id}
        PriorityQueue<int[]> busyServers = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        int[] requestCount = new int[k];
        int maxRequests = 0;

        for (int i = 0; i < arrival.length; i++) {
            int currTime = arrival[i];
            int currLoad = load[i];

            // Free up servers whose tasks have finished by the current arrival time
            while (!busyServers.isEmpty() && busyServers.peek()[0] <= currTime) {
                availableServers.add(busyServers.poll()[1]);
            }

            // If no servers are available, drop the request
            if (availableServers.isEmpty()) {
                continue;
            }

            // Find the server starting from (i % k)
            Integer target = availableServers.ceiling(i % k);
            if (target == null) {
                target = availableServers.first(); // Wrap around to the beginning
            }

            // Assign the request to the chosen server
            availableServers.remove(target);
            busyServers.offer(new int[] { currTime + currLoad, target });
            
            requestCount[target]++;
            maxRequests = Math.max(maxRequests, requestCount[target]);
        }

        // Collect all servers that handled the maximum number of requests
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (requestCount[i] == maxRequests) {
                result.add(i);
            }
        }

        return result;
    }
}