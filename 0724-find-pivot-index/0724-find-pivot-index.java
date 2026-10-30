class Solution {
    public int pivotIndex(int[] nums) {
        
        int tsum=0;
        int lsum=0;

        for(int num:nums){
            tsum= tsum+num;
        }

        for(int i=0; i<nums.length; i++){
            if(lsum == tsum-lsum-nums[i]){
                return i;
            }
            lsum= lsum+nums[i];
        }
        return -1;
    }
}