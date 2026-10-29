import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(0, candidates, target, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int start, int[] candidates, int target, List<Integer> currentList, List<List<Integer>> result) {
        // Base case: if the target becomes 0, we found a valid combination
        if (target == 0) {
            result.add(new ArrayList<>(currentList));
            return;
        }
        
        // If the target goes below 0, this path is invalid
        if (target < 0) {
            return;
        }

        // Iterate through candidates starting from 'start' to avoid duplicate combinations
        for (int i = start; i < candidates.length; i++) {
            currentList.add(candidates[i]);
            // Since numbers can be chosen unlimited times, we pass 'i' again (not 'i + 1')
            backtrack(i, candidates, target - candidates[i], currentList, result);
            // Backtrack by removing the last added element to explore other possibilities
            currentList.remove(currentList.size() - 1);
        }
    }
}