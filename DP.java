import java.util.Arrays;

class Item {
    int weight;
    int value;
    double ratio;

    Item(int weight, int value, double ratio) {
        this.weight = weight;
        this.value = value;
        this.ratio = ratio;
    }
}

public class DP {
    // final int MAX_VALUE = 2147483647;
    public static int climbingStairs(int A) {
        int curr = 1;
        int prev = 1;
        for (int i = 2; i <= A; i++) {
            int temp = curr + prev;
            prev = curr;
            curr = temp;
        }
        return curr;
    }

    public static int Fibonacci(int N) {
        int first = 0;
        int second = 1;
        for (int i = 2; i <= N; i++) {
            int fib = first + second;
            first = second;
            second = fib;
        }
        return second;

    }

    public static int minSquares(int N) {
       
        int[] dp = new int[N+1];
        dp[0]=0;
        for(int i=1; i<=N; i++){
            int min = Integer.MAX_VALUE;
            for(int j=1; j*j <=i; j++){
                min = Math.min(min, dp[i-j*j]);
            }
            dp[i]=1+min;
        }

        return dp[N];
        
    }

    public static int maxSumWithoutAdjescent(int[][] A) {
        int n = A[0].length;
        if (n == 1)
            return Math.max(A[0][0], A[1][0]);
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = Math.max(A[0][i], A[1][i]);
        }
        dp[1] = Math.max(dp[0], dp[1]);
        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.max(dp[i] + dp[i - 2], dp[i - 1]);
        }
        return dp[n - 1];
    }

    public static int fun(int[] arr, int i , int prev, int[][] dp){
        if(i==arr.length){
            return 0;
        }
        if(dp[i][prev+1]!= -1){
            return dp[i][prev+1];
        }
        int take =0;
        if(prev == -1 || arr[i]>arr[prev]){
            take = 1  + fun(arr,i +1, i,dp);
        }
        int notTake = fun(arr, i+1, prev,dp);
        return dp[i][prev+1]=Math.max(take, notTake);
    }

    public static int longestIncreasingSubsequence(int[] nums) {
        int[][] dp = new int[nums.length][nums.length + 2];
        for(int[] arr : dp){
            Arrays.fill(arr,-1);
        }

        return fun(nums,0,-1,dp);
    }

    public static int getDigits(int[][] dp, int digit, int sum) {
        if (sum < 0)
            return 0;
        if (digit == 0 && sum == 0)
            return 1;
        if (digit == 0)
            return 0;

        if (dp[digit][sum] != -1)
            return dp[digit][sum];

        int ans = 0;
        for (int i = 0; i < 10; i++) {
            ans += getDigits(dp, digit - 1, sum - i);
            ans %= 1000000007;
        }
        dp[digit][sum] = ans;
        return ans;

    }

    public static int getDisgitEqualsSum(int digits, int sum) {
        int[][] dp = new int[digits + 1][sum + 1];
        for (int i = 0; i <= digits; i++) {
            for (int j = 0; j <= sum; j++) {
                dp[i][j] = -1;
            }
        }
        int ans = 0;
        for (int i = 1; i < 10; i++) {
            ans += getDigits(dp, digits - 1, sum - i);
            ans %= 1000000007;
        }
        return ans;
    }

    public static int getUniquePaths(int[][] grid) {
        int n = grid[0].length;
        int m = grid.length;
        int[][] dp = new int[n][m];
        if (grid[0][0] == 1)
            return 0;
        dp[0][0] = 1;
        for (int i = 1; i < n; i++) {
            if (dp[i - 1][0] == 0 || grid[i][0] == 1) {
                dp[i][0] = 0;
            } else {
                dp[i][0] = 1;
            }
        }
        for (int i = 1; i < m; i++) {
            if (dp[0][i - 1] == 0 || grid[0][i] == 1) {
                dp[0][i] = 0;
            } else {
                dp[0][i] = 1;
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (grid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }

        return dp[n - 1][m - 1];

    }

    public static int longestCommonSubsequences(String text1, String text2) {
        int n1 = text1.length();
        int n2 = text2.length();
        int[][] dp = new int[n1 + 1][n2 + 1];

        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[n1][n2];
    }

    public static int getMinLife(int[][] dp, int i, int j, int[][] A) {
        int n = A.length, m = A[0].length;
        if (i == n - 1 && j == m - 1) {
            return Math.max(1, 1 - A[n - 1][m - 1]);
        }
        if (i == n || j == m) {
            return Integer.MAX_VALUE;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int ans = Math.min(getMinLife(dp, i + 1, j, A), getMinLife(dp, i, j + 1, A)) - A[i][j];
        dp[i][j] = Math.max(1, ans);
        return dp[i][j];
    }

    public static int dungeonAndPrincess(int[][] A) {
        int n = A.length;
        int m = A[0].length;
        int[][] dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        int ans = getMinLife(dp, 0, 0, A);
        return ans;
    }

    public static int catalanNumbers(int num) {
        int[] dp = new int[num + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= num; i++) {
            dp[i] = 0;
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - 1 - j];
            }
        }
        return dp[num];
    }

    public static int cuttingRod(int[] costs) {
        int N = costs.length;
        int[] dp = new int[N + 1];
        dp[0] = 0;
        for (int length = 1; length <= N; length++) {
            dp[length] = 0;
            for (int cut = 1; cut <= length; cut++) {
                dp[length] = Math.max(dp[length], dp[length - cut] + costs[cut - 1]);
            }
        }
        return dp[N];

    }

    public static int fractionalKnapsack(int[] weights, int[] values, int maxCapacity) {
        int N = weights.length;
        Item[] items = new Item[N];
        for (int i = 0; i < N; i++) {
            items[i] = new Item(weights[i], values[i], (double) values[i] / weights[i]);
        }

        Arrays.sort(items, (item1, item2) -> Double.compare(item2.ratio, item1.ratio));
        double ans = 0;
        for (int i = 0; i < N; i++) {
            if (items[i].weight <= maxCapacity) {
                ans += items[i].value;
                maxCapacity -= items[i].weight;
            } else {
                ans = ans + (maxCapacity * items[i].ratio);
                break;
            }
        }

        return (int) Math.floor(ans);
    }

    public static int knapSack0_1A(int[] weights, int[] values, int maxCapacity) {
        int N = values.length;

        int[][] dp = new int[N + 1][maxCapacity + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= maxCapacity; j++) {
                if (j - weights[i - 1] >= 0) {
                    dp[i][j] = Math.max(dp[i - 1][j], values[i - 1] + dp[i - 1][j - weights[i - 1]]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[N][maxCapacity];

        // Optimised Version:
        // int N = values.length;
        // int[] dp = new int[maxCapacity + 1];

        // // Fill dp array
        // for (int i = 0; i < N; i++) {
        // for (int j = maxCapacity; j >= weights[i]; j--) {
        // dp[j] = Math.max(dp[j], values[i] + dp[j - weights[i]]);
        // }
        // }

        // return dp[maxCapacity];
    }

    public static int unboundedKnapsack(int[] weights, int[] values, int maxCapacity) {
        int N = values.length;
        int[][] dp = new int[N + 1][maxCapacity + 1];
        for (int i = 0; i <= N; i++) {
            dp[i][0] = 0;
        }

        for (int j = 0; j <= maxCapacity; j++) {
            dp[0][j] = 0;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= maxCapacity; j++) {
                if (j - weights[i - 1] >= 0) {
                    dp[i][j] = Math.max(dp[i - 1][j], values[i - 1] + dp[i][j - weights[i - 1]]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[N][maxCapacity];

    }

    public static int noOfWaysToMakeSum(int[] A, int sum){
        int N = A.length;
        int[] dp = new int[sum+1];
        dp[0]=1;
        for(int i=0; i<N; i++){
            for(int j=A[i]; j<=sum; j++){
                dp[j]= dp[j]+dp[j-A[i]];
            }
        }
        return dp[sum];
    }

    public static void main(String[] args) {
        /*
         * 1. You are climbing a staircase and it takes A steps to reach the top.
         * Each time you can either climb 1 or 2 steps. In how many distinct ways can
         * you climb to the top?
         */
        int steps = climbingStairs(3);
        System.out.println("Steps: " + steps);

        /*
         * 2. Given a positive integer A, write a program to find the Ath Fibonacci
         * number.
         * In a Fibonacci series, each term is the sum of the previous two terms and the
         * first two terms of the series are 0 and 1. i.e. f(0) = 0 and f(1) = 1. Hence,
         * f(2) = 1, f(3) = 2, f(4) = 3 and so on.
         * NOTE: 0th term is 0. 1th term is 1 and so on.
         */
        int fib = Fibonacci(5);
        System.out.println("Fibonacci: " + fib);

        /*
         * 3. Given an integer A. Return minimum count of numbers, sum of whose squares
         * is
         * equal to A.
         */

        int squares = minSquares(6);
        System.out.println("Min no of squares: " + squares);

        /*
         * 4. Given a 2 x N grid of integers, A, your task is to choose numbers from the
         * grid such that sum of these numbers is maximized.
         * However, you cannot choose two numbers that are adjacent horizontally,
         * vertically, or diagonally.
         * Return the maximum possible sum.
         * Note: You are allowed to choose more than 2 numbers from the grid.
         */
        int[][] mat = { { 1, 2, 3, 4 }, { 2, 3, 4, 5 } };
        int maxSum = maxSumWithoutAdjescent(mat);
        System.out.println("Max sum without adjescent element is: " + maxSum);

        /*
         * 5. The Longest Increasing Subsequence (LIS) problem is to find the length of
         * the
         * longest subsequence of a given sequence such that all elements of the
         * subsequence are sorted in increasing order.
         */
        int[] arr = { 10, 22, 9, 33, 21, 50, 41, 60, 80 };
        int longest = longestIncreasingSubsequence(arr);
        System.out.println("Longest increasing subsequence is of length: " + longest);

        /*
         * 6. Find out the number of A digit positive numbers, whose digits on being
         * added
         * equals to a given number B.
         * Note that a valid number starts from digits 1-9 except the number 0 itself.
         * i.e. leading zeroes are not allowed.
         * Since the answer can be large, output answer modulo 1000000007
         */

        int digits = 2;
        int sumEquals = 4;
        int noOfDigits = getDisgitEqualsSum(digits, sumEquals);
        System.out.println("No of digits to form the given sum is: " + noOfDigits);

        /*
         * 7. Given a grid of size n * m, lets assume you are starting at (1,1) and your
         * goal is to reach (n, m).
         * At any instance, if you are on (x, y), you can either go to (x, y + 1) or (x
         * + 1, y).
         * Now consider if some obstacles are added to the grids.
         * Return the total number unique paths from (1, 1) to (n, m).
         * Note:
         * 1. An obstacle is marked as 1 and empty space is marked 0 respectively in the
         * grid.
         * 2. Given Source Point and Destination points are 1-based index.
         */

        int[][] gridMap = { { 0, 0, 0 }, { 0, 1, 0 }, { 0, 0, 0 } };
        int noOfUniquePaths = getUniquePaths(gridMap);
        System.out.println("Number of unique paths are: " + noOfUniquePaths);

        /*
         * 8. Given two strings text1 and text2, return the length of their longest
         * common subsequence. If there is no common subsequence, return 0.
         * A subsequence of a string is a new string generated from the original string
         * with some characters (can be none) deleted without changing the relative
         * order of the remaining characters.
         * For example, "ace" is a subsequence of "abcde".
         * A common subsequence of two strings is a subsequence that is common to both
         * strings.
         */

        String text1 = "abcdef";
        String text2 = "ace";
        int longestCommon = longestCommonSubsequences(text1, text2);
        System.out.println("Longest common subsequence length is: " + longestCommon);

        /*
         * 9. The demons had captured the princess and imprisoned her in the
         * bottom-right
         * corner of a dungeon. The dungeon consists of M x N rooms laid out in a 2D
         * grid. Our valiant knight was initially positioned in the top-left room and
         * must fight his way through the dungeon to rescue the princess.
         * The knight has an initial health point represented by a positive integer. If
         * at any point his health point drops to 0 or below, he dies immediately.
         * Some of the rooms are guarded by demons, so the knight loses health (negative
         * integers) upon entering these rooms; other rooms are either empty (0's) or
         * contain magic orbs that increase the knight's health (positive integers).
         * In order to reach the princess as quickly as possible, the knight decides to
         * move only rightward or downward in each step.
         * Given a 2D array of integers A of size M x N. Find and return the knight's
         * minimum initial health so that he is able to rescue the princess.
         */
        int[][] dungeon = { { -2, -3, 3 }, { -5, -10, 1 }, { 10, 30, -5 } };
        int minlife = dungeonAndPrincess(dungeon);
        System.out.println("Minimum life required to save princess: " + minlife);

        /*
         * 10. Given an integer A, how many structurally unique BST's (binary search
         * trees) exist that can store values 1...A?
         */
        int num = 4;
        int catalan = catalanNumbers(num);
        System.out.println("Catalan number ans is: " + catalan);

        /*
         * 11. Given a rod of length N units and an array A of size N denotes prices
         * that contains prices of all pieces of size 1 to N.
         * Find and return the maximum value that can be obtained by cutting up the rod
         * and selling the pieces.ƒ
         */

        int[] costs = { 3, 4, 1, 6, 2 };
        int maxCost = cuttingRod(costs);
        System.out.println("Maximum costs of cutting rod is: " + maxCost);

        int[] values = { 60, 100, 120 };
        int[] weights = { 10, 20, 30 };
        int maxCapacity = 50;

        /*
         * 12. Given two integer arrays A and B of size N each which represent values
         * and
         * weights associated with N items respectively.
         * Also given an integer C which represents knapsack capacity.
         * Find out the maximum total value that we can fit in the knapsack. If the
         * maximum total value is ans, then return ⌊ans × 100⌋ , i.e., floor of (ans ×
         * 100).
         * 
         * NOTE: You can break an item for maximizing the total value of the knapsack
         */

        int maxValue = fractionalKnapsack(weights, values, maxCapacity);
        System.out.println("Maximum Value we can achieve in Freactional Knapsack is: " + maxValue);

        /*
         * 13. (0-1 Knapsack) Given two integer arrays A and B of size N each which
         * represent values and
         * weights associated with N items respectively.
         * Also given an integer C which represents knapsack capacity.
         * Find out the maximum value subset of A such that sum of the weights of this
         * subset is smaller than or equal to C.
         * 
         * NOTE:You cannot break an item, either pick the complete item, or don’t pick
         * it (0-1 property).
         */

        int maxValue1 = knapSack0_1A(weights, values, maxCapacity);
        System.out.println("Maximum Value we can achieve in 0-1 Knapsack is: " + maxValue1);

        /*
         * 14. Given a knapsack weight A and a set of items with certain value B[i] and
         * weight C[i], we need to calculate maximum amount that could fit in this
         * quantity.
         * This is different from classical Knapsack problem, here we are allowed to use
         * unlimited number of instances of an item.
         */

        int maxValue2 = unboundedKnapsack(weights, values, maxCapacity);
        System.out.println("Maximum value we can achieven in an Unbounded Knapsack is: " + maxValue2);

        /*
         * You are given a set of coins A. In how many ways can you make sum B assuming
         * you have infinite amount of each coin in the set.
         * 
         * NOTE:
         * Coins in set A will be unique. Expected space complexity of this problem is
         * O(B).
         */
        int[] coins ={1, 2, 3}; 
        int maxCoins = noOfWaysToMakeSum(coins, 4);
        System.out.println("No of ways to make coins sum is: "+maxCoins);
    }
}
