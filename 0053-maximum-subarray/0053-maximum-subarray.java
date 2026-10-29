class Solution {
    public int maxSubArray(int[] nums) {
        int currentSum = nums[0];
        int maxSum = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            // Decide whether to add the current element to the existing subarray 
            // or start a fresh subarray from the current element.
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            
            // Update the global maximum sum found so far.
            maxSum = Math.max(maxSum, currentSum);
        }
        
        return maxSum;
    }
}