package leetcode.contests;
/**
 * Given an array A of 0s and 1s, divide the array into 3 non-empty parts such that all of these parts represent the same binary value.
 * <p>
 * If it is possible, return any [i, j] with i+1 < j, such that:
 * <p>
 * A[0], A[1], ..., A[i] is the first part;
 * A[i+1], A[i+2], ..., A[j-1] is the second part, and
 * A[j], A[j+1], ..., A[A.length - 1] is the third part.
 * All three parts have equal binary value.
 * If it is not possible, return [-1, -1].
 * <p>
 * Note that the entire part is used when considering what binary value it represents.  For example, [1,1,0] represents 6 in decimal, not 3.  Also, leading zeros are allowed, so [0,1,1] and [1,1] represent the same value.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: [1,0,1,0,1]
 * Output: [0,3]
 * Example 2:
 * <p>
 * Input: [1,1,0,1,1]
 * Output: [-1,-1]
 * <p>
 * <p>
 * Note:
 * <p>
 * 3 <= A.length <= 30000
 * A[i] == 0 or A[i] == 1
 */

//---------------------Observation------------------>>>

import java.util.Arrays;

/**
 * To split it into three parts, we need same amount of 1;
 * total 1's/3= T where T is the number of 1 in group
 * iterate over the array and count 1, when you find total T then you are done with first group
 * <p>
 * start the second group, count until 2T
 * similar until 3T
 */
public class ThreeEqualParts_927 {
    public static void main(String[] args) {
        int[] in = new int[]{1, 0, 1, 0, 1};
        threeEqualParts(in);

    }

    public static int[] threeEqualParts(int[] A) {
        int[] IMP = new int[]{-1, -1};
        int N = A.length;

        int S = 0;
        for (int x: A) S += x;
        if (S % 3 != 0) return IMP;
        int T = S / 3;
        if (T == 0)
            return new int[]{0, N - 1};

        int i1 = -1, j1 = -1, i2 = -1, j2 = -1, i3 = -1, j3 = -1;
        int su = 0;
        for (int i = 0; i < N; ++i) {
            if (A[i] == 1) {
                su += 1;
                if (su == 1) i1 = i;
                if (su == T) j1 = i;
                if (su == T+1) i2 = i;
                if (su == 2*T) j2 = i;
                if (su == 2*T + 1) i3 = i;
                if (su == 3*T) j3 = i;
            }
        }

        // The array is in the form W [i1, j1] X [i2, j2] Y [i3, j3] Z
        // where [i1, j1] is a block of 1s, etc.
        int[] part1 = Arrays.copyOfRange(A, i1, j1+1);
        int[] part2 = Arrays.copyOfRange(A, i2, j2+1);
        int[] part3 = Arrays.copyOfRange(A, i3, j3+1);

        if (!Arrays.equals(part1, part2)) return IMP;
        if (!Arrays.equals(part1, part3)) return IMP;

        // x, y, z: the number of zeros after part 1, 2, 3
        int x = i2 - j1 - 1;
        int y = i3 - j2 - 1;
        int z = A.length - j3 - 1;

        if (x < z || y < z) return IMP;
        return new int[]{j1+z, j2+z+1};
    }
}
