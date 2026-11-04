import java.util.*;

class Solution {

    class MultiSet {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int size = 0;
        long sum = 0;

        void add(int val) {
            map.put(val, map.getOrDefault(val, 0) + 1);
            size++;
            sum += val;
        }

        void remove(int val) {
            int count = map.get(val);
            if (count == 1) {
                map.remove(val);
            } else {
                map.put(val, count - 1);
            }
            size--;
            sum -= val;
        }

        int first() {
            return map.firstKey();
        }

        int last() {
            return map.lastKey();
        }

        boolean contains(int val) {
            return map.containsKey(val);
        }
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long[] costs = getCosts(nums, x);

        long INF = Long.MAX_VALUE / 4;
        long[][] dp = new long[n + 1][k + 1];

        for (long[] row : dp) {
            Arrays.fill(row, INF);
        }

        dp[0][0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                // Option 1: Skip the current element
                dp[i][j] = dp[i - 1][j];

                // Option 2: Choose a window ending at index i - 1
                if (j > 0 && i >= x) {
                    dp[i][j] = Math.min(
                        dp[i][j],
                        dp[i - x][j - 1] + costs[i - x]
                    );
                }
            }
        }

        return dp[n][k];
    }

    private long[] getCosts(int[] nums, int x) {
        int n = nums.length;
        long[] costs = new long[n - x + 1];

        MultiSet lower = new MultiSet();
        MultiSet upper = new MultiSet();

        int targetSize = (x + 1) / 2;

        for (int i = 0; i < n; i++) {
            int val = nums[i];

            // 1. Insert into appropriate set
            if (lower.size == 0 || val <= lower.last()) {
                lower.add(val);
            } else {
                upper.add(val);
            }

            // 2. Remove element going outside the sliding window
            if (i >= x) {
                int out = nums[i - x];
                if (lower.contains(out)) {
                    lower.remove(out);
                } else {
                    upper.remove(out);
                }
            }

            // 3. Balance the sets safely
            while (lower.size > targetSize) {
                int move = lower.last();
                lower.remove(move);
                upper.add(move);
            }

            while (lower.size < targetSize && !upper.map.isEmpty()) {
                int move = upper.first();
                upper.remove(move);
                lower.add(move);
            }

            // 4. Calculate total operations to reach median once window is size x
            if (i >= x - 1) {
                int median = lower.last();

                long leftCost = (long) median * lower.size - lower.sum;
                long rightCost = upper.sum - (long) median * upper.size;

                costs[i - x + 1] = leftCost + rightCost;
            }
        }

        return costs;
    }
}

