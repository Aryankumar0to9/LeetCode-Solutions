class Solution {
    public int titleToNumber(String columnTitle) {
        String col=columnTitle;
        int r=0;

        for(int i=0; i<col.length(); i++){
            int val=col.charAt(i)-'A' +1;
            r=r*26+val;
        }
        return r;
    }
}