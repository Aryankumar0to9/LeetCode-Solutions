
import java.util.*;

class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {

        int n = nums.length;

        Deque<int[]> dq = new ArrayDeque<>();

        int j = n - 1;

        long cost = 0;
        long ans = 0;

        for (int i = n - 1; i >= 0; i--) {

            int count = 1;

            while (!dq.isEmpty() && dq.peekLast()[0] < nums[i]) {

                int[] last = dq.pollLast();

                cost += (long)(nums[i] - last[0]) * last[1];

                count += last[1];
            }

            dq.addLast(new int[]{nums[i], count});

           
            while (cost > k) {

                int[] first = dq.pollFirst();

                cost -= first[0] - nums[j];

                j--;

                if (first[1] > 1) {
                    dq.addFirst(
                        new int[]{first[0], first[1] - 1}
                    );
                }
            }

            
            ans += j - i + 1;
        }

        return ans;
    }
}
