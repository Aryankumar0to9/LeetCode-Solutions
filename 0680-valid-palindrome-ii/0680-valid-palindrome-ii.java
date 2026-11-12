class Solution {
    public boolean validPalindrome(String s) {
        int i=0 , j=s.length()-1;

        while(i<j){
            if(s.charAt(i)==s.charAt(j)){
                
                i++;
                j--;
            }
            else{
                return isPlalindrome(s,i+1, j)|| isPlalindrome(s,i,j-1);
            }
        }
        return true;
    }

    public boolean isPlalindrome(String s, int i, int j){

        while(i<j){
            if(s.charAt(i)!=s.charAt(j)){
                return false;
            }

            i++;
            j--;
        }
        return true;
    }
    
    }

// time = o(n)
// space o(1)