package youzhi.day02;

import java.util.Objects;

/**
 * @author youzhi
 * @date 2021/5/3 22:12
 */
public class Question5_2 {
    public static void main(String[] args) {
        System.out.println(longestPalindrome("babad"));
    }

    public static String longestPalindrome(String s) {
        if(s == null || s.length() <= 0){
            return s;
        }

        int begin = 0;
        int length = 1;

        for(int i=0;i<s.length();i++){
            int len2 = expandAroundCenter(s, i, i);
            int len1 = expandAroundCenter(s, i, i+1);
            int len = Math.max(len1, len2);
            if(len > length){
                begin = i - (len - 1)/2;
                length = len;
            }
        }

        return s.substring(begin, begin + length);
    }

    public static int expandAroundCenter(String s, int left, int right){
        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            left = left - 1;
            right = right + 1;
        }
        return right - left - 1;
    }
}
