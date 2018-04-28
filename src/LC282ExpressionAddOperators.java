/*
    Given a string that contains only digits 0-9 and a target value,
    return all possibilities to add binary operators (not unary) +, -, or * between
    the digits so they evaluate to the target value.

    Examples:
    "123", 6 -> ["1+2+3", "1*2*3"]
    "232", 8 -> ["2*3+2", "2+3*2"]
    "105", 5 -> ["1*0+5","10-5"]
    "00", 0 -> ["0+0", "0-0", "0*0"]
    "3456237490", 9191 -> []
*/

public class LC282ExpressionAddOperators {

    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<String>();
        if(num == null || num.length() == 0) return res;
        dfs(res,num,0,target,new StringBuilder(),0,0);
        return res;
    }

    public void dfs(List<String> res, String num, int index, int target, StringBuilder path,
                    long curSum, long lastVal){
        // success base case
        if(index == num.length() && target == curSum){
            res.add(path.toString());
            return;
        }

        //four cases here:
        //1 accumulate number
        //2 +
        //3 -
        //4 *

        for(int i = index; i<num.length();i++){
            //exclude numbers with leading zeros
            if(i != index && num.charAt(index) == '0') break;
            long val = Long.parseLong(num.substring(index, i + 1));
            int oriPathLen = path.length();
            if(index==0){
                path.append(val);
                dfs(res,num,i+1,target,path,val,val);
                path.setLength(oriPathLen);
            }
            else{
                //+
                path.append("+").append(val);
                dfs(res,num,i+1,target,path,curSum+val,val);
                path.setLength(oriPathLen);

                //-
                path.append("-").append(val);
                dfs(res,num,i+1,target,path,curSum-val,-val);
                path.setLength(oriPathLen);

                //*
                path.append("*").append(val);
                dfs(res,num,i+1,target,path,curSum-lastVal+val*lastVal,val*lastVal);
                path.setLength(oriPathLen);
            }
        }
    }
}
