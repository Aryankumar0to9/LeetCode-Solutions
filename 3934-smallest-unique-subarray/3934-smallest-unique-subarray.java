import java.util.*;

class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;

       
        Integer[] sa = new Integer[n];
        int[] rank = nums.clone();

        for (int i = 0; i < n; i++) {
            sa[i] = i;
        }

        for (int k = 1; k < n; k <<= 1) {
            final int len = k;
            final int[] currentRank = rank;

            Arrays.sort(sa, (a, b) -> {
                if (currentRank[a] != currentRank[b]) {
                    return Integer.compare(currentRank[a], currentRank[b]);
                }

                int ra = (a + len < n) ? currentRank[a + len] : -1;
                int rb = (b + len < n) ? currentRank[b + len] : -1;

                return Integer.compare(ra, rb);
            });

            int[] newRank = new int[n];
            newRank[sa[0]] = 0;

            for (int i = 1; i < n; i++) {
                int a = sa[i - 1];
                int b = sa[i];

                int a1 = rank[a];
                int b1 = rank[b];

                int a2 = (a + k < n) ? rank[a + k] : -1;
                int b2 = (b + k < n) ? rank[b + k] : -1;

                if (a1 != b1 || a2 != b2) {
                    newRank[b] = newRank[a] + 1;
                } else {
                    newRank[b] = newRank[a];
                }
            }

            rank = newRank;

            if (rank[sa[n - 1]] == n - 1) {
                break;
            }
        }

      
        int[] pos = new int[n];

        for (int i = 0; i < n; i++) {
            pos[sa[i]] = i;
        }

       
        int[] lcp = new int[n];
        int h = 0;

        for (int i = 0; i < n; i++) {
            int r = pos[i];

            if (r == 0) {
                h = 0;
                continue;
            }

            int j = sa[r - 1];

            while (i + h < n && j + h < n &&
                   nums[i + h] == nums[j + h]) {
                h++;
            }

            lcp[r] = h;

            if (h > 0) {
                h--;
            }
        }

      
        int answer = n;

        for (int r = 0; r < n; r++) {
            int index = sa[r];

            int leftLcp = lcp[r];
            int rightLcp = (r + 1 < n) ? lcp[r + 1] : 0;

            int maxLcp = Math.max(leftLcp, rightLcp);

            int uniqueLength = maxLcp + 1;

           
            if (uniqueLength <= n - index) {
                answer = Math.min(answer, uniqueLength);
            }
        }

        return answer;
    }
}