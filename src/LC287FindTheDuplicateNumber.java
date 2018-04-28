/*
Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive),
prove that at least one duplicate number must exist. Assume that there is only one duplicate number,
find the duplicate one.

Note:
You must not modify the array (assume the array is read only).
You must use only constant, O(1) extra space.
Your runtime complexity should be less than O(n2).
There is only one duplicate number in the array, but it could be repeated more than o

 */

public class LC287FindTheDuplicateNumber {

    public static void main(String[] args) {
        int[] arr = {8,1,4,2,3,5,6,7,9,8};
        System.out.println(findDuplicate1(arr));
    }

    /*
    Solution1:
    Use two pointers the fast and the slow. The fast one goes forward two steps each time, while the slow one
    goes only step each time. They must meet the same item when slow==fast. In fact, they meet in a circle, the
    duplicate number must be the entry point of the circle when visiting the array from nums[0]. Next we just need
    to find the entry point. We use a point(we can use the fast one before) to visit form begining with one step each
    time, do the same job to slow. When fast==slow, they meet at the entry point of the circle.

    nums: 1 2 3 4
    index:0 1 2 3

    0-1-2-3-4

    nums: 1 2 3 4 3
    index:0 1 2 3 4

    0-1-2-3-4-3-4-3-4....

     */

    public static int findDuplicate1(int[] nums) {
        if(nums==null||nums.length<2) {
            return -1;
        }
        int slow = nums[0];
        int fast = nums[nums[0]];
        while(slow!=fast){
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        fast = 0;
        while(fast!=slow){
            fast = nums[fast];
            slow = nums[slow];
        }
        return slow;
    }

    /*
    Solution2: pigeon hole problem
    T:O(nlogn)
     */

    public static int findDuplicate2(int[] nums) {
        if(nums==null||nums.length<2) {
            return -1;
        }
        int left = 0;
        int right = nums.length-1;
        int mid;

        while(left+1<right){
            mid = left+(right-left)/2;
            int count = countSmaller(nums,mid);
            if(count>mid){
                right = mid;
            }
            else{
                left= mid;
            }
        }
        return countSmaller(nums,left)<=left? right:left;
    }

    private static int countSmaller(int[] nums, int target){
        int count = 0;
        for(int i:nums){
            if(i<=target){
                count++;
            }
        }
        return count;
    }
}
