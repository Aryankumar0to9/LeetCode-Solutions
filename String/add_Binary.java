//leetcode no --67

class Solution {
    public String addBinary(String a, String b) {
        StringBuilder result = new StringBuilder();
        int i=a.length()-1;
        int j=b.length()-1;
        int carry=0;

        while(j>=0 || i>=0 || carry>0){
            int sum=carry;
        

        if(i>=0){
            sum+=a.charAt(i)-'0';
            i--;
        }
        if(j>=0){
            sum+=b.charAt(j)-'0';
          
            j--;
        }
        result.append(sum%2);
        carry= sum/2;

        }
        return result.reverse().toString();
    }
}