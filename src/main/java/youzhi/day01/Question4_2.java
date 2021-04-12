package youzhi.day01;

/**
 * @author youzhi
 * @date 2021/4/10 17:53
 */
public class Question4_2 {
    public static void main(String[] args) {
        /**
         * 思路：
         * 1.编写一个方法，获取两个数组中的第K小的数 - getKthElement
         * 2.实现这个方法采用二分法，达到时间复杂度的要求为O(log2(m+n))
         * 具体实现思路为：
         *   (1)定义：
         *     <1>K为第两个数组中第K小的数
         *     <2>定义两个数组为A,B
         *     <3>因为采用二分法，所以每次从【k/2-1】开始排除
         *   (2)算法开始：
         *     <1>比较A[k/2-1]和B[k/2-1]
         *        <1-1>如果A[k/2-1] < B[k/2-1]，排除A[0]到A[k/2-1]的数。
         *          (PS：如果A[k/2-1] < B[k/2-1]，
         *          则比A[k/2-1]小的数最多只有A的前k/2-1个数和B的前k/2-1个数,
         *          即比A[k/2-1]小的数最多只有k-2个,因此A[k/2-1]不可能是第K个数，
         *          A[0]到A[k/2-1]也不可能是第K个数，
         *          可以全部排除)
         *        <1-2>如果A[k/2-1] > B[k/2-1]，排除B[0]到B[k/2-1]的数。
         *        <1-3>如果A[k/2-1] = B[k/2-1]，排除A[0]到A[k/2-1]的数。
         *     <2>根据排除的个数，减少k的值
         *     <3>
         *     当k=1时，比较两个有序数组未排除下标范围的第一个数，较小的则为第k个数
         *     当A数组被完全排除时，则在B数组中获取第k个数
         *     当B数组被完全排除时，则在A数组中获取第k个数
         *
         * 时间复杂度的分析：
         *     每次排除掉k/2-1个数，时间复杂度为O(log2(m+n))
         *
         */
        int[] nums1 = new int[]{4};
        int[] nums2 = new int[]{1,2,3,5,6};
        System.out.println(findMedianSortedArrays2(nums1,nums2));
    }

    public static double findMedianSortedArrays2(int[] nums1, int[] nums2){
        int length1 = nums1.length;
        int length2 = nums2.length;
        int totalLength = length1 + length2;
        if(totalLength %2 == 1){
            //奇数
            int k = totalLength/2 + 1;
            return getKthElement3(nums1, nums2, k);
        }else{
            //偶数
            int k1 = totalLength/2;
            int k2 = totalLength/2 + 1;
            double result = (getKthElement3(nums1, nums2, k1) + getKthElement3(nums1, nums2, k2))/2.0;
            return result;
        }
    }

    public static double getKthElement3(int[] nums1, int[] nums2, int k){
        int index1 = 0; //nums1数组的起始坐标
        int index2 = 0; //nums2数组的结束坐标
        int length1 = nums1.length; //nums1数组的长度
        int length2 = nums2.length; //nums2数组的长度

        while (true){
            if(index1 == nums1.length){
                //如果nums1数组已经排除完
                return nums2[index2 + k - 1];
            }else if(index2 == nums2.length){
                //如果nums2数组已经排除完
                return nums1[index1 + k - 1];
            }else if(k == 1){
                return Math.min(nums1[index1],nums2[index2]);
            }

            int half = k/2 - 1;
            int newIndex1 = Math.min(length1 - 1, index1 + half);
            int newIndex2 = Math.min(length2 - 1, index2 + half);
            int value1 = nums1[newIndex1];
            int value2 = nums2[newIndex2];
            if(value1 <= value2){
                int quantity = newIndex1 - index1 + 1; //要排除的数量
                k = k - quantity;
                index1 = newIndex1 + 1;
            }else{
                int quantity = newIndex2 - index2 + 1; //要排除的数量
                k = k - quantity;
                index2 = newIndex2 + 1;
            }

        }
    }
}
