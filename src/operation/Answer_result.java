package operation;
import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

import jdk.internal.dynalink.beans.StaticClass;
import jdk.internal.org.objectweb.asm.tree.analysis.Value;
import sun.reflect.generics.tree.Tree;
import operation.Operate;

import java.util.Stack;
import java.util.regex.Pattern;

/**
 * Answer_result.main
 * @author Lenovo
 * input exp like ( 1 + 1 ) * 5
 * output result(Answer.result.main(exp,)
 */
public class Answer_result{
    //public Node root;
    public static String answer(String exp) {//传入题目，解出答案
        // TODO Auto-generated method stub
        //生成resultNum答案
        String[] exc=TreeNode.toStringArrayTrimOutParrenthes(exp);
        Num resultNum=new TreeNode(exc).calculate();
        Operate.simplify(resultNum);
        return Operate.fraction(resultNum);
    }

    public static String toPostifixExp(TreeNode node)//转化为后缀表达式
    {
        String s=new String("");
        if(node==null) return null;
        if(node.left!=null)
        s=s + " " + toPostifixExp(node.left);
        if(node.right!=null)
        s=s + " " + toPostifixExp(node.right);
        if(node.isSymbol)
            return String.valueOf(node.symbol);
        else
            return Operate.fraction(node.data);
    }

    public static String getDupExpression(String postfix)//后缀表达式转化为查重表达式
    {
        String dup=new String();
        String[] s=postfix.split(" ");
        Pattern p = Pattern.compile("^-?\\d+$");
        Stack stack = new Stack();
        for(int i=0;i<s.length;i++)
        {
            if(p.matcher(s[i]).matches())//如果是数字
            {
                stack.push(s[i]);
                dup=dup+" ";
            }
            else//如果是符号
            {
                String result= "#",num1,num2;
                dup=dup+s[i];
                dup=dup+" ";
                num1=stack.pop().toString();
                num2=stack.pop().toString();

                if(num1 != "#") {
                dup=dup+num1;
                dup=dup+" ";
                }else{
                }

                if(num2 != "#") {
                  dup=dup+num2;
                  dup=dup+" ";
                }else{
                }
                stack.push(result);
            }
        }
        return dup;
    }



}






