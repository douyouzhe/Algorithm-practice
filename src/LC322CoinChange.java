/*
    You are given coins of different denominations and a total amount of money amount.
    Write a function to compute the fewest number of coins that you need to make up that
    amount. If that amount of money cannot be made up by any combination of the coins,
    return -1.

    Example 1:
    coins = [1, 2, 5], amount = 11
    return 3 (11 = 5 + 5 + 1)

    Example 2:
    coins = [2], amount = 3
    return -1.

    Note:
    You may assume that you have an infinite number of each kind of coin.
 */
//brutal force dfs
class Solution {
    public int coinChange(int[] coins, int amount) {
        return helper(coins,amount,0);
    }

    private int helper(int[] coins, int amount, int coinIndex){
        if(amount==0) return 0;
        if(coinIndex>=coins.length||amount<0) return-1;

        int maxCoin = amount/coins[coinIndex];
        int min = Integer.MAX_VALUE;

        for(int i = 0; i<=maxCoin; i++){
            int res = helper(coins, amount-i*coins[coinIndex],coinIndex+1);
            if(res!=-1) min = Math.min(min, res+i);
        }
        return min==Integer.MAX_VALUE? -1:min;
    }
}


//DP top-down
class Solution {
    public int coinChange(int[] coins, int amount) {
        if(amount<=0) return 0;
        return helper(coins, amount, new int[amount]);
    }

    private int helper(int[] coins, int amount, int[] ans){
        if(amount<0) return -1;
        if(amount==0) return 0;

        if(ans[amount-1]!=0) return ans[amount-1];

        int min = Integer.MAX_VALUE;
        for(int coin:coins){
            int res = helper(coins,amount-coin,ans);
            if(res!=-1) min = Math.min(res,min);
        }
        ans[amount-1] = min==Integer.MAX_VALUE? -1:min+1;
        return ans[amount-1];
    }
}


//DP bottom-up
class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        // cant use Integer.MAX_VALUE
        //+1会overflow 变成最小的integer
        Arrays.fill(dp, amount+1);
        dp[0] = 0;

        for(int i = 1; i<=amount ; i++){
            for(int coin:coins){
                if(coin<=i){
                    dp[i] = Math.min(dp[i],dp[i-coin]+1);
                }
            }
        }
        return dp[amount]==amount+1? -1:dp[amount];
    }
}
