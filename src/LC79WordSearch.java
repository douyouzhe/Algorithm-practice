/*
Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell,
where "adjacent" cells are those horizontally or vertically neighboring.
The same letter cell may not be used more than once.

For example,
Given board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]
word = "ABCCED", -> returns true,
word = "SEE", -> returns true,
word = "ABCB", -> returns false.
*/

public class LC79WordSearch {

    public boolean exist(char[][] board, String word) {
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[0].length; j++) {
                if (dfs(board,new boolean[board.length][board[0].length],
                        word,0,i,j)) return true;
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, boolean[][] visited, String word, int index, int i, int j){
        if(index==word.length()) return true;
        if(i<0||i>=board.length||
                j<0||j>=board[0].length||
                visited[i][j]==true||word.charAt(index)!=board[i][j]) return false;


        visited[i][j]=true;

        boolean res = dfs(board,visited,word,index+1,i+1,j)
                ||dfs(board,visited,word,index+1,i-1,j)
                ||dfs(board,visited,word,index+1,i,j+1)
                ||dfs(board,visited,word,index+1,i,j-1);

        visited[i][j]=false;
        return res;

    }
}
