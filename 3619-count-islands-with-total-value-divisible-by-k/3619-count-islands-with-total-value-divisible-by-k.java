class Solution {
    int ans=0;
    public int countIslands(int[][] grid, int k) {
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]>0){
                    long val = solve(grid,i,j);
                    if(val%k==0)ans++;
                }
            }
        }
        return ans;
    }

    private long solve(int[][] grid, int i,int j){
        if(i<0 || j<0 || i==grid.length || j==grid[0].length){
            return 0;
        }
        long sum=grid[i][j];
        grid[i][j]=-1;
        if(i>0 && grid[i-1][j]>0){
            sum+=solve(grid,i-1,j);
        }
        if(j>0 && grid[i][j-1]>0){
            sum+=solve(grid,i,j-1);
        }
        if(i<grid.length-1 && grid[i+1][j]>0){
            sum+=solve(grid,i+1,j);
        }
        if(j<grid[0].length-1 && grid[i][j+1]>0){
            sum+=solve(grid,i,j+1);
        }
        return sum;
    }
}