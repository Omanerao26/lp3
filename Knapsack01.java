public class Knapsack01 {

    public static int knapsack(int wt[], int val[], int W, int n, int dp[][]) {
        if (n == 0 || W == 0) {
            return 0;
        }

        if (dp[n][W] != -1) {
            return dp[n][W];
        }

        if (wt[n - 1] <= W) { // valid to include
            int ans1 = val[n - 1] + knapsack(wt, val, W - wt[n - 1], n - 1, dp); // include
            int ans2 = knapsack(wt, val, W, n - 1, dp); // exclude
            dp[n][W] = Math.max(ans1, ans2);
            return dp[n][W];
        } else { // not valid to include
            dp[n][W] = knapsack(wt, val, W, n - 1, dp);
            return dp[n][W];
        }
    }

    public static void print(int dp[][]){
        for(int i=0; i<dp.length; i++){
            for(int j =0; j<dp[0].length; j++){
                System.out.print(dp[i][j]+ " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int knapsackTab(int val[], int wt[], int W) {
        int n = val.length;
        int dp[][] = new int[n + 1][W + 1];

        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 0;
        }
        for (int j = 0; j < dp[0].length; j++) {
            dp[0][j] = 0;
        }
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < W + 1; j++) {
                int v = val[i - 1];
                int w = wt[i - 1];
                if (w <= j) {
                    int incProfit = v + dp[i - 1][j - w];
                    int excProfit = dp[i - 1][j];
                    dp[i][j] = Math.max(incProfit, excProfit);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        print(dp);
        return dp[n][W];
    }

    public static void main(String[] args) {
        int val[] = { 15, 14, 10, 45, 30 };
        int wt[] = { 2, 5, 1, 3, 4 };
        int W = 7;

        int dp[][] = new int[val.length + 1][W + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }

        // System.out.println(knapsack(wt, val, W, val.length, dp));

        System.out.println(knapsackTab(val, wt, W));
    }
}
