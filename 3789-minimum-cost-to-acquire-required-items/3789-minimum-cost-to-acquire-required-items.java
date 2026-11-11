class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long commonNeed = Math.min(need1, need2);
        long rem1 = need1 - commonNeed;
        long rem2 = need2 - commonNeed;
        
        long totalCost = commonNeed * Math.min((long)costBoth, (long)cost1 + cost2);
        
        totalCost += rem1 * Math.min((long)cost1, (long)costBoth);
        
        totalCost += rem2 * Math.min((long)cost2, (long)costBoth);
        
        return totalCost;
    }
}