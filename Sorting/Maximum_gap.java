
class Solution {
    public int maximumGap(int[] nums) {
        int n = nums.length;

        if (n < 2) {
            return 0;
        }

        int min = nums[0];
        int max = nums[0];

        // Step 1: Find minimum and maximum
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        if (min == max) {
            return 0;
        }

        // Step 2: Calculate bucket size and count
        int bucketSize = Math.max(1, (max - min) / (n - 1));
        int bucketCount = (max - min) / bucketSize + 1;

        int[] bucketMin = new int[bucketCount];
        int[] bucketMax = new int[bucketCount];
        boolean[] used = new boolean[bucketCount];

        // Step 3: Initialize buckets
        for (int i = 0; i < bucketCount; i++) {
            bucketMin[i] = Integer.MAX_VALUE;
            bucketMax[i] = Integer.MIN_VALUE;
        }

        // Step 4: Distribute numbers into buckets
        for (int num : nums) {
            int index = (num - min) / bucketSize;

            bucketMin[index] = Math.min(bucketMin[index], num);
            bucketMax[index] = Math.max(bucketMax[index], num);
            used[index] = true;
        }

        // Step 5: Find maximum gap between buckets
        int answer = 0;
        int previousMax = min;

        for (int i = 0; i < bucketCount; i++) {
            if (!used[i]) {
                continue;
            }

            answer = Math.max(answer, bucketMin[i] - previousMax);
            previousMax = bucketMax[i];
        }

        return answer;
    }
}