import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class StackQueueHashing {

    public static void longestSubarrayWithSumZero(int[] A) {

        long sum = 0;
        int n = A.length;
        int length = 0;
        HashMap<Long, Integer> map = new HashMap<>();
        map.put(sum, -1);

        for (int i = 0; i < n; i++) {
            sum += A[i];
            if (map.containsKey(sum)) {
                length = Math.max(length, i - map.get(sum));
            } else {
                map.put(sum, i);
            }
        }

        System.out.println(length);
    }

    public static void balancedParenthesis(String s) {
        Stack<Character> stack = new Stack<>();
        int n = s.length();

        for (int i = 0; i < n; i++) {
            char el = s.charAt(i);

            if (stack.isEmpty() || el == '[' || el == '{' || el == '(') {
                stack.push(el);
            } else if (!stack.isEmpty() && stack.peek() == '[' && el == ']') {
                stack.pop();
            } else if (!stack.isEmpty() && stack.peek() == '{' && el == '}') {
                stack.pop();
            } else if (!stack.isEmpty() && stack.peek() == '(' && el == ')') {
                stack.pop();
            }
        }

        System.out.println(stack.isEmpty());
    }

    public static void finalPrices(int[] prices) {
        Stack<Integer> stack = new Stack<>();
        int n = prices.length;
        int[] ans = new int[n];
        int idx = n - 1;
       
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() > prices[i]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                
                ans[idx] = prices[i];
                stack.add(prices[i]);
                idx--;
            } else {
                // System.out.println(prices[i]+" peek: "+ stack.peek());
                ans[idx] = prices[i] - stack.peek();
                stack.add(prices[i]);
                idx--;
            }

        }
        System.out.println(Arrays.toString(ans));
    }

    public static int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] smallL = new int[n];
        int[] smallR = new int[n];
        Stack<Integer> stack = new Stack<>();
        int idx = 0;
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                smallL[idx] = -1;
                idx++;
                stack.push(i);
            } else if (heights[i] > heights[stack.peek()]) {
                smallL[idx] = stack.peek();
                idx++;
                stack.push(i);
            }
        }

        while (!stack.isEmpty()) {
            stack.pop();
        }

        idx = n - 1;

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                smallR[idx] = n;
                idx--;
                stack.push(i);
            } else if (heights[i] > heights[stack.peek()]) {
                smallR[idx] = stack.peek();
                idx--;
                stack.push(i);
            }
        }
       
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int h = heights[i];
            int dist = smallR[i] - smallL[i] - 1;
            max = Math.max(max, h * dist);
        }
        return max;
    }

    public static void main(String[] args) {
        /*
         * Problem: Given an array having both positive and negative integers.Find
         * length of the
         * largest subarray with sum 0.
         */
        int[] A = { 1, -2, 1, 2 };
        longestSubarrayWithSumZero(A);

        /*
         * Problem: Given an array having both positive and negative integers.Find
         * length of the largest subarray with sum 0.
         */

        String s = "[()]{}{[()()]()}";
        balancedParenthesis(s);

        /*
         * You are given an integer array prices where prices[i] is the price of the ith
         * item in a shop.
         * There is a special discount for items in the shop. If you buy the ith item,
         * then you will receive a discount equivalent to prices[j] where j is the
         * minimum index such that j > i and prices[j] <= prices[i]. Otherwise, you will
         * not receive any discount at all.
         * Return an integer array answer where answer[i] is the final price you will
         * pay for the ith item of the shop, considering the special discount.
         */
        int[] prices = { 8, 4, 6, 2, 3 };
        finalPrices(prices);

        /*
         * Given an array of integers heights representing the histogram's bar height
         * where the width of each bar is 1, return the area of the largest rectangle in
         * the histogram.
         */
        int[] heights = { 2, 1, 5, 6, 2, 3 };
        int finalAns = largestRectangleArea(heights);
        System.out.println(finalAns);

        

    }
}
