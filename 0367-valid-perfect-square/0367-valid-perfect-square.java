class Solution {
    public boolean isPerfectSquare(int num) {
        if(num<=0){
            return false;
        }
        if(num==1){
            return true;
        }
        int left=1;
        int right=num;
        while(left<=right){
            int mid=left+(right-left)/2;
            long s=(long)mid*mid;
            if(s==num){
                return true;
            }
            else if(s<num){
                left=mid+1;
            }
            else{
                right=mid-1;
            }
        }
        return false;
    }
}