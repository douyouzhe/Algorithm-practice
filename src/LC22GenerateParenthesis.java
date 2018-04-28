/*
    Given n pairs of parentheses, write a function to generate all combinations
    of well-formed parentheses.
    For example, given n = 3, a solution set is:
    [
      "((()))",
      "(()())",
      "(())()",
      "()(())",
      "()()()"
    ]
*/


class Solution1 {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        helper(n,0,0,res,new StringBuilder());
        return res;
    }

    private void helper(int n, int left, int right, List<String> res, StringBuilder sb){
        if(left==n && right == n){
            res.add(sb.toString());
            return;
        }

        if(left<n){
            sb.append("(");
            helper(n,left+1,right,res,sb);
            sb.deleteCharAt(sb.length()-1);
        }
        if(right<left){
            sb.append(")");
            helper(n,left,right+1,res,sb);
            sb.deleteCharAt(sb.length()-1);
        }
    }
}


class Solution2 {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        helper(n,0,0,res,"");
        return res;
    }

    private void helper(int n, int left, int right, List<String> res, String tmp){
        if(left==n && right == n){
            res.add(new String(tmp));
            return;
        }

        if(left<n) helper(n,left+1,right,res,tmp+"(");
        if(right<left) helper(n,left,right+1,res,tmp+")");
    }
}