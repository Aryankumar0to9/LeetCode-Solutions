//28. Find the Index of the First Occurrence in a String

class Solution {
    public int strStr(String haystack, String needle) {
        int n= haystack.length();
        int m= needle.length();

        if(m>n){
            return -1;
        }

        for(int i=0; i<=n-m; i++){
            if(haystack.substring(i, m+i).equals(needle)){
                return i;

            }
        }
        return -1;
    }
}