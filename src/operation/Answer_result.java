package operation;
import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

import jdk.internal.dynalink.beans.StaticClass;
import jdk.internal.org.objectweb.asm.tree.analysis.Value;
import sun.reflect.generics.tree.Tree;
import operation.Operate;

import java.util.List;
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
        String s=new String();
        if(node==null) return null;
        if(node.left!=null)
        s=s + toPostifixExp(node.left);
        if(node.right!=null)
        s=s + toPostifixExp(node.right);
        if(node.isSymbol)
            return s+node.symbol+" ";
        else
            return Operate.fraction(node.data)+" ";
    }

        public static String getDupExpression(String postfix)//后缀表达式转化为查重表达式
    {
        String dup=new String();//
        String[] s=postfix.split(" ");//以空格分割后缀表达式并返回字符数组
        Pattern p = Pattern.compile("^-?\\d+$");//定的正则表达式编译为模式
        Stack stack = new Stack();//新建存储栈
        for(int i=0;i<s.length;i++)//字符从左到右遍历
        {
            if(p.matcher(s[i]).matches())//如果是数字
            {
                stack.push(s[i]);//出栈
            }
            else//如果是符号
            {
                String result= "#",num1,num2;
                dup=dup+s[i];
                dup=dup+" ";//空格用于后续分割
                num1=stack.pop().toString();//入栈
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

    public static boolean IsRepeat(List<String> exercises, TreeNode tree)//列表里的表达式是否与新的表达式是否重复
    {

        String exercise=Answer_result.toPostifixExp(tree);
        exercise=Answer_result.getDupExpression(exercise);
        for(int i=0;i< exercises.size();i++)
        {
            if(dupCheck(exercises.get(i),exercise))
                return true;
        }
        return false;
    }
    public static boolean dupCheck(String str1,String str2) {//对比两个查重表达式是否重复
        String[] s1=str1.split(" ");//将字符串s1转化成字符数组
        String[] s2=str1.split(" ");//将字符串s2转化成字符数组
        if(s1.equals(s2))
            return true;//若两列表内容相同返回true
        for(int j = 0; j < s2.length-1; j++) {//若不同进行循环将+或×后的两个字符串交换看能否相同
            if(s2[j].equals("+")||s2[j].equals("×")) {
                String temp= s2[j+1];
                s2[j+1]=s2[j+2];
                s2[j+2]=temp;
            }
            j*=3;
            if(s1.equals(s2))
                return true;//若交换后相同则返回true
        }return false;
    }
}






