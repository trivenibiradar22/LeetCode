class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(' || (m + n - 1) % 2 != 0) {
            return false;
        }
        
        int maxOpen = (m + n - 1) / 2;
        boolean[][][] visited = new boolean[m][n][maxOpen + 1];
        return dfs(grid, 0, 0, 0, maxOpen, visited);
    }
    
    private boolean dfs(char[][] grid, int r, int c, int balance, int maxOpen, boolean[][][] visited) {
        int m = grid.length;
        int n = grid[0].length;
        
        if (grid[r][c] == '(') {
            balance++;
        } else {
            balance--;
        }
        
        if (balance < 0 || balance > maxOpen || visited[r][c][balance]) {
            return false;
        }
        
        if (r == m - 1 && c == n - 1) {
            return balance == 0;
        }
        
        visited[r][c][balance] = true;
        
        if (r + 1 < m && dfs(grid, r + 1, c, balance, maxOpen, visited)) {
            return true;
        }
        
        if (c + 1 < n && dfs(grid, r, c + 1, balance, maxOpen, visited)) {
            return true;
        }
        
        return false;
    }
}