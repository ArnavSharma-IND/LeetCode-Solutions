class Solution {
    public void rotate(int[] nums, int k) {
        if(nums.length<=1){
            return;
        }
        int n = nums.length;
         k=k%n;
        int temp[]=new int[k];
        for(int i=n-k;i<n;i++){
            temp[i-(n-k)]=nums[i];
        }
        for(int p=n-k-1;p>=0;p--){
            nums[p+k]=nums[p];

        }
        for(int j=0;j<k;j++){
            nums[j]=temp[j];
        }  
        return;
    }
}