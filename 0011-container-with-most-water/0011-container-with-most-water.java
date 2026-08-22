class Solution {
    public int maxArea(int[] height) {
        int left=0;
        int right=height.length-1;
        int maxCap=0;
        while(left<right){
            int width=right-left;
            int h=Math.min(height[left],height[right]);
            int area=width*h;
            maxCap=Math.max(maxCap,area);
            if(height[left]<height[right]){
                left++;
            }
            else{
                right--;
            }
        }
        return maxCap;
    }
}