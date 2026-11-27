class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int mn = Integer.MAX_VALUE;
        for (int[] it : items) {
            mn = Math.min(mn, it[1]);
        }

        
        int[] dp = new int[budget + 1];

        for (int i = 0; i < n; i++) {
            int factor = items[i][0];
            int price = items[i][1];

            
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (items[j][0] % factor == 0) {
                    cnt++;
                }
            }

            
            for (int b = budget; b >= price; b--) {
                dp[b] = Math.max(dp[b], dp[b - price] + cnt);
            }
        }

        int ans = 0;
        for (int i = 0; i <= budget; i++) {
            int remaining = budget - i;
            int total = dp[i] + remaining / mn;
            ans = Math.max(ans, total);
        }
        return ans;
    }
}