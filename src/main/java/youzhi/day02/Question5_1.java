package youzhi.day02;

/**
 * @author youzhi
 * @date 2021/5/7 21:48
 */
public class Question5_1 {
    public static void main(String[] args) {
        System.out.println(getLongestString("bb"));
    }

    /**
     * 获取最长的回文串
     * 定义：
     * boolean[][] dp:定义的数组 dp[i][j] 表示 [i,j]字符串是否为回文串
     * char[] charList 字符串的分解数组
     *
     *
     * @param s
     * @return
     */
    public static String getLongestString(String s){
        //长度为1的字符串为回文串
        if(s.length() < 2){
            return s;
        }

        //定义变量
        int length = s.length();
        boolean[][] dp = new boolean[length][length];
        int begin = 0;
        int maxLen = 1;
        char[] charArray = s.toCharArray();

        //初始化数组dp
        for(int i=0;i<dp.length;i++){
            for(int j=0;j<dp[i].length;j++){
                if(i == j){
                    dp[i][j] = true;
                }else{
                    dp[i][j] = false;
                }
            }
        }

        int L = 2;
        for(int i=L;i<=length;i++){
            for(int j=0;j<length;j++){
                //判断是否越界
                if(j + i > length){
                    break;
                }
                int iIndex = j;
                int jIndex = j + i - 1;
                if(charArray[iIndex] != charArray[jIndex]){
                    dp[iIndex][jIndex] = false;
                }else{
                    if(jIndex - iIndex >= 2){
                        dp[iIndex][jIndex] = dp[iIndex+1][jIndex-1];
                    }else{
                        dp[iIndex][jIndex] = true;
                    }
                }
                if(dp[iIndex][jIndex] && jIndex - iIndex + 1 > maxLen){
                    maxLen = jIndex - iIndex + 1;
                    begin = iIndex;
                }

            }
        }
        return s.substring(begin, begin + maxLen);
    }
















}
