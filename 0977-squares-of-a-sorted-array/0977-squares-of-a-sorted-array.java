class Solution {
    public int[] sortedSquares(int[] nums) {
        int n= nums.length;
        int i=0 , j=n-1;
        int[] result= new int[n];
        int index= n-1; // this is for result array (index of result array)

        while(i<=j){
            int l=nums[i]*nums[i];
             int r=nums[j]*nums[j];

             if(l>r){
                result[index]=l;
                i++;
             }
             else {
                result[index]=r;
                j--;
             }
             index --;
        }
        return result;
    }
}