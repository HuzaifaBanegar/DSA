import java.util.Arrays;

/**
 * arrays
 */
public class arrays {

    public static void equillibriumIndex(int[] A) {
        int leftSum = 0;
        int rightSum = 0;
        int idx = -1;
        for (int i = 0; i < A.length; i++) {
            rightSum += A[i];
        }
        for (int i = 0; i < A.length; i++) {
            rightSum -= A[i];
            if (leftSum == rightSum) {
                idx = i + 1;
                break;
            }
            leftSum += A[i];
        }
        System.out.println(idx);

    }

    public static void maxContagiousSubarray(int[] A) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
            if (sum < 0) {
                sum = 0;
            }
            max = Math.max(max, sum);
        }
        System.out.println(max);
    }

    public static void sumArraySumwithLength(int[] A, int k, int sum) {
        int ans = 0;
        for (int i = 0; i < A.length; i++) {
            ans += A[i];
            if (i >= k) {
                ans = ans - A[i - k];
            }
            if (sum == ans) {
                System.out.println("Yes");
                return;
            }
        }
        System.out.println("No");
    }

    public static void firstMissingInteger(int[] A) {
        
        int n = A.length;
        int i=0;
        while(i<n){
            int correctIdx = A[i]-1;
            if(A[i]>0 && A[i]<=n){
                if(A[correctIdx] != A[i]){
                    int temp = A[correctIdx];
                    A[correctIdx]= A[i];
                    A[i]= temp;
                }else{
                    i++;
                }
            }else{
                i++;
            }
        }

        for(int j=0; j<n; j++){
            if(A[j]!=j+1){
                System.out.println(j + 1 + " is the first missing positive Integer");
                return;
            }
        }
        System.out.println(n + 1 + " is the first missing positive Integer");

    }

    public static void rainWaterTrapped(int[] A) {
        int n = A.length;
        int[] leftMax = new int[n];
        leftMax[0] = A[0];
        int[] rightMax = new int[n];
        rightMax[n - 1] = A[n - 1];

        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], A[i]);
        }

        for (int j = n - 2; j >= 0; j--) {
            rightMax[j] = Math.max(A[j], rightMax[j + 1]);
        }
        
        int sum = 0;
        for (int i = 0; i < n; i++) {
            int water = Math.min(leftMax[i], rightMax[i]) - A[i];
            if (water < 0) {
                water = 0;
            }
            sum += water;
        }

        System.out.println(sum);

    }

    public static void main(String[] args) {

        /*
         * Problem: Given an array arr[] of size n, return an equilibrium index (if any)
         * or -1 if no equilibrium index exists. The equilibrium index of an array is an
         * index such that the sum of elements at lower indexes equals the sum of elements at
         * higher indexes.
         * Note: Return equilibrium point in 1-based indexing. Return -1 if no such
         * point exists
         */
        int[] A = { -7, 1, 5, 2, -4, 3, 0 };
        equillibriumIndex(A);

        /*
         * Problem: Given an array arr[] of size N. The task is to find the sum of the
         * contiguous
         * subarray within a arr[] with the largest sum.
         */
        int[] a = { -2, -3, 4, -1, -2, 1, 5, -3 };
        maxContagiousSubarray(a);

        /*
         * Problem: Given an array arr[], an integer k and a Sum. The task is to check
         * if there exists any subarray with K elements whose sum is equal to the given
         * sum. If any of the subarray with size K has the sum equal to the given sum
         * then print YES otherwise print NO.
         */
        int arr[] = {1, 4, 2, 10, 2, 3, 1, 0, 20}, k = 4, sum = 18;
        sumArraySumwithLength(arr, k, sum);

        /*
         * Problem: Given an unsorted integer array nums. Return the smallest positive
         * integer
         * that is not present in nums.
         * You must implement an algorithm that runs in O(n) time and uses O(1)
         * auxiliary space.
         */

        int [] A1 = {1,3,0};
        firstMissingInteger(A1);

        /*
         * Trapping Rainwater Problem states that given an array of N non-negative
         * integers arr[] representing an elevation map where the width of each bar is
         * 1, compute how much water it can trap after rain.
         */
        int[] A2 = {0, 1, 0, 2};
        rainWaterTrapped(A2);

    }
}