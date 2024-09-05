import java.util.Arrays;
import java.util.Comparator;

public class Searching {
    public static void rotatedAndSorted(int[] A, int target) {
        int n = A.length;
        int start = 0;
        int end = n - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (A[mid] == target) {
                System.out.println("Target is at " + mid + " index");
                return;
            } else if (A[start] <= A[mid]) {
                // leftside sorted
                if (target >= A[start] && target < A[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                if (target > A[mid] && target <= A[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        System.out.println("Target is not inside the array");
    }

    public static void findMinimuminRotatedAndSorted(int[] A) {
        int n = A.length;
        int start = 0;
        int end = n - 1;
        while (start < end) {
            int mid = (start + end) / 2;
            if (A[mid] > A[end]) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        System.out.println(A[start]);
        return;
    }

    public static void merge(int[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        int L[] = new int[n1];
        int R[] = new int[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        int i = 0, j = 0;

        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    public static void mergeSort(int[] A, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(A, start, mid);
            mergeSort(A, mid + 1, end);

            merge(A, start, mid, end);
        }

    }

    public static class EucladianComparator implements Comparator<int[]> {
        @Override
        public int compare(int[] A, int[] B) {
            int x1 = A[0], y1 = A[1], x2 = B[0], y2 = B[1];
            double d1 = Math.sqrt(x1 * x1 + y1 * y1);
            double d2 = Math.sqrt(x2 * x2 + y2 * y2);

            if (d1 < d2) {
                return -1;
            } else if (d1 > d2) {
                return 1;
            } else {
                if (x1 < x2) {
                    return -1;
                } else if (x1 > x2) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
    }

    public static void closestToOrigin(int[][] A, int k) {
        Arrays.sort(A, new EucladianComparator());
        int[][] ans = new int[k][2];

        for (int i = 0; i < k; i++) {
            ans[i] = A[i];
        }

        System.out.println(Arrays.deepToString(ans));
    }

    public static void minPainters(int K, int time, int[] lengths) {
        int minValue = 0;
        int maxValue = 0;
        for (int length : lengths) {
            if (length > minValue) {
                minValue = length;
            }
            maxValue += length;
        }

        int start = minValue * time;
        int end = maxValue * time;
        int ans = -1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (isPossible(mid, K, time, lengths)) {
                ans = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        System.out.println(ans);
    }

    public static boolean isPossible(int minTime, int K, int time, int[] lengths) {
        int count = 0;
        int sum = 0;
        for (int i = 0; i < lengths.length; i++) {
            if (sum + (lengths[i] * time) > minTime) {
                sum = 0;
                count++;
            }
            sum += (lengths[i] * time);
        }

        return count <= K;

    }

    public static void angryCows (int[] stalls, int cows){
        Arrays.sort(stalls);
        int n = stalls.length;
        int start=1;
        int end = stalls[n-1]- stalls[0];
        int ans = -1;
        while(start<=end){
            int mid = (start+end)/2;
            if(isPossibleForCows(mid, cows, stalls)){
                ans = mid;
                start=mid+1;
            }else{
                end= mid-1;
            }
        }
        System.out.println(ans);
    }

    public static boolean isPossibleForCows(int minDist, int cows, int[] stalls ){
        int cowsCount=1;
        int lastPlace=0;
        for(int i=0; i<stalls.length; i++){
            if(stalls[i]-stalls[lastPlace]>=minDist){
                cowsCount++;
                lastPlace=i;
            }
            if(cowsCount == cows){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        // int[] A1 = { 6, 7, 8, 0, 0, 1, 1, 1, 2, 3, 4, 5 };
        // int n = A1.length;

        /*
         * Problem: Search and element in rotated and sorted array
         */
        // int target = 5;
        // rotatedAndSorted(A1, target);

        /*
         * Problem: Find minimum in rotated and sorted array
         */
        // findMinimuminRotatedAndSorted(A1);

        /*
         * Problem: Do merge Sort and return a sorted array.
         */
        // mergeSort(A1, 0, n - 1);
        // System.out.println(Arrays.toString(A1));

        /*
         * Problem: Given an array of points where points[i] = [xi, yi] represents a
         * point on the
         * X-Y plane and an integer k, return the k closest points to the origin (0, 0).
         * The distance between two points on the X-Y plane is the Euclidean distance
         * (i.e., √(x1 - x2)2 + (y1 - y2)2).
         * You may return the answer in any order. The answer is guaranteed to be unique
         * (except for the order that it is in).
         */

        // int[][] points = {{1,3},{-2,2}};
        // int k = 1;
        // closestToOrigin(points, k);

        /*
         * Problem: Given 2 integers A and B and an array of integers C of size N.
         * Element C[i]
         * represents the length of ith board.
         * You have to paint all N boards [C0, C1, C2, C3 … CN-1]. There are A painters
         * available and each of them takes B units of time to paint 1 unit of the
         * board.
         * 
         * Calculate and return the minimum time required to paint all boards under the
         * constraints that any painter will only paint contiguous sections of the
         * board.
         * NOTE:
         * 1. 2 painters cannot share a board to paint. That is to say, a board cannot
         * be painted partially by one painter, and partially by another.
         * 2. A painter will only paint contiguous boards. This means a configuration
         * where painter 1 paints boards 1 and 3 but not 2 is invalid.
         */
        // int[] lengths= {1, 10};
        // int B = 2;
        // int C = 5;
        // minPainters(B, C, lengths);

        /*
         * Problem: Farmer John has built a new long barn with N stalls. Given an array of
         * integers A of size N where each element of the array represents the location
         * of the stall and an integer B which represents the number of cows.

         * His cows don't like this barn layout and become aggressive towards each other
         * once put into a stall. To prevent the cows from hurting each other, John
         * wants to assign the cows to the stalls, such that the minimum distance
         * between any two of them is as large as possible. What is the largest minimum
         * distance?
         */

         int[] stalls = {1,2,3,4,5};
         int cows = 3;
         angryCows(stalls, cows);

    }

}
