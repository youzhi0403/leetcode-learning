package youzhi.day01;

/**
 * @author youzhi
 * @date 2021/4/13 10:51
 */
public class Question4_3 {
    public static void main(String[] args) {
        /**
         * 中位数的作用是什么：将一个集合划分为两个长度相等的子集，其中一个子集的元素总是大于另一个子集的元素
         * 定义A集合，B集合
         * 定义i，将A集合分为两个部分：{A[0],...,A[i-1]},{A[i]....,A[m-1]} m=A.length
         * 定义j，将B集合分为两个部分，{B[0],...,B[j-1]},{B[j]...,B[n-1]} n=B.length
         *  将A的左部分和B的左部分放到一个集合left_part， 把A的右部分和B的右部分放到一个集合right_part
         *
         *  当A和B的总长度是偶数，如果可以满足以下两个条件：
         *      len(left_part) = len(right_part)
         *      max(left_part) <= min(right_part)
         *  那么中位数就是：median = (max(left_part) + min(right_part))/2
         *
         *  当A和B的总长度是奇数，如果可以满足以下两个条件
         *      len(left_part) = len(right_part) + 1
         *      max(left_part) <= min(right_part)
         *   那么中位数就是 max(left_part)
         *
         *   要确保 偶数情况 和 奇数情况 的两个条件，只需要保证：
         *   当m+n为偶数时，i+j = m - i + n - j         ===========> i+j = (m + n)/2
         *   当m+n为奇数时，i+j = m - i + n - j + 1     ===========> i+j = (m + n + 1)/2
         *   （1）从程序的角度可以合并为 i + j = (m + n + 1)/2
         *
         *   （2）规定A的长度小于等于B，如果A大于B，则交换两个数组
         *
         *   （3）B[j-1] <= A[i] 并且 A[i-1] <= B[j]
         *
         *   分析临界规则
         *   假设A[i-1] A[i] B[j-1] B[j] 总是存在，
         *   对于i=0,i=m,j=0,j=n 这样的临界条件，只需要规定A[-1] = B[-1] = 负无穷大  A[m] = B[n] = 正无穷大
         *   如果一个数组不出现前一部分时，对应的值为负无穷，就不会对前一部分的最大值产生影响
         *   如果一个数组不出现后一部分时，对应的值为正无穷，就不会对后一部分的最小值产生影响
         *
         *   所以我们需要做的是：
         *   在[0,m]中找到i,使得 B[j-1] <= A[i] 且 A[i-1] <= B[j] ,其中j = (m+n+1)/2 - i
         *   他等价于：
         *   A[i-1] <= b[j] 其中 j = (m+n+1)/2 - i
         *   因为 一定存在一个最大的i满足A[i-1] <= B[j]
         *   i+1肯定不满足 A[i-1] <= b[j]，也就是A[i] > B[j-1]
         *
         *   ========================
         *   2021-4-19
         *    当 m + n 为偶数或者为奇数， i+j = (m + n + 1)/2
         *    当 m + n = 1    1
         *    当 m + n = 2    1
         *               3    2
         *               4    2
         *
         *    i=0  A[i-1] = A[-1] = 负无穷大
         *    i=m  A[i] = A[m] = 正无穷大
         *    j=0  B[j-1] = B[-1] = 负无穷大
         *    j=n  B[j] = B[n] = 正无穷大
         */

//        int[] nums1 = new int[]{4};
//        int[] nums2 = new int[]{1,2,3,5,6};

//        int[] nums1 = new int[]{};
//        int[] nums2 = new int[]{1};

//        int[] nums1 = new int[]{1};
//        int[] nums2 = new int[]{1};

        int[] nums1 = new int[]{1,1,1};
        int[] nums2 = new int[]{1,1,1};

//        int[] nums1 = new int[]{2};
//        int[] nums2 = new int[]{};
        System.out.println(findMedianSortedArrays2(nums1,nums2));

    }

    /**
     * (1)数学论证：
     *
     * 将一个集合划分为两个长度相等的子集，其中一个子集的元素总是大于另一个子集的元素
     * 定义A集合，B集合
     * 定义i，将A集合分为两个部分：{A[0],...,A[i-1]},{A[i]....,A[m-1]} m=A.length
     * 定义j，将B集合分为两个部分，{B[0],...,B[j-1]},{B[j]...,B[n-1]} n=B.length
     * 将A的左部分和B的左部分放到一个集合left_part， 把A的右部分和B的右部分放到一个集合right_part
     *
     *   当A和B的总长度是偶数，如果可以满足以下两个条件：
     *      len(left_part) = len(right_part)
     *      max(left_part) <= min(right_part)
     *   那么中位数就是：median = (max(left_part) + min(right_part))/2
     *
     *   当A和B的总长度是奇数，如果可以满足以下两个条件
     *      len(left_part) = len(right_part) + 1
     *      max(left_part) <= min(right_part)
     *   那么中位数就是 max(left_part)
     *
     *   要确保【奇数场景】【偶数场景】这两种情况，要保证如下条件：
     *   <一> i + j = m - i + n - j(偶数场景) 或者是 i + j = m - i + n - j + 1（奇数场景）。
     *   可以得到 i + j = (m + n)/2（偶数场景）或者是 i + j = (m + n + 1)/2 (奇数场景)
     *   这里的分数结果只保留整数部分，可以将两种情况合并为： i + j = (m + n + 1)/2
     *   <二> 规定A的长度小于等于B的长度，即m<=n。(为了避免j为负数)
     *   那么j = (m + n + 1)/2 - i
     *   如果A的长度大于B,那么交换A和B即可
     *   <三> A[i-1] <= B[j] 并且 B[j-1] <= A[i]
     *
     *   对于临界条件的处理：
     *   假设A[i-1],B[j-1],A[i],B[i]总是存在的。对于i=0，i=m,j=0,j=n这些临界情况，我们只需要规定
     *   A[-1] = B[-1] = 负无穷大，A[m] = B[n] = 正无穷大。
     *   当一个数组不出现前一部分时，对应的值为负无穷，就不会对前一部分的最大值产生影响
     *   当一个数组不出现后一部分时，对应的值为正无穷，就不会对后一部分的最小值产生影响
     *
     *   可以将<三>简化为：在[0,m]种找到最大的i，使得：A[i-1] <= B[j]，其中j = (m+n+1)/2 - i
     *   证明过程：(1)当i从0~m递增时，A[i-1]递增，B[j]递减，所以一定存在一个最大的i满足A[i-1] <= B[j]
     *   (2)如果i是最大的，那么说明i+1不满足。将i+1带入可以得到A[i] > B[j-1]，也就是B[j-1]<A[i]。
     *
     *   因此我们可以对i在[0,m]的区间进行二分搜索，找到最大的满足A[i-1] <= B[j]的i值。
     * 程序思路：
     * <一>定义变量：
     * m: 数组A的长度
     * n: 数组B的长度
     * median1: 左部分数组的最大值
     * median2: 右部分数组的最小值
     * left: 在对[0,m]的区间进行二分搜索的左坐标，初始值为0
     * right: 在对[0,m]的区间进行二分搜索的右坐标，初始值为m
     * nums1is1: nums1[i-1]
     * nums1i: nums1[i]
     * nums2js1: nums2[j-1]
     * nums2j: nums2[j]
     * <2>程序逻辑详情如下代码
     *
     *
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays2(int[] nums1, int[] nums2){
        //需要保证 nums1的长度比nums2的长度小,如果nums1.length > nums2.length,则对调两个数组
        if(nums1.length > nums2.length){
            return findMedianSortedArrays2(nums2, nums1);
        }
        //定义
        //数组的长度 m, n
        //前一部分的最大值 median1
        //后一部分的最小值 median2
        //二分查找开始的左坐标 left
        //二分查找开始的右坐标 right
        //nums1[i-1],nums1[i],nums2[j-1],nums2[j]
        int m = nums1.length;
        int n = nums2.length;
        int median1 = 0;
        int median2 = 0;
        int left = 0;
        int right = m;

        int nums1is1 = 0;
        int nums1i = 0;
        int nums2js1 = 0;
        int nums2j = 0;

        while (left <= right){
            int i = (left + right) / 2;
            int j = (m + n + 1) / 2 - i;
            if(i == 0){
                nums1is1 = Integer.MIN_VALUE;
            }else{

                nums1is1 = nums1[i-1];
            }

            if(i == m){
                nums1i = Integer.MAX_VALUE;
            }else{
                nums1i = nums1[i];
            }

            if(j == 0){
                nums2js1 = Integer.MIN_VALUE;
            }else{
                nums2js1 = nums2[j-1];
            }

            if(j == n){
                nums2j = Integer.MAX_VALUE;
            }else{
                nums2j = nums2[j];
            }

            //nums1is < nums2j 或者是 nums1is <= nums2j 都是可以的，不影响median1，median2的值改变
            if(nums1is1 < nums2j){
                median1 = Math.max(nums1is1, nums2js1);
                median2 = Math.min(nums1i, nums2j);
                left = i + 1;
            }else{
                right = i - 1;
            }
        }
        int len = m + n;
        if(len % 2 == 0){
            return (median1 + median2) / 2.0;
        }else{
            return median1;
        }

    }

}
