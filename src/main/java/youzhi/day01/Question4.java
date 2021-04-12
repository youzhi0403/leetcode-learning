package youzhi.day01;

/**
 * @author youzhi
 * @date 2021/4/6 23:07
 */
public class Question4 {
    public static void main(String[] args) {
        // 4. 寻找两个正序数组的中位数
        // 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。

        int[] num1 = new int[]{1,3};
        int[] num2 = new int[]{2};

        System.out.println(findNumber(num1,num2));

    }

    /**
     * 时间复杂度为O(n+m),空间复杂度为O(n+m)
     * @param num1
     * @param num2
     * @return
     */
    public static double findNumber(int[] num1, int[] num2){
        int[] result = new int[num1.length + num2.length];

        //num1光标
        int index1 = 0;
        //num2光标
        int index2 = 0;
        //result光标
        int resultIndex = 0;
        while (index1 < num1.length && index2 < num2.length){
            if(num1[index1] <= num2[index2]){
                result[resultIndex] = num1[index1];
                index1++;
            }else{
                result[resultIndex] = num2[index2];
                index2++;
            }
            resultIndex++;
        }

        if(index1 == num1.length){
            //将num2剩余数赋值给数组
            while (index2 < num2.length){
                result[resultIndex] = num2[index2];
                index2++;
                resultIndex++;
            }
        }else{
            //将num1剩余数赋值给数组
            while (index1 < num1.length){
                result[resultIndex] = num1[index1];
                index1++;
                resultIndex++;
            }
        }
        double k;
        //获取中位数
        if(result.length%2 == 1){
            //奇数
            k = result[result.length/2];
        }else{
            //偶数
            k = (result[result.length/2 - 1] + result[result.length/2])/2.0;
        }
        return k;
    }
}
