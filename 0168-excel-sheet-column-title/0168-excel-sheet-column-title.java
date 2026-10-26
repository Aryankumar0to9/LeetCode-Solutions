class Solution {
    public String convertToTitle(int columnNumber) {
        StringBuilder result= new StringBuilder();
           int col= columnNumber;
        while(col>0){
            col --;
            int rem= col%26;
            
            char ch= (char)('A' + rem);
            result.append(ch);
            col/=26;
        }
        
        return  result.reverse().toString();
    }
}