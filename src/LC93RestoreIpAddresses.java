/*
    Given a string containing only digits, restore it by returning all possible valid IP address
    combinations.
    For example:
    Given "25525511135",
    return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
*/

class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        helper(s,0,new StringBuilder(), res, 0);
        return res;
    }

    public void helper(String s, int index, StringBuilder sb, List<String> res, int n){
        if(index==s.length() && n==4){
            res.add(sb.toString());
            return;
        }
        if(index==s.length()||n>4) return;

        for(int k=1;k<=3;k++){
            if (index+k > s.length()) break;
            String tmp = s.substring(index,index+k);
            int val = Integer.parseInt(tmp);
            if(val>255 || (tmp.indexOf("0")==0 && tmp.length()>1)) continue;
            //in the case 010
            int len = sb.length();
            if(n!=0){
                sb.append(".");
            }
            sb.append(val);
            helper(s,index+k,sb,res,n+1);
            sb.setLength(len);
        }
    }
}
