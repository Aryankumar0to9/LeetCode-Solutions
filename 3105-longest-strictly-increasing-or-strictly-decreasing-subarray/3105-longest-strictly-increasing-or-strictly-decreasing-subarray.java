class Solution {
    public int longestMonotonicSubarray(int[] nums) {
        int maxlen=1;
        int inclen=1;
        int declen=1;

        if(nums==null || nums.length==0){
            return 0;
        }

        for(int i=1; i<nums.length; i++){
           if(nums[i]>nums[i-1]){
            inclen++;
            declen=1;
           }

           else if(nums[i]<nums[i-1]){
            inclen=1;
            declen++;
           }
           else{
            inclen=1;
            declen=1;
           }
           maxlen= Math.max(maxlen, Math.max(inclen, declen));
        }
        return maxlen;
    }
}