/**
 * Egg Dropping Problem.
 * Time Complexity: O(N*K^2)
 * N = Number of eggs
 * K = Number of floors
 *
 * @author Mayur Kulkarni <mayurkulkarni012@gmail.com>
 */
public class EggDropDP {
    private static void eggDrop(int floors, int eggs) {
        int[][] dp = new int[eggs + 1][floors + 1];

        // If we have one floor and >= 1 egg the answer is always 1
        for (int i = 1; i <= eggs; i++) {
            dp[i][1] = 1;
        }

        // If we have one egg and n floors, the answer is always n
        for (int i = 1; i <= floors; i++) {
            dp[1][i] = i;
        }
        for (int currEggs = 2; currEggs <= eggs; currEggs++) {
            for (int currFloor = 2; currFloor <= floors; currFloor++) {
                dp[currEggs][currFloor] = Integer.MAX_VALUE;
                for (int remainingFloor = 1; remainingFloor <= currFloor; remainingFloor++) {
                    int answer = 1 + Math.max(dp[currEggs - 1][remainingFloor - 1],
                            dp[currEggs][currFloor - remainingFloor]);
                    if (answer < dp[currEggs][currFloor]) {
                        dp[currEggs][currFloor] = answer;
                    }
                }
            }
        }

        for (int[] aDp : dp) {
            for (int i : aDp) System.out.print(i + " ");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        eggDrop(5, 5);
    }
}