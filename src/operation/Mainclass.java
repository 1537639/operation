package operation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Mainclass {
    public static void main(String[] args) throws IOException {
        Generate_exercise g_e=new Generate_exercise();//生成题目类
        Output_result o_r=new Output_result();//输出结果到exercise.txt和answer.txt类
        Check_result c_r=new Check_result();//检查答案并输出到grade.txt类
        TreeNode tree;//公式树
        int exercise_num=10;//生成题目个数
        int limit_num=20;//界定生成数范围
        String exercise;//一个题目
        List<String> exercises=new ArrayList<>();//题目查重列表
        String answer;//一个答案


        //输入生成题目个数n
        
        //输入界定生成数范围

        //进行n次以下操作
        for(int i=1;i<=exercise_num;i++)
        {
            exercise=g_e.generate(limit_num);
            String[] exc=TreeNode.toStringArrayTrimOutParrenthes(exercise);
            tree=new TreeNode(exc);
            Num resultNum=tree.calculate();
            Operate.simplify(resultNum);
            answer=Operate.fraction(resultNum);
            if(answer==null)//如果返回空值，说明答案是负数
            {
                i--;
                continue;//扔掉该回答
            }

            if(Operate.IsRepeat(exercises,tree))//如果重复
            {
                i--;
                continue;//扔掉该回答
            }
            else//否则加入该回答到查重列表
            {
                String dup=Answer_result.toPostifixExp(tree);
                dup=Answer_result.getDupExpression(dup);
                exercises.add(dup);
            }
            o_r.output(exercise,answer,i);//输出该题目和答案到文件
        }

        //判断答案是否正确，输出分数到grade.txt

    }
}
