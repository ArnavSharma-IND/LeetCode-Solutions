class Solution {
    public int[] getConcatenation(int[] nums) {
        int n=nums.length;
        int[] ans=new int[2*n];
        for(int i=0;i<n;i++){
            ans[i]=nums[i];
            int j=i+n;
            if(j<=2*n-1){
                ans[j]=nums[i];
            }
        } 
        return ans;
    }
}