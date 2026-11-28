import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int maxFactor = 0;
        int minPrice = Integer.MAX_VALUE;

        for (int[] item : items) {
            maxFactor = Math.max(maxFactor, item[0]);
            minPrice = Math.min(minPrice, item[1]);
        }

        // Count frequency of each factor
        int[] freq = new int[maxFactor + 1];

        for (int[] item : items) {
            freq[item[0]]++;
        }

        // Count how many items have factors divisible by each factor
        int[] total = new int[maxFactor + 1];

        for (int i = 1; i <= maxFactor; i++) {
            if (freq[i] == 0) continue;

            for (int j = i; j <= maxFactor; j += i) {
                total[i] += freq[j];
            }
        }

        // Store extra-value purchases grouped by price
        TreeMap<Integer, Long> bonus = new TreeMap<>();

        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];

            // Only consider purchases that can provide better value
            if (price >= 2L * minPrice) continue;

            int gifts = total[factor] - 1;

            if (gifts > 0) {
                bonus.put(price, bonus.getOrDefault(price, 0L) + gifts);
            }
        }

        long remaining = budget;
        long answer = 0;

        // Buy items that provide 2 copies per purchase (1 paid + 1 free)
        for (Map.Entry<Integer, Long> entry : bonus.entrySet()) {
            int price = entry.getKey();
            long count = entry.getValue();

            long buy = Math.min(remaining / price, count);

            answer += buy * 2;
            remaining -= buy * price;
        }

        answer += remaining / minPrice;

        return (int) answer;
    }
}