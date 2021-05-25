package youzhi.day02;

/**
 * @author youzhi
 * @date 2021/4/22 22:19
 */
public class Question5 {

    public static void main(String[] args) {
        /**
         * P(i,j)表示字符串s的第i个到第j个字母组成的串。
         *
         * P(i,j) = true 如果Si....Sj是回文串
         * P(i,j) = false 其他情况
         *
         * 其他情况包括两种可能：
         * (1)s[i,j]本身不是一个回文串
         * (2)i>j,此时s[i,j]本身不合法
         *
         * 动态规划的状态转移方程：P(i,j) = P(i+1,j-1) 并且 Si == Sj
         *
         * 需要考虑动态规划的边界条件，即子串的长度为1或2。
         * 对于长度为1的子串，显然是个回文串。
         * 对于长度为2的子串，只要两个字母相同，就是回文串
         *
         * 动态规划的边界条件为：
         * P(i,i) = true
         * P(i,i+1) = （Si == Si+1）
         *
         * 答案为P(i,j) = true 中 j-i+1 的最大值
         *
         * 在状态转移方程中，是从长度短的字符串向长度长的字符串转移的。
         *
         */
        System.out.println(longestPalindrome("1121"));

    }

    public static String longestPalindrome(String s){
        int len = s.length();
        if(len < 2){
            return s;
        }

        int maxLen = 1;
        int begin = 0;

        // dp[i][j]表示s[i,...,j]是否是回文串
        boolean[][] dp = new boolean[len][len];

        //初始化 所有长度为1的子串都是回文串
        for(int i=0;i<len;i++){
            dp[i][i] = true;
        }

        char[] charArray = s.toCharArray();
        //递推开始
        //先枚举子串长度
        //定义L为子串长度，i为左边界，右边界为 i+L-1
        for(int L = 2;L <= len;L ++){
            for(int i=0;i<len;i++){
                //确定右边界
                int j = L + i - 1;
                //如果右边界越界，则退出循环
                if(j >= len){
                    break;
                }

                //如果值不相等
                if(charArray[i] != charArray[j]){
                    dp[i][j] = false;
                }else{
                    if(j - i < 3){
                        dp[i][j] = true;
                    }else{
                        dp[i][j] = dp[i+1][j-1];
                    }
                }

                //只要dp[i][L] = true成立，就表示s[i,....,L]是回文，此时记录回文长度和起始位置
                if(dp[i][j] && j - i + 1 > maxLen){
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        printArray(dp);
        return s.substring(begin, begin + maxLen);
    }

    /**
     * 初始化后的数组：
     * true false false false
     * false true false false
     * false false true false
     * false false false true
     *
     * 第一轮循环：

     * @param dp
     */
    public static void printArray(boolean[][] dp){
        for(int i=0;i<dp.length;i++){
            boolean[] booleanArray = dp[i];
            for(int j=0;j<booleanArray.length;j++){
                System.out.print(booleanArray[j] + " ");
            }
            System.out.println();
        }
    }


}
