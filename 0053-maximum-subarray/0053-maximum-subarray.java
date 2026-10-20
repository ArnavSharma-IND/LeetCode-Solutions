class Solution {
    public int maxSubArray(int[] nums) {
        if(nums.length==0){
            return 0;
        }
        int max=nums[0];
        int ans=0;
        for(int i=0;i<nums.length;i++){
            if(ans<0){
                ans=0;
            }
            ans+=nums[i];
            if(ans>max){
                max=ans;
            }
        }
        return max;
    }
}