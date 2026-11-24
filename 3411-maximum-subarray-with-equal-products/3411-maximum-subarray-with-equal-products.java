class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int ans = 0;

        int maxNum = 0;
        int maxLcm = 1;

        for (int num : nums) {
            maxNum = Math.max(maxNum, num);
            maxLcm = lcm(maxLcm, num);
        }

        int limit = maxLcm * maxNum;

        for (int i = 0; i < n; i++) {
            int product = 1;
            int gcd = 0;
            int lcm = 1;

            for (int j = i; j < n; j++) {
                product *= nums[j];
                gcd = gcd(gcd, nums[j]);
                lcm = lcm(lcm, nums[j]);

                if (product > limit) {
                    break;
                }

                if (product == gcd * lcm) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }

        return ans;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private int lcm(int a, int b) {
        return (a / gcd(a, b)) * b;
    }
}